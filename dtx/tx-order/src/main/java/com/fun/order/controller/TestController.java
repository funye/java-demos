package com.fun.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fun.order.api.OrderApi;
import com.fun.order.api.OrderDto;
import com.fun.stock.api.StockApi;
import com.fun.stock.api.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    OrderApi orderApi;

    @Reference
    private StockApi stockApi;

    @RequestMapping("/test/order")
    public void test() {

        orderApi.createOrder(new OrderDto());

        stockApi.updateStock(new StockDto());
    }
}
