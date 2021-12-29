package com.fun.ratelimit;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class GuavaFiles {

    /*
     *
Guava的好用方法
LoadingCache（本地缓存）
Multimap 和 Multiset
BiMap
Table（表）
Sets和Maps（交并差）
EventBus（事件）
StopWatch（秒表）
Files（文件操作）
RateLimiter（限流器）
Guava Retry（重试）
     *
     */

    public static void main(String[] args) throws IOException {

        File f = new File("D:\\Users\\80279309\\Documents\\fun\\github\\java-demos\\little-demo\\src\\main\\java\\com\\fun\\ratelimit\\GuavaFiles.java");

        List<String> lines = Files.readLines(f, Charset.forName("UTF-8"));

        lines.forEach(e-> {
            System.out.println(e);
        });

    }
}
