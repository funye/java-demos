package com.fun.mapping.jdop.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fun.mapping.jdop.annotation.Query;
import com.fun.mapping.jdop.utils.CommonUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结果映射默认实现
 * @author huanye
 * Date: 2017/9/22 下午12:33
 */
public class DefaultResultMapping implements ResultMapping {

    @Override
    public <T> T convertToOne(List<Map<String, Object>> list, Class<T> clazz, String... keys) throws Exception {
        if (list == null || list.size() < 1) {
            return null;
        }

        Map<String,T> resultMap = handlerList(list,clazz,keys);

        // mapToList
        if (resultMap.size() < 0) {
            return null;
        }
        if (resultMap.size() == 1) {
            for (Map.Entry<String,T> m : resultMap.entrySet()) {
                return m.getValue();
            }
        }
        throw new Exception("查询结果为多条，需要使用List接收");

    }

    @Override
    public <T> List<T> convertToList(List<Map<String, Object>> list, Class<T> clazz, String... keys) throws Exception {
        if (list == null || list.size() < 1) {
            return null;
        }

        Map<String,T> resultMap = handlerList(list,clazz,keys);

        // mapToList
        if (resultMap.size() <= 0) {
            return null;
        } else {
            // convert map to list
            List<T> result = new ArrayList<>(resultMap.size());
            resultMap.forEach((k,v)-> result.add(v));
            return result;
        }
    }

    // 处理map的一个key-value
    private Object doMapping(Object instance, Class clazz, int level, String key, Object v, String uniueqKey) throws Exception {

        // 处理map的一个节点
        String[] keys = key.split("\\.");
        Field f = clazz.getDeclaredField(keys[level]);

        if (CommonUtil.isJavaBasicClass(f.getType())) { // 如果是基础类型

            Method m = clazz.getDeclaredMethod("set" + CommonUtil.firstUpperCase(f.getName()), f.getType());
            m.invoke(instance, v);
            return instance;

        } else { // 用户定义类型，区分是否是集合类型，如果是集合类型，数据要合并

            if (!CommonUtil.isContainerType(f.getType())) { // 单个实体类型

                level++; // 深入一层
                // 自定义对象必须要有子元素
                if (level >= keys.length) {
                    throw new Exception("field [" + f.getName() + "] must has sub elements");
                }
                Method m = clazz.getDeclaredMethod("get" + CommonUtil.firstUpperCase(f.getName()));
                Object subInstance = m.invoke(instance);
                if (subInstance == null) { // 创建并设置
                    subInstance = f.getType().newInstance();
                    Method m2 = clazz.getDeclaredMethod("set" + CommonUtil.firstUpperCase(f.getName()), f.getType());
                    m2.invoke(instance, subInstance);
                }
                doMapping(subInstance, f.getType(), level, key, v, uniueqKey);
                return instance;

            } else { // 集合类型
                level++; // 深入一层
                // 自定义对象必须要有子元素
                if (level >= keys.length) {
                    throw new Exception("field [" + f.getName() + "] must has sub elements");
                }

                Method m = clazz.getDeclaredMethod("get" + CommonUtil.firstUpperCase(f.getName()));
                Object subInstance = m.invoke(instance);
                if (subInstance == null) { // 创建集合对象
                    Map<String,Object> subMap = new HashMap<>();
                    Method m2 = clazz.getDeclaredMethod("set" + CommonUtil.firstUpperCase(f.getName()), f.getType());
                    m2.invoke(instance, subInstance);
                }
                //doMapping(null,null,level,key,v,keys[level]);
                return instance;
            }
        }
    }

    private <T> Map<String,T> handlerList(List<Map<String, Object>> list,Class<T> clazz,String... keys) throws Exception {
        Map<String,T> resultMap = new HashMap<>();
        for (Map<String, Object> map : list) {

            int level = 0;
            T instance = resultMap.get(keys[level]) != null ? resultMap.get(keys[level]) : clazz.newInstance();

            String uniqueKey = null;
            for (Map.Entry<String,Object> m : map.entrySet()) {
                String k = m.getKey();
                Object v = m.getValue();
                uniqueKey = uniqueKey == null ? (k.equals(keys[level]) ? v.toString() : null) : uniqueKey;
                instance = (T) doMapping(instance,clazz,level,k,v,keys[level]);
            }
            if (uniqueKey != null) {
                resultMap.put(uniqueKey, instance);
            }
        }
        return resultMap;
    }


    public <T> List<T> handlerResult(Class<T> clazz,List<Map<String, Object>> results,int level,String fix,String... mergeKeys) throws Exception {

        List<T> resultList = null;
        Map<String, Map> map = new HashMap<>();
        for (int i = 0; i < level; i++) { // 分层处理list里面的map,i为当前层次
            for (int j = 0; j < results.size(); j++) { // 得到一个map，即一条数据库记录
                Map<String, Object> result = results.get(j);
                for (Map.Entry<String,Object> entry : result.entrySet()) {
                    String k = entry.getKey();
                    Object v = entry.getValue();
                    if (CommonUtil.containsTimes(k,".") == i) { // 找到层数为 i 的key-value对，进行处理
                        if (i == 0) { // 根元素，即要返回的clazz类型
                            String key = fix + result.get(mergeKeys[0]) + fix;
                            if (map.get(key) == null) {
                                map.put(key, new HashMap<>());
                            }
                            map.get(key).put(k,v);
                        } else { // 叶子节点，可能是单个复杂类型(如：city.province)，也可能是多个复杂类型(如：city.areas)

                        }
                    }
                }
            }
        }

        // 明确多个而结果
        String resultJson = JSON.toJSONString(map).replaceFirst("\\{","\\[");
        resultJson = resultJson.substring(0,resultJson.length()-1) + "]";

        // 剔除不要的key
        resultJson = resultJson.replaceAll("\""+fix+"[\\w]+"+fix+"\":","");
        System.out.println(resultJson);

        resultList = JSONArray.parseArray(resultJson,clazz);
        return resultList;
    }

}
