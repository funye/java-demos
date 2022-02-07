package com.fun.lang.serialize;

import lombok.Data;

import java.io.*;

@Data
public class SerializeDemo implements Serializable{

//    private static final long serialVersionUID = 2L;

    private String name;

    private static Integer age = 30; // 先执行一次序列化，然后改变这个值 反序列化，发现打印为修改后的值，说明这个值没有被存储在序列化流里面

    private InnerSerialize inner;

    @Data
    static class InnerSerialize implements Serializable { // 去掉序列化 查看效果
        private String code;

        public InnerSerialize(String code) {
            this.code = code;
        }
    }

    public SerializeDemo(String name, InnerSerialize inner) {
        this.name = name;
        this.inner = inner;
    }

    public static void main(String[] args) throws Exception {

//        serialize();
        deSerialize();

    }

    public static void serialize() throws Exception{
        InnerSerialize inner = new InnerSerialize("code inner");
        SerializeDemo demo = new SerializeDemo("serialize", inner);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                new File("D:/serialize.txt")));
        oos.writeObject(demo);
        System.out.println("SerializeDemo对象序列化成功！");
        oos.close();
    }

    public static void deSerialize() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("D:/serialize.txt")));
        SerializeDemo demo = (SerializeDemo) ois.readObject();
        System.out.println("SerializeDemo对象反序列化成功！");;
        System.out.println(demo.getName()+" : "+ demo.getInner().getCode() + ", age=" + SerializeDemo.age);

    }
}
