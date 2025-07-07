package io.github.fdrunker.blog.entity.system;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.fdrunker.blog.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户中心-用户信息对象 base_user
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_user")
@ApiModel(value = "用户中心-用户信息对象", description = "user-用户中心-用户信息信息表")
public class UserEntity extends BaseEntity {

    @ApiModelProperty(value = "小程序的openId")
    private String openId;

    @ApiModelProperty(value = "用户角色")
    private String roleId;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "用户加密盐值")
    private String salt;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "帐号状态（1正常 0停用）")
    private Integer status;

    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

    @TableLogic(value = "0", delval = "1")
    @ApiModelProperty(value = "删除标志（0正常 1删除）")
    private Boolean isDelete;

    @ApiModelProperty(value = "备注")
    private String remark;

}