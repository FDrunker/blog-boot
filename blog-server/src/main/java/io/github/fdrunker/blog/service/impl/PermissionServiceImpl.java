package io.github.fdrunker.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fdrunker.blog.entity.system.PermissionEntity;
import io.github.fdrunker.blog.mapper.PermissionMapper;
import io.github.fdrunker.blog.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
}
