package com.fun.websocket;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileChangeListener extends FileAlterationListenerAdaptor {
    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);

    }

    @Override
    public void onDirectoryCreate(File directory) {
        super.onDirectoryCreate(directory);
    }

    @Override
    public void onDirectoryChange(File directory) {
        super.onDirectoryChange(directory);
    }

    @Override
    public void onDirectoryDelete(File directory) {
        super.onDirectoryDelete(directory);
    }

    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
        System.out.println("文件： " + file.getName() + " 被创建了");
    }

    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        System.out.println("config文件： " + file.getName() + " 被修改了");
        server.broadcast("文件： " + file.getName() + " 被修改了");
    }

    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
        System.out.println("文件： " + file.getName() + " 被删除了");
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

    private TestWebSocketServer server;

    public FileChangeListener(TestWebSocketServer server) {
        this.server = server;
    }

}
