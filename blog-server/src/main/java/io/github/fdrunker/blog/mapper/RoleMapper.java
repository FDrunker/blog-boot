package io.github.fdrunker.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.fdrunker.blog.entity.system.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
}
