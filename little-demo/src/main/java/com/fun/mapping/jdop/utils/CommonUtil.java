package com.fun.mapping.jdop.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author huanye
 * Date: 2017/9/22 下午6:09
 */
public class CommonUtil {

    /**
     * 首字母编程大写
     * @param str 待处理的字符串
     * @return 首字母大写模式的字符串
     */
    public static String firstUpperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 判断是不是java的八种基本类型
     * @param clazz class类型
     * @return 如果是java基本类型，则返回true，否则返回false
     * @throws Exception 抛出class类型错误异常
     */
    public static boolean isJavaBasicClass(Class<?> clazz) throws Exception {
        if (clazz == null) {
            throw new Exception("class type error");
        }
        return
            (
                clazz.equals(String.class) ||
                    clazz.equals(Integer.class)||
                    clazz.equals(Byte.class) ||
                    clazz.equals(Long.class) ||
                    clazz.equals(Double.class) ||
                    clazz.equals(Float.class) ||
                    clazz.equals(Character.class) ||
                    clazz.equals(Short.class) ||
                    clazz.equals(BigDecimal.class) ||
                    clazz.equals(BigInteger.class) ||
                    clazz.equals(Boolean.class) ||
                    clazz.equals(Date.class) ||
                    clazz.isPrimitive()
            );
    }

    /**
     * 判断是不是map或者collection类型
     * @param clazz class
     * @return 如果是map或者collection类型，返回true，否则返回false
     * @throws Exception 抛出class类型错误异常
     */
    public static boolean isContainerType(Class<?> clazz) throws Exception {
        if (clazz == null) {
            throw new Exception("class type error");
        }

        boolean isCollection = Collection.class.isAssignableFrom(clazz);
        boolean isMap = Map.class.isAssignableFrom(clazz);
//        boolean isIterator = Iterator.class.isAssignableFrom(clazz);

        return isCollection || isMap ;
    }


    /**
     * 统计target字符串在source中出现的次数
     * @param source 源字符串
     * @param target 目标字符串
     * @return 返回出现的次数
     */
    public static int containsTimes(String source,String target) {
        if (target == null || source == null) {
            return 0;
        }
        int count = 0;
        do{
            int index = source.indexOf(target);
            if (index >= 0) {
                count++;
                source = source.substring(index + target.length());
            } else{
                return count;
            }
        }while (true);

    }


    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();

        System.out.println(isContainerType(list.getClass()));
    }

}
