package com.fun.simple;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Temp {

    public static void main(String[] args) {
        String a = testFunction.apply("jack");
        String b = testFunction.apply("test");
        System.out.println(a);
        System.out.println(b);
    }

    @Data
    class OrgStructDto {
        private String orgCode;
        private List<OrgStructDto> subList;
    }

    public void getOrgCode(List<OrgStructDto> currentList, Set<String> departIds) {
        // 结束递归的条件
        if (currentList == null || CollectionUtil.isEmpty(currentList)) {
            return;
        }

        // 使用parallelStream 并行计算，最大限度提高多核cpu 线程利用率
        currentList.parallelStream().forEach(e-> {
            // 获取数据
            departIds.add(e.getOrgCode());
            // 递归下一层
            getOrgCode(e.getSubList(), departIds);
        });
    }

    public static Function<String, String> testFunction = in-> {
        if (in.equals("test")) {
            return null;
        } else {
            return "hello " + in;
        }
    };
}
