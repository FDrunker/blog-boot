package io.github.fdrunker.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.fdrunker.blog.pojo.entity.system.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper 接口
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
