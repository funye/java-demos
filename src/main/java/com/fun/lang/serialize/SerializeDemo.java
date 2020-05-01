package com.fun.lang.serialize;

import java.io.*;

public class SerializeDemo implements java.io.Serializable{

    private String name;

    private InnerSerialize inner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InnerSerialize getInner() {
        return inner;
    }

    public void setInner(InnerSerialize inner) {
        this.inner = inner;
    }




    static class InnerSerialize implements java.io.Serializable { // 去掉序列化 查看效果
        private String code;

        public InnerSerialize(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
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
        System.out.println(demo.getName()+" : "+ demo.getInner().getCode());

    }
}
