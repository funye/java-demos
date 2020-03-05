package com.fun.collection;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yehuan on 2017/2/13.
 */
public class TestList {

    @Test
    public void testArrayList(){
        List<User> users = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++ ){
            users.add(new User("name_"+i,26));
        }
        User user = new User("fun",26);
        users.add(user);

        System.out.println(users.toArray());

        users.remove(user);

        System.out.println(users.toArray());
    }

    @Test
    public void testInoutParameter() {
        Integer count = 0;
        System.out.println(testChange(count));
        System.out.println(count);
    }

    public int testChange(Integer inoutParam) {
        inoutParam++;
        return inoutParam;
    }

    @Test
    public void testSubList() {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 12; i++ ) {
            list.add(i*10);
        }
        List<Integer> list1 = list.subList(0,3);

        int cursor = 0;
        int pageSize = 3;
        for (int i = 0; i < list.size() / pageSize ; i++ ) {
            System.out.println(i+":"+list.subList(cursor,cursor+pageSize));
            cursor = cursor+pageSize;
        }
        System.out.println(cursor);
        if(cursor < list.size()) {
            System.out.println("last part:"+list.subList(cursor,list.size()));
        }

    }

    @Test
    public void testAsList() {
        int[] a = {1,2,3,4};
        List list = Arrays.asList(a);
//        List<Integer> list2 = Arrays.asList(a); // 取消注释看看效果
        System.out.println(list.size());
    }
}
