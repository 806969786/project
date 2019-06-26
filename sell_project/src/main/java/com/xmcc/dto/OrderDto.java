package com.xmcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("订单列表入参")//swagger 参数的描述信息
public class OrderDto implements Serializable {

    @NotBlank(message = "买家微信id不能为空")
    @ApiModelProperty(value = "买家微信id",dataType = "String")//swagger 参数的描述信息
    private String openid;

    @ApiModelProperty(value = "当前页",dataType = "Integer")
    private Integer page;

    @NotNull(message = "每页大小不能为空")
    @Min(value = 1,message = "最小为1")
    @ApiModelProperty(value = "每页大小",dataType = "Integer")
    private Integer size;
}
