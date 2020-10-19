package com.fun.collection;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanye
 *         Date: 2017/11/2 下午7:50
 */
public class TreeListUtil<T> {

    public T listToTreeNode(List<T> nodes, String idKey, String parentIdKey, String childrenKey, String rootNodeIdValue,String endParentIdValue) {
        if (rootNodeIdValue == null || nodes == null || nodes.size() < 1)
            return null;

        try {
            Class clazz = nodes.get(0).getClass();
            Method getId = clazz.getDeclaredMethod("get" + getFirstWordUpperCase(idKey));
            Method getParentId = clazz.getDeclaredMethod("get" + getFirstWordUpperCase(parentIdKey));
            Method getChildrens = clazz.getDeclaredMethod("get" + getFirstWordUpperCase(childrenKey));
            Method setChildrens = clazz.getDeclaredMethod("set" + getFirstWordUpperCase(childrenKey), List.class);

            Map<String, T> nodeMap = new HashMap<>();
            for (T node : nodes) {
                nodeMap.put(getId.invoke(node).toString(), node);
            }

            for (T node : nodes) {

                if (endParentIdValue != null) {

                }
                Object obj = getParentId.invoke(node);
                String pid = obj == null ? null : obj.toString();
                if (pid == null) { // 根节点
                    continue;
                }
                if (endParentIdValue != null && pid.equals(endParentIdValue)) {
                    continue;
                }
                List<T> childrens = (List<T>) getChildrens.invoke(nodeMap.get(pid));
                if (childrens == null || childrens.size() < 1) {
                    childrens = new ArrayList<>();
                    setChildrens.invoke(nodeMap.get(pid), childrens);
                }
                childrens.add(node);
            }

            return nodeMap.get(rootNodeIdValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFirstWordUpperCase(String word) {
        if (word == null || word.length() < 1) {
            return null;
        }
        char[] ch = word.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }


    public static void main(String[] args) {

        List<Node> nodes = new ArrayList<>();

        nodes.add(new Node("420103", "江汉区", "420100"));
        nodes.add(new Node("420100", "武汉", "420000"));
        nodes.add(new Node("420200", "黄石", "420000"));
//        nodes.add(new Node("420000", "湖北", null));
        nodes.add(new Node("420000", "湖北", "666666"));
        nodes.add(new Node("420102", "江岸区", "420100"));
        nodes.add(new Node("420202", "黄石港区", "420200"));

        final Clock clock = Clock.systemUTC();
        long start = clock.millis();
//        Node root = new TreeListUtil<Node>().listToTreeNode(nodes, "id", "pid", "childrens", "420000",null);
        Node root = new TreeListUtil<Node>().listToTreeNode(nodes, "id", "pid", "childrens", "420000","666666");
        long end = clock.millis();
        System.out.println("use time " + (end - start) + " ms");
        System.out.println(JSON.toJSONString(root));
    }

}
