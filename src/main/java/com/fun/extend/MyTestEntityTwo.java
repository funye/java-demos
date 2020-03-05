package com.fun.extend;

/**
 * @author huanye
 * @date: 2017/7/14 下午5:10
 */
public class MyTestEntityTwo extends BaseEntity {

    private long id;

    private String name;

    public MyTestEntityTwo(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String printTwo() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
            .append(id);
        sb.append(",\"name\":\"")
            .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
