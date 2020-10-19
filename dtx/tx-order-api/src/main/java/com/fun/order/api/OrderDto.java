package com.fun.order.api;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto implements java.io.Serializable {

    private String orderId;
    private String name;
    private String userId;
    private long amount;
    private String productId;
    private Date createTime;
}
