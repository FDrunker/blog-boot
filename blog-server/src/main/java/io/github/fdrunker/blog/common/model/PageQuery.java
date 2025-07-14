package io.github.fdrunker.blog.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询参数对象", description = "分页查询参数对象")
public class PageQuery {

    @ApiModelProperty(value = "当前页码")
    private Integer current = 1;

    @ApiModelProperty(value = "每页显示数量")
    private Integer size = 10;

}
