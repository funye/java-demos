package com.fun.lang.file;

import java.io.*;

/**
 * Date: 2021/5/10 5:02 PM
 */
public class FileOperationDemo {

    public static void main(String[] args) {


    }

    /**
     * 使用FileWriter类写文本文件,支持追加
     */
    public static void writeMethod1(String fileName, boolean append) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write("Hello Kuka:\n");
            writer.write("  My name is coolszy!\n");
            writer.write("  I like you and miss you。");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //注意：上面的例子由于写入的文本很少，使用FileWrite类就可以了。但如果需要写入的
    //内容很多，就应该使用更为高效的缓冲器流类BufferedWriter。

    /**
     * 使用BufferedWriter类写文本文件
     */
    public static void writeMethod2() {
        String fileName = "C:/kuka.txt";
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            out.write("Hello Kuka:");
            out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
            out.write("  My name is coolszy!\n");
            out.write("  I like you and miss you。");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeMethod3(String fileName) {
        // 构建指定文件
        File file = new File(fileName);
        // 根据文件创建文件的输出流
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            String str = "我是中国人";
            // 把内容转换成字节数组
            byte[] data = str.getBytes();
            // 向文件写入内容
            os.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     * 使用FileReader类读文本文件
     */
    public static void readMethod1(String fileName) {
        int c = 0;
        try {
            FileReader reader = new FileReader(fileName);
            c = reader.read();
            while (c != -1) {
                System.out.print((char) c);
                c = reader.read();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用BufferedReader类读文本文件
     */
    public static void readMethod2(String fileName) {
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMethod3(String fileName) {
        // 构建指定文件
        File file = new File(fileName);
        InputStream is = null;
        try {
            // 根据文件创建文件的输入流
            is = new FileInputStream(file);
            // 创建字节数组
            byte[] data = new byte[1024];
            // 读取内容，放到字节数组里面
            is.read(data);
            System.out.println("文件内容:" + new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输入流
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
