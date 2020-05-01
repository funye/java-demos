package com.fun.excel.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.fun.collection.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SimpleReadListener extends AnalysisEventListener<User> {

    private List<User> list = new ArrayList<>();

    private static final long BATCH_COUNT=10;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void invoke(User data, AnalysisContext analysisContext) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
//        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (list.size() >= BATCH_COUNT) {
//            System.out.println(JSON.toJSONString(list));
//            // 存储完成清理 list
//            list.clear();
//        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成！");

        System.out.println(JSON.toJSONString(list));
    }
}
