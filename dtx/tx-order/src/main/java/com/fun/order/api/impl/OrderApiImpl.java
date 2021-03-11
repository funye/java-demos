package com.fun.order.api.impl;

import com.fun.order.api.OrderApi;
import com.fun.order.api.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderApiImpl implements OrderApi {
    @Override
    public boolean createOrder(OrderDto orderDto) {
        return false;
    }
}
