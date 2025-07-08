package io.github.fdrunker.blog.pojo.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.fdrunker.blog.pojo.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统管理-操作日志记录
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_operate_log")
@ApiModel(value = "OperateLog对象", description = "系统管理-操作日志记录")
public class OperateLogEntity extends BaseEntity {

    @ApiModelProperty("模块标题")
    private String title;

    @ApiModelProperty("业务类型（0查询 1新增 2修改 3删除 4其他）")
    private Integer businessType;

    @ApiModelProperty("方法名称")
    private String method;

    @ApiModelProperty("响应时间")
    private Long respTime;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("操作类别（0网站用户 1后台用户 2小程序 3其他）")
    private Integer operateType;

    @ApiModelProperty("请求URL")
    private String operateUrl;

    @ApiModelProperty("主机地址")
    private String operateIp;

    @ApiModelProperty("操作地点")
    private String operateLocation;

    @ApiModelProperty("请求参数")
    private String operateParam;

    @ApiModelProperty("返回参数")
    private String jsonResult;

    @ApiModelProperty("操作状态（0异常 1正常）")
    private Integer status;

    @ApiModelProperty("错误消息")
    private String errorMsg;

}