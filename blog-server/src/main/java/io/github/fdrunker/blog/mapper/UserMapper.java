package io.github.fdrunker.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.fdrunker.blog.entity.system.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
