package io.github.fdrunker.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fdrunker.blog.pojo.entity.system.RoleEntity;
import io.github.fdrunker.blog.mapper.RoleMapper;
import io.github.fdrunker.blog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
}
