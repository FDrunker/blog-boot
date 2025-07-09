package io.github.fdrunker.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fdrunker.blog.common.constant.GlobalConstant;
import io.github.fdrunker.blog.common.exception.CheckException;
import io.github.fdrunker.blog.common.exception.CommonException;
import io.github.fdrunker.blog.common.exception.ErrorCode;
import io.github.fdrunker.blog.common.model.TokenBean;
import io.github.fdrunker.blog.common.utils.JwtUtil;
import io.github.fdrunker.blog.common.utils.Md5Util;
import io.github.fdrunker.blog.common.utils.RedisUtil;
import io.github.fdrunker.blog.mapper.PermissionMapper;
import io.github.fdrunker.blog.mapper.RoleMapper;
import io.github.fdrunker.blog.pojo.dto.LoginDTO;
import io.github.fdrunker.blog.pojo.entity.system.PermissionEntity;
import io.github.fdrunker.blog.pojo.entity.system.RoleEntity;
import io.github.fdrunker.blog.pojo.entity.system.UserEntity;
import io.github.fdrunker.blog.mapper.UserMapper;
import io.github.fdrunker.blog.pojo.vo.UserVO;
import io.github.fdrunker.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;
    private final RedisUtil redisUtil;
    @Value("${jwt.expireTime}")
    private Long expireTime;

    @Override
    public UserVO login(LoginDTO loginDTO) {
        UserEntity user = userMapper.selectOne(
                lambdaQuery().eq(UserEntity::getAccount, loginDTO.getAccount())
                        .last(GlobalConstant.LAST_LIMIT_1)
        );
        if (Objects.isNull(user)) {
            throw new CheckException(ErrorCode.USER_LOGIN_ERROR);
        }
        if (GlobalConstant.DISABLE.equals(user.getStatus())) {
            throw new CheckException(ErrorCode.USER_STATUS_ERROR);
        }
        if (!Md5Util.verifyPassword(loginDTO.getPassword(), user.getPassword(), user.getSalt())) {
            throw new CheckException(ErrorCode.USER_LOGIN_ERROR);
        }
        // 获取权限
        RoleEntity role = roleMapper.selectById(user.getRoleId());
        List<String> permissions = permissionMapper.selectList(
                Wrappers.lambdaQuery(PermissionEntity.class)
                        .eq(PermissionEntity::getRoleId, user.getRoleId())
        ).stream().map(PermissionEntity::getCode).toList();
        // 登录权限校验
        if (permissions.stream().anyMatch(permission ->
                Pattern.matches(permission.replace("*", ".*"), "user:manage:login")
        )) {
            throw new CheckException(ErrorCode.PERMISSION_ERROR);
        }
        // 创建token
        TokenBean tokenBean = TokenBean.builder()
                .userId(user.getId())
                .userType(user.getUserType())
                .account(user.getAccount())
                .roleName(role.getRoleName())
                .permissionList(permissions)
                .build();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        String token;
        try {
            token = JwtUtil.createToken(tokenBean);
        } catch (Exception e) {
            throw new CommonException(ErrorCode.OTHER_ERROR);
        }
        userVO.setToken(token);
        redisUtil.set(GlobalConstant.getRedisUserKey(user.getAccount()), token, expireTime);
        return userVO;
    }

    @Override
    public void logout() {
        redisUtil.remove(GlobalConstant.getRedisUserKey(JwtUtil.getAccount()));
    }
}
