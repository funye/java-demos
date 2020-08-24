package com.fun.pattern.builder;

import lombok.Builder;
import lombok.ToString;

import java.util.Date;

@Builder
@ToString
public class MyUser {

    private String phone; // 必填
    private String nickName; // 必填
    private String email; // 选填
    private Integer age;// 选填
    private Date birthday;// 选填

    public static void main(String[] args) {

        MyUser myUser = MyUser.builder().nickName("张三").build();
        System.out.println(myUser);
    }
}
