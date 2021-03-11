package com.fun.order.service;

import com.fun.order.model.Order;
import com.fun.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService2 {

    @Autowired
    private OrderService orderService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Order insertRequired(Order order) {
        try {
            return orderService.insertRequired(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
