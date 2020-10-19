package com.fun.lang.fx;

public interface MessageHandler<IN extends String, OUT extends String> {

    OUT handle(IN in);
}
