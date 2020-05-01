package com.fun.collection;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by yehuan on 2017/2/13.
 */
@Data
public class User {

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("年龄")
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
