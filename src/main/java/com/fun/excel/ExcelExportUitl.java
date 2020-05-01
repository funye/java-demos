package com.fun.excel;

import net.sf.jxls.transformer.XLSTransformer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator
 * Date: 2017/9/6.
 */
public class ExcelExportUitl {

    public static File createExcel(String templateFile, Map<String, ?> map, String resultFile) {
        // 创建XLSTransformer对象
        XLSTransformer transformer = new XLSTransformer();
        try {
            // 生成Excel文件
            transformer.transformXLS(templateFile, map, resultFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new File(resultFile);
    }

    public static void main(String[] args) {

        String templateFile = "C:\\Users\\Administrator\\Desktop\\output\\template.xlsx";
        String resultFile = "C:\\Users\\Administrator\\Desktop\\output\\users2.xlsx";


        int size = 100;
        List<TestUser> list = new ArrayList<>(size);
        for (int i = 0; i < 100; i++) {
            String name = "user_" + i;
            String LoginName = "loginName_" + i;
            list.add(new TestUser( i, name, "123123", LoginName));
        }

        Map<String, List<TestUser>> data = new HashMap<>();
        data.put("list",list);

        ExcelExportUitl.createExcel(templateFile, data, resultFile);
    }

    public static class TestUser {

        private int id;
        private String name;
        private String password;
        private String LoginName;

        public TestUser(int id, String name, String password, String loginName) {
            this.id = id;
            this.name = name;
            this.password = password;
            LoginName = loginName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLoginName() {
            return LoginName;
        }

        public void setLoginName(String loginName) {
            LoginName = loginName;
        }
    }

}
