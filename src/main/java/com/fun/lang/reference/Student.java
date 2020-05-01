package com.fun.lang.reference;

import java.io.Serializable;

/**
 * Created by Administrator
 *
 * @date 2017/4/27.
 */
public class Student implements Serializable {

    private String name;
    private ClassRoom room;

    public Student() {
    }

    public Student(String name, ClassRoom room) {
        this.name = name;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassRoom getRoom() {
        return room;
    }

    public void setRoom(ClassRoom room) {
        this.room = room;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"room\":")
                .append(room);
        sb.append('}');
        return sb.toString();
    }

}
