package com.fun.mapping.app;

import java.util.List;

/**
 * @author huanye
 * Date: 2017/9/22 上午10:10
 */
public class City {

    private String code;

    private String name;

    private Province province;

    private List<Area> areas;

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

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
