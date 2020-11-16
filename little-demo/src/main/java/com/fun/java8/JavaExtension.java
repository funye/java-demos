package com.fun.java8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaExtension {

    public static void main(String[] args) {
//        callCMD();
        callPing();
    }

    public static void callCMD() {
        Runtime r = Runtime.getRuntime();
        System.out.println("Ready to carry CMD!");
        try {
            r.exec("cmd /k calc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End to carry CMD!");
    }

    public static void callPing() {
        try {
            Process p = Runtime.getRuntime().exec("ping www.baidu.com");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
            String line = "";
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
//            OutputStreamWriter writer = new OutputStreamWriter(p.getOutputStream(), "UTF-8");
//            writer.write("这是我的输入到命令行的内容");
//            writer.flush();
//            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
