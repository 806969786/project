package com.xmcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("订单详情入参Dto")//swagger 参数的描述信息
public class OrderDetailParticipationDto implements Serializable {

    @NotBlank(message = "买家微信id不能为空")
    @ApiModelProperty(value = "买家微信id",dataType = "String")//swagger 参数的描述信息
    private String openid;

    @NotBlank(message = "订单id不能为空")
    @ApiModelProperty(value = "订单id",dataType = "String")
    private String orderId;
}
