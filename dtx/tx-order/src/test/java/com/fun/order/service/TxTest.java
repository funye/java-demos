package com.fun.order.service;

import com.fun.order.AppTest;
import com.fun.order.model.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

public class TxTest extends AppTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderService2 orderService2;

    @Test
    public void test1() {
        Order order = new Order();
        order.setOrderNo("1000001");
        order.setName("测试订单");
        order.setCreateTime(new Date());
        orderService.insert(order);
    }

    @Test
    public void test2() throws Exception {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-",""));
        order.setName("测试订单");
        order.setCreateTime(new Date());
        orderService.insertRequired(order);
    }

    @Test
    public void test3() throws Exception {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replaceAll("-",""));
        order.setName("测试订单");
        order.setCreateTime(new Date());
        orderService2.insertRequired(order);
    }

}
