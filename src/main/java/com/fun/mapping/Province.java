package com.fun.mapping;

import java.util.List;

/**
 * @author huanye
 * Date: 2017/9/22 上午10:10
 */
public class Province {

    private String code;

    private String name;

    private List<City> cities;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
