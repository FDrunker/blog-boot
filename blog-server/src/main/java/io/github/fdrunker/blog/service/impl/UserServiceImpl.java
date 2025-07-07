package io.github.fdrunker.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.fdrunker.blog.entity.system.UserEntity;
import io.github.fdrunker.blog.mapper.UserMapper;
import io.github.fdrunker.blog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {



}
