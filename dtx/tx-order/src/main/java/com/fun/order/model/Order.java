package com.fun.order.model;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_order")
@DynamicUpdate
public class Order implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String orderNo;

    private String name;

    private Date createTime;

    private Date updateTime;
}
