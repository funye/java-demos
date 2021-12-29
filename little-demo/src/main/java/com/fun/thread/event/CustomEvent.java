package com.fun.thread.event;

import lombok.Data;

@Data
public class CustomEvent {

    private String id;
    private String data;

    public CustomEvent(String id, String data) {
        this.id = id;
        this.data = data;
    }
}
