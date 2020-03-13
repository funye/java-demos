package com.fun.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fun.collection.User;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {


    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "d:/demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, User.class, new SimpleReadListener()).sheet().doRead();

        // 写法2：
        fileName = "d:/demo2.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, User.class, new SimpleReadListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    public void simpleWrite() {
        // 写法1
        String fileName = "d:/demo.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, User.class).sheet("模板").doWrite(data());

        // 写法2
        fileName = "d:/demo2.xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    private List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User("name"+i, 10*i);
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        new EasyExcelTest().simpleWrite();
//        new EasyExcelTest().simpleRead();
    }
}
