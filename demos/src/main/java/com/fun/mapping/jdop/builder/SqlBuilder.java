package com.fun.mapping.jdop.builder;

/**
 * @author huanye
 * Date: 2017/9/22 下午12:31
 */
public interface SqlBuilder {

    String build(String jSQL,Object... params);
}
