package com.fun.order.controller;

import com.fun.order.api.OrderApi;
import com.fun.order.api.OrderDto;
import com.fun.order.model.Order;
import com.fun.order.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    OrderApi orderApi;

//    @Reference
//    private StockApi stockApi;

    @RequestMapping("/test")
    public void test() {

        orderApi.createOrder(new OrderDto());

//        stockApi.updateStock(new StockDto());
    }

    @RequestMapping("/test/order")
    public Response<Order> testOrder() {
        Order order = new Order();
        order.setOrderNo("orderno-1000001");
        log.info("test={}", order.getOrderNo());
        return Response.ok(order);
    }
}
