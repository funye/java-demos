package com.fun;

import lombok.Data;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class TestUnit {

    @Test
    public void testUUID() {
        System.out.println(UUID.randomUUID());
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

        List<Object> result = list.stream().filter(e->{
            try {
                Method m = e.getClass().getMethod("getName");
                Object name = m.invoke(e);
                System.out.println(name);
                return name != null;
            } catch (Exception ex) {

            }
            return false;
        }).map(e->{
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




}
