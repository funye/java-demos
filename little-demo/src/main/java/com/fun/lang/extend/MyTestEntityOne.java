package com.fun.lang.extend;

/**
 * @author huanye
 * @date: 2017/7/14 下午5:10
 */
public class MyTestEntityOne extends BaseEntity {

    private long id;

    private int age;

    public MyTestEntityOne(long id, int age) {
        this.id = id;
        this.age = age;
    }

    public String printOne() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
            .append(id);
        sb.append(",\"age\":")
            .append(age);
        sb.append('}');
        return sb.toString();
    }
}
