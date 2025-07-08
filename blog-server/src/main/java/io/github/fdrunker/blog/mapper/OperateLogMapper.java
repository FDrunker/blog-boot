package io.github.fdrunker.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.fdrunker.blog.pojo.entity.system.OperateLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统管理-操作日志记录 Mapper 接口
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLogEntity> {

}
