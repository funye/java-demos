package com.fun.java8;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

@Slf4j
public class RuntimeExec {

    public static void main(String[] args) {

        String cmd = "cmd /C dir";
        method1(cmd);
        System.out.println("==================================================");
        method2(cmd);
    }

    public static void method1(String vCmd) {
        try {
            long start = System.currentTimeMillis();
            Process ps = Runtime.getRuntime().exec(vCmd);
            int exitValue = ps.waitFor();
            if (0 != exitValue) {
                System.out.println("==========执行错误=======");
            }

            BufferedInputStream in = new BufferedInputStream(ps.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            in.close();
            reader.close();


            String result = sb.toString();
            if (result == null || result.length() == 0) {
                System.out.println("======执行结果: \n" + "返回空白");
            } else {
                System.out.println("======执行结果：\n" + result);
            }

            System.out.println("======执行耗时: " + (System.currentTimeMillis() - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void method2(String vCmd) {
        long s = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            CommandLine commandline = CommandLine.parse(vCmd);

            //看门狗，可设置超时
            ExecuteWatchdog watchdog = new ExecuteWatchdog(1000);

            DefaultExecutor exec = new DefaultExecutor();
            exec.setExitValues(null);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,errorStream);
            exec.setStreamHandler(streamHandler);
            exec.setWatchdog(watchdog);

            //调用命令行
            exec.execute(commandline);
            sb.append(" execute used " + (System.currentTimeMillis() - s + /*后面为0 赋值*/((s = System.currentTimeMillis())-s)  )  );

            //消费数据
            String out = outputStream.toString("gbk");
            String error = errorStream.toString("gbk");
            sb.append(" stream used " + (System.currentTimeMillis() - s + /*后面为0 赋值*/((s = System.currentTimeMillis())-s)  )  );
            System.out.println(out);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            sb.append("  exception info "+e.getMessage()+" used " + (System.currentTimeMillis() - s + /*后面为0 赋值*/((s = System.currentTimeMillis())-s)  )  );
            System.out.println("F");
        } finally {
            log.info(" exec info " + sb.toString());
        }
    }
}
