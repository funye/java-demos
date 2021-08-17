package com.fun.network.cmd;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShellCmdDemo3 {

    private static String DEFAULT_CHAR_SET = "UTF-8";
    private static String tipStr = "=======================%s=======================";
    private static String splitStr = "=====================================================";

    public static void main(String[] args) {
        try {

            Connection conn = new Connection("120.77.179.58", 22);
            conn.connect(); // 连接主机

            boolean isAuthenticated = conn.authenticateWithPassword("root", "Fun@2020"); // 认证
            if (isAuthenticated) {
                System.out.println(String.format(tipStr, "认证成功"));
            } else {
                System.out.println(String.format(tipStr, "认证失败"));
            }

            Session session = conn.openSession();
            session.requestPTY("bash");
            session.startShell();

            InputStream stdout = new StreamGobbler(session.getStdout());
            InputStream stderr = new StreamGobbler(session.getStderr());

            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));


        } catch (Exception e) {

        } finally {

        }
    }
}
