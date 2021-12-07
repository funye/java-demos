package com.fun.lang.file;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileRWDemo {

    public static void main(String[] args) throws IOException {
        String file = "D:\\Users\\80279309\\Documents\\fun\\test\\test-read.txt";
//        write(file);
//        read(file);
//        randomRead(file);
        bufferedRandomRead(file);
    }

    public static void write(String file) throws IOException {

        File f = new File(file);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(f);

        String name = "fun-ye";
        out.write(name.getBytes(StandardCharsets.UTF_8));
        out.close();
    }

    public static void read(String file) throws IOException {

        File f = new File(file);
        FileInputStream in = new FileInputStream(f);
        byte[] b = new byte[4];
        in.read(b, 1, 3);
        System.out.println(new String(b));
        in.close();
    }


    public static void randomWrite(String file) throws IOException {

        File f = new File(file);
        if (!f.exists()) {
            f.createNewFile();
        }
        RandomAccessFile raf = new RandomAccessFile(f, "rw");

        String name = "fun-ye";
        raf.write(name.getBytes(StandardCharsets.UTF_8));
        raf.close();
    }

    public static void randomRead(String file) throws IOException {

        File f = new File(file);
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        byte[] b = new byte[4];
        raf.seek(4);
        raf.read(b, 0, 4);
        System.out.println(new String(b));
        System.out.println(raf.getFilePointer());

        byte[] b1 = new byte[4];
        raf.seek(0);
        raf.read(b1, 0, 4);
        System.out.println(new String(b1));
        System.out.println(raf.getFilePointer());

        System.out.println("========skipBytes =======");
        byte[] b2 = new byte[4];
        raf.seek(0);
        raf.skipBytes(4);
        System.out.println(raf.getFilePointer());
        raf.read(b2, 0, 4);
        System.out.println(new String(b2));
        System.out.println(raf.getFilePointer());
        raf.close();
    }

    public static void bufferedRandomRead(String file) throws IOException {

        File f = new File(file);
//        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        FileInputStream raf = new FileInputStream(f);

        // 文件过大的时候可以通过map()的position参数设置不同的位置开始映射
        MappedByteBuffer mbin = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, raf.getChannel().size());
        // 设置开始读取的位子
        mbin.position(2);
        byte[] b2 = new byte[4];
        mbin.get(b2, 0, 4);
        System.out.println(new String(b2));
        raf.close();
    }

}
