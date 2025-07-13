package io.github.fdrunker.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.fdrunker.blog.pojo.entity.system.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限映射 Mapper 接口
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity> {
}
