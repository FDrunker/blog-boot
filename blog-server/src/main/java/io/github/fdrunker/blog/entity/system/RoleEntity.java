package io.github.fdrunker.blog.entity.system;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.fdrunker.blog.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户中心-角色信息对象 base_role
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_role")
@ApiModel(value = "用户中心-角色信息对象", description = "system-用户中心-角色信息信息表")
public class RoleEntity extends BaseEntity {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleKey;

    @ApiModelProperty(value = "显示顺序")
    private Long sortNum;

    @ApiModelProperty(value = "角色状态（1正常 0停用）")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableLogic(value = "0", delval = "1")
    @ApiModelProperty(value = "删除标志（0正常 1删除）")
    private Boolean isDelete;

}