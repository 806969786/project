package com.xmcc.controller;

import com.xmcc.common.ResultResponse;
import com.xmcc.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/buyer/product")
@Api(description = "商品信息接口") //生成接口文档注解:描述类信息
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;

    /**
     * 查询商品列表
     * @return
     */
    @RequestMapping("/list")
    @ApiOperation(value = "查询商品列表") //生成接口文档注解:描述操作信息
    public ResultResponse list(){
        return productInfoService.queryList();
    }
}
