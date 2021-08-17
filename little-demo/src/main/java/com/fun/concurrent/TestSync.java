package com.fun.concurrent;

public class TestSync {

    synchronized void m() {

    }

    void n() {
        synchronized (this) {

        }
    }
}
