package io.github.fdrunker.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.fdrunker.blog.pojo.dto.LoginDTO;
import io.github.fdrunker.blog.pojo.entity.system.UserEntity;
import io.github.fdrunker.blog.pojo.vo.UserVO;

public interface UserService extends IService<UserEntity> {

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return 用户信息
     */
    UserVO login(LoginDTO loginDTO);

    /**
     * 用户登出
     */
    void logout();
}
