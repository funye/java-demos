package com.fun.simple;

import lombok.Data;
import org.junit.Test;
import scala.Predef;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JunitTest {

    @Test
    public void testGroupby() {

        List<Result> list = new ArrayList<>();
        list.add(new Result("1", "湖北", "11", "武汉", "111", "武昌区"));
        list.add(new Result("1", "湖北", "11", "武汉", "112", "江汉区"));
        list.add(new Result("1", "湖北", "12", "孝感", "121", "孝南区"));
        list.add(new Result("2", "广东", "21", "深圳", "211", "南山区"));
        list.add(new Result("2", "广东", "22", "广州", "221", "天河区"));

        Map<String, Map<String, Map<String, List<Result>>>> map = list.stream()
                .collect(
                        Collectors.groupingBy(Result::getProvinceCode,
                                Collectors.groupingBy(Result::getCityCode,
                                        Collectors.groupingBy(Result::getAreaCode)))
                );
        System.out.println(map);
    }

    @Data
    public class Result {
        private String provinceCode;
        private String provinceName;

        private String cityCode;
        private String cityName;

        private String areaCode;
        private String areaName;

        public Result(String provinceCode, String provinceName, String cityCode, String cityName, String areaCode, String areaName) {
            this.provinceCode = provinceCode;
            this.provinceName = provinceName;
            this.cityCode = cityCode;
            this.cityName = cityName;
            this.areaCode = areaCode;
            this.areaName = areaName;
        }
    }


    @Test
    public void tmpTool() {
        String tml = "CREATE TABLE `lc_user_client` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `ssoid` varchar(64) NOT NULL COMMENT '用户id',\n" +
                "  `client_id` varchar(64) NOT NULL COMMENT '客户端与长连接中间件链接id,用来确定客户端的标志',\n" +
                "  `app_version` varchar(32) NOT NULL COMMENT 'APP版本号',\n" +
                "  `create_time` timestamp NOT NULL COMMENT '创建时间',\n" +
                "  `update_time` timestamp NULL DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idx_ssoid` (`ssoid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户客户端连接关系表';";

        for (int i = 0; i < 100; i++) {
            System.out.println(tml.replace("lc_user_client", "lc_user_client_" + i));
            System.out.println();
        }

    }


    @Test
    public void testBit() {
        String word = "abac";
        int mask = 0;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            mask |= (1 << (ch - 'a'));
        }
        if (Integer.bitCount(mask) <= 7) {
            System.out.println(mask);
        }
    }

    @Test
    public void testPage() {

        long min = 267242963487975424L, max = 274122347738073600L;

        long gap = (max - min) / 3;
        long remain = (max - min) % 3;
        System.out.println("gap=" + gap + ", remain=" + remain);

        System.out.printf("page1 start=%d, end=%d\n" +
                "page1 start=%d, end=%d\n" +
                "page1 start=%d, end=%d\n", min, min+gap, min+gap, min+2*gap, min+2*gap, min+3*gap);

    }

}
