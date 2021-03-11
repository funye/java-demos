package com.fun.order;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class TestUnit {

    @Test
    public void testUUID() {
        System.out.println(UUID.randomUUID());
        System.out.println(new Random().nextInt(2));
    }

    @Test
    public void testListType() throws Exception {

        List<TestUser> list = new ArrayList<>();
        list.add(new TestUser("123"));
        list.add(new TestUser(null));
        test123(list);

    }


    public <T> void test123(List<T> list) throws Exception {

        if (list == null) return;

        List<Object> result = list.stream().filter(e -> {
            try {
                Method m = e.getClass().getMethod("getName");
                Object name = m.invoke(e);
                System.out.println(name);
                return name != null;
            } catch (Exception ex) {

            }
            return false;
        }).map(e -> {
            Object name = null;
            try {
                Method m = e.getClass().getMethod("getName");
                name = m.invoke(e);
                System.out.println(name);
            } catch (Exception ex) {
            }
            return name;
        }).collect(Collectors.toList());

        System.out.println(result);
        System.out.println("end============");

        Set<Object> iidSet = new HashSet<>();
        for (Object e : list) {
            Method m = e.getClass().getMethod("getName");
            Object name = m.invoke(e);
            System.out.println(name);

            if (name == null) {
                System.out.println("null error");
                break;
            }
            if (iidSet.contains(name)) {
                System.out.println("duplicate error");
                break;
            } else {
                iidSet.add(name);
            }
        }


    }

    @Data
    class TestUser {
        String name;

        public TestUser(String name) {
            this.name = name;
        }
    }


    @Test
    public void testR() {
        System.out.printf("C语言语言言言言\rjava语言\n--------\n");
        System.out.printf("C语言\njava语言\n--------\n");
        System.out.printf("C语言\r\njava语言\n--------\n");

        int[] arr = new int[10];
        System.out.println(arr.length);

        String test = "abc";
        System.out.println(test.length());

        System.out.println("===========================");
        System.out.println("004".compareTo("0041"));
        System.out.println("005-".compareTo("0041"));

    }


    @Test
    public void testBoolean() {
        System.out.println("true".equals(Boolean.TRUE));
        System.out.println(Boolean.TRUE.equals(true));
        System.out.println("===============================");
        TestJson tj = new TestJson("power", Boolean.TRUE);
        System.out.println(JSON.toJSONString(tj));
        System.out.println("===============================");
        TestJson tj2 = new TestJson("power", "false");
        System.out.println(JSON.toJSONString(tj2));
    }


    @Data
    class TestJson implements java.io.Serializable {
        private String code;
        private Object value;

        public TestJson(String code, Object value) {
            this.code = code;
            this.value = value;
        }
    }

    @Test
    public void calculateTable() {
        // 实例表
//        String ssoId = "558242084";
        String ssoId = "560585299";
        int piece = Math.abs(("IOT" + ssoId).hashCode()) % 100;
        System.out.println(String.format("table name = %s_%d", "instance", piece));

        // 设备影子
        System.out.println(String.format("shadow collection = shadow_deviceId_prefix_%d", Math.abs("9895604651720".hashCode()) % 100));

        // third party
        String key = "780753004570:zVQjY126";
        piece = Math.abs(("IOT" + key).hashCode()) % 100;
        System.out.println(String.format("table name = %s_%d", "thirdparty_device_info", piece));

    }

    @Test
    public void test() {
        String key = "test-key";
        String key1 = "test-key1";
        byte[][] keys = getKeys(key.getBytes(Charset.forName("UTF-8")), key1.getBytes(Charset.forName("UTF-8")));
        System.out.println(keys);
    }

    public byte[][] getKeys(byte[]... keys) {
        if (ArrayUtil.isEmpty(keys)) {
            return null;
        } else {
            Object[] result = Arrays.stream(keys).filter(ArrayUtil::isNotEmpty).toArray();
            int i = 0;
            for (Object o : result) {
                keys[i] = (byte[])o;
                i++;
            }
            Object[] aa = new Object[2];

            String[] bb = (String[])aa;

            byte[][]cc = (byte[][])aa;
        }
        return keys;
    }

    @Test
    public void testArray() {
//        Object[] a = new Object[]{"aaa", "bbb"};
//        String[] sArr = (String[])a;

        Object[] b = new Object[]{"aaa".getBytes(), "bbb".getBytes()};
        byte[][] byteArr = (byte[][])b;
    }

}
