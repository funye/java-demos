package com.fun.lang.reference;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator
 *
 * @date 2017/4/27.
 */
public class ClassRoom implements Serializable {

    private String name;

    public ClassRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
