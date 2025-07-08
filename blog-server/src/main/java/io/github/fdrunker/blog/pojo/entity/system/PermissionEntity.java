package io.github.fdrunker.blog.pojo.entity.system;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.fdrunker.blog.pojo.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户中心-角色和权限关联表 base_permission
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_permission")
@ApiModel(value="Permission对象", description="用户中心-角色和权限关联表")
public class PermissionEntity extends BaseEntity {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限标识")
    private String code;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value = "显示顺序")
    private Long sortNum;

    @ApiModelProperty(value = "权限状态（1正常 0停用）")
    private Integer status;

    @TableLogic(value = "0", delval = "1")
    @ApiModelProperty(value = "删除标识（0正常 1删除）")
    private Boolean isDelete;

}
