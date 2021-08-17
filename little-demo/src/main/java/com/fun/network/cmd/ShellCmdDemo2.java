package com.fun.network.cmd;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Calendar;

@Slf4j
public class ShellCmdDemo2 {

    private static Session session;
    private static Connection conn;

    private static String DEFAULT_CHAR_SET = "UTF-8";
    private static String tipStr = "=======================%s=======================";
    private static String splitStr = "=====================================================";

    /**
     * 登录主机
     *
     * @return 登录成功返回true，否则返回false
     */
    public void init(String ip, int port, String userName, String password) {
        boolean isAuthenticated;
        long startTime = Calendar.getInstance().getTimeInMillis();
        try {
            conn = new Connection(ip, port);
            conn.connect(); // 连接主机

            isAuthenticated = conn.authenticateWithPassword(userName, password); // 认证
            if (isAuthenticated) {
                System.out.println(String.format(tipStr, "认证成功"));
            } else {
                System.out.println(String.format(tipStr, "认证失败"));
            }
        } catch (IOException e) {
            System.err.println(String.format(tipStr, "登录失败"));
            e.printStackTrace();
        }
        long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("登录用时: " + (endTime - startTime) / 1000.0 + "s\n" + splitStr);
    }

    /**
     * 远程执行shell脚本或者命令
     *
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     */
    public String execute(String cmd) {
        String result = "";
        try {
            session = conn.openSession();
            session.execCommand(cmd);      // 执行命令
//            session.startShell();
            result = processStdout(session.getStdout(), DEFAULT_CHAR_SET);

            //如果为得到标准输出为空，说明脚本执行出错了
            if (StringUtils.isBlank(result)) {
                result = processStdout(session.getStderr(), DEFAULT_CHAR_SET);
            }
            System.out.println("【执行命令】执行的命令如下：\n" + cmd);
            System.out.println("【执行命令】执行的结果如下：\n" + result);

        } catch (IOException e) {
            System.err.println("【执行命令失败】\n执行的命令如下：\n" + cmd + "\n" + e.getMessage());
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     */
    private String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }

    private void close() {
        if (session != null) {
            session.close();
        }

        if (conn != null) {
            conn.close();
        }
    }



    public static void main(String[] args) throws Exception {

        ShellCmdDemo2 ssh = new ShellCmdDemo2();
        ssh.init("120.77.179.58", 22, "root", "Fun@2020");
        ssh.execute("ls");
        ssh.execute("pwd");
        ssh.close();
    }

}
