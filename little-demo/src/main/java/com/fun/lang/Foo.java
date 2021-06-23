package com.fun.lang;

import com.fun.collection.User;
import org.redisson.cluster.ClusterPartition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by fun on 2017/2/13.
 */
public class Foo implements Serializable{

    private static final long serialVersionUID = -2L;

    public static int w = 1;
    public static transient int x = 2;
    public int y = 3;
    public transient int z = 4;

    public static User user = new User("yehuan",11);

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> list2 = new ArrayList<>();
//        list2.add(5);
//        list2.add(6);
//        list2.add(3);
//        list2.add(4);

        Set<Integer> set = list.stream().filter(s->list2.stream().noneMatch(p->p==s)).collect(Collectors.toSet());

        System.out.println(set);

        Collection<ClusterPartition> partitions = new ArrayList<>();
        partitions.add(new ClusterPartition("1"));
        partitions.add(new ClusterPartition("2"));

        ClusterPartition p = find(partitions, 1);
        System.out.println(p);
    }



    public static ClusterPartition find(Collection<ClusterPartition> partitions, Integer slot) {
        return partitions.stream().filter(p -> p.hasSlot(slot)).findFirst().orElseThrow(() -> {
            return new IllegalStateException("Unable to find partition with slot " + slot);
        });
    }
}
