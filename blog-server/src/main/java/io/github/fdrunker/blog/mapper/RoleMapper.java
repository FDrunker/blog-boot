package io.github.fdrunker.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.fdrunker.blog.pojo.entity.system.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色 Mapper 接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
}
