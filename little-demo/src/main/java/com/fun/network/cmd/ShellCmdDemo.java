package com.fun.network.cmd;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
public class ShellCmdDemo {

    private Session session;

    /**
     * 初始化
     *
     * @param ip       远程Linux地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @throws JSchException JSch异常
     */
    public ShellCmdDemo(String ip, Integer port, String username, String password){
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, ip, port);
            session = jsch.getSession(username, ip, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect(60 * 1000);
            log.info("Session connected!");
        } catch (Exception e) {
            log.error("get session error");
        }
    }

    /**
     * 执行一条命令
     */
    public String execCmd(String command) throws Exception {
        if (session == null) {
            throw new Exception("session is null");
        }
        log.info("execCmd command - > {}", command);

        Channel channel = null;
        BufferedReader reader = null;
        PrintWriter printWriter = null;

        StringBuilder sb = new StringBuilder(16);
        try {
            channel = session.openChannel("shell");
            channel.connect();
            reader = new BufferedReader(new InputStreamReader(channel.getInputStream(), StandardCharsets.UTF_8));

            printWriter = new PrintWriter(channel.getOutputStream());
            printWriter.println(command);
            printWriter.println("exit");
            printWriter.flush();

            String buffer;
            while ((buffer = reader.readLine()) != null) {
                sb.append("\n").append(buffer);
            }
            log.info("execCmd result - > {}", sb);
            return sb.toString();
        } catch (Exception e) {
            log.error("COMMAND EXECUTE ERROR", e);
        } finally {
            reader.close();
            printWriter.close();
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
        }
        return "COMMAND EXECUTE ERROR";
    }



    /**
     * 执行一条命令
     */
    public String execCmdForOne(String command) throws Exception {
        if (session == null) {
            throw new Exception("session is null");
        }
        log.info("execCmd command - > {}", command);

        ChannelExec channel = null;
        BufferedReader reader = null;

        StringBuilder sb = new StringBuilder(16);
        try {
            channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand(command);
            channel.connect();
            reader = new BufferedReader(new InputStreamReader(channel.getInputStream(), StandardCharsets.UTF_8));

            String buffer;
            while ((buffer = reader.readLine()) != null) {
                sb.append("\n").append(buffer);
            }
            log.info("execCmd result - > {}", sb);
            return sb.toString();
        } catch (Exception e) {
            log.error("COMMAND EXECUTE ERROR", e);
        } finally {
            reader.close();
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
        }
        return "COMMAND EXECUTE ERROR";
    }

    /**
     * 释放资源
     *
     * @date 2019/3/15 12:47
     */
    private void close() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public static void main(String[] args) throws Exception {

        ShellCmdDemo cmd = new ShellCmdDemo("120.77.179.58", 22, "root", "Fun@2020");

        cmd.execCmd("ls");
        cmd.execCmd("date");
        System.out.println("============================");
        cmd.execCmdForOne("pwd");
        cmd.execCmdForOne("cd ./html");
        cmd.execCmdForOne("pwd");

        cmd.close();
    }

}
