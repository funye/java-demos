package com.fun.order.service;

import com.fun.order.model.Order;
import com.fun.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    public Order insert(Order order) {
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Order insertRequired(Order order) throws Exception {
        order = orderRepository.saveAndFlush(order);
        throw new RuntimeException("error");
    }

}
