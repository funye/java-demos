package com.fun.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fun.mapping.jdop.mapping.DefaultResultMapping;
import com.fun.mapping.jdop.mapping.ResultMapping;
import com.fun.mapping.jdop.utils.CommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huanye
 * Date: 2017/9/22 上午10:33
 */
public class TestUnit {

    private ResultMapping resultMapping = new DefaultResultMapping();

    /**
     * 测试一对一，多条记录
     */
    @Test
    public void testMultiOne2One() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("code", "420106");
        map.put("name", "武昌区");
        map.put("city.code", "420100");
        map.put("city.name", "武汉市");
        map.put("city.province.code", "420000");
        map.put("city.province.name", "湖北省");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("code", "420102");
        map2.put("name", "江岸区");
        map2.put("city.code", "420100");
        map2.put("city.name", "武汉市");
        map2.put("city.province.code", "420000");
        map2.put("city.province.name", "湖北省");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("code", "420982");
        map3.put("name", "安陆市");
        map3.put("city.code", "420900");
        map3.put("city.name", "孝感市");
        map3.put("city.province.code", "420000");
        map3.put("city.province.name", "湖北省");

        list.add(map);
        list.add(map2);
        list.add(map3);

        List<Area> areas = resultMapping.convertToList(list, Area.class, "code", "city.code", "city.province.code");
        System.out.println("json result is: " + JSON.toJSONString(areas));


        Map<String,Object> city = new HashMap<>();
        city.put("code","420100");
        city.put("name","武汉市");
        map.put("city",city);
        Area a = new Area();
        BeanUtils.populate(a,map);
        System.out.println(a.getName());



    }

    /**
     * 测试一对多，多条记录
     */
    @Test
    public void testMultiOne2Many() throws Exception {
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("cities.areas.code","420106");
        map.put("cities.areas.name","武昌区");
        map.put("cities.code","420100");
        map.put("cities.name","武汉市");
        map.put("code","420000");
        map.put("name","湖北省");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("cities.areas.code","420102");
        map2.put("cities.areas.name","江岸区");
        map2.put("cities.code","420100");
        map2.put("cities.name","武汉市");
        map2.put("code","420000");
        map2.put("name","湖北省");

        Map<String,Object> map3 = new HashMap<>();
        map3.put("cities.areas.code","440305");
        map3.put("cities.areas.name","南山区");
        map3.put("cities.code","440300");
        map3.put("cities.name","深圳市");
        map3.put("code","440000");
        map3.put("name","广东省");

        list.add(map);
        list.add(map2);
        list.add(map3);

        List<Province> plist = resultMapping.convertToList(list,Province.class,"code","cities.code","cities.areas.code");
        System.out.println("json result is: " + JSON.toJSONString(plist));

        List<Province> resultList = ((DefaultResultMapping)resultMapping).handlerResult(Province.class,list,3,"###","code","code,","cities.code");
        System.out.println(resultList.size());
    }

    @Test
    public void testStringSplit() {
        String test = "area.city.province";
        String[] keys = test.split("\\.");
        System.out.println(keys.length);
    }

    @Test
    public void testStr() throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        Province p = new Province();

        Type t = p.getClass().getDeclaredField("cities").getGenericType();
        if (t instanceof ParameterizedType) {
            System.out.println((((ParameterizedType) t).getActualTypeArguments()[0]).getTypeName());
        }

    }

    @Test
    public void testStrings() {
        String source = "{\n" +
            "\t\"code\": \"420100\",\n" +
            "\t\"name\": \"武汉市\",\n" +
            "\t\"areas\": [{\n" +
            "\t\t\"code\": \"420102\",\n" +
            "\t\t\"name\": \"武昌区\"\n" +
            "\t}, {\n" +
            "\t\t\"code\": \"420101\",\n" +
            "\t\t\"name\": \"江汉区\"\n" +
            "\t}],\n" +
            "\t\"province\": {\n" +
            "\t\t\"code\": \"420000\",\n" +
            "\t\t\"name\": \"湖北省\"\n" +
            "\n" +
            "\t}\n" +
            "}";
        City c = JSONObject.parseObject(source,City.class);
        System.out.println(c.getName());
    }

}
