package com.fun.stock.api;

import lombok.Data;

@Data
public class StockDto implements java.io.Serializable {
    private String productId;
    private long amount;
    private String productName;
}
