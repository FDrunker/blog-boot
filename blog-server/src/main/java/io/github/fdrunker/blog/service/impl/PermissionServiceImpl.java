package io.github.fdrunker.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fdrunker.blog.pojo.entity.system.PermissionEntity;
import io.github.fdrunker.blog.mapper.PermissionMapper;
import io.github.fdrunker.blog.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {
}
