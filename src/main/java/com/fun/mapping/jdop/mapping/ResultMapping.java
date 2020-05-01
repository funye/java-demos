package com.fun.mapping.jdop.mapping;

import java.util.List;
import java.util.Map;

/**
 * @author huanye
 * Date: 2017/9/22 上午10:13
 */
public interface ResultMapping {

    /**
     * 转换过成单条记录
     * @param list
     * @param clazz
     * @param keys
     * @param <T>
     * @return
     */
    <T> T convertToOne(List<Map<String,Object>> list, Class<T> clazz,String... keys) throws Exception;

    /**
     * 转换成多条记录
     * @param list
     * @param clazz
     * @param keys
     * @param <T>
     * @return
     */
    <T> List<T> convertToList(List<Map<String,Object>> list, Class<T> clazz,String... keys) throws Exception;
}
