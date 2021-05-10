package com.fun.cglib;

import lombok.Data;

@Data
public class Target {

    private String name;
    private Integer age;

    @Data
    class Inner {
        private int val;
    }

}
