package com.fun.algorithm;

/**
 * 短连接生成方案
 */
public class ShortLink {

    public static final String CODE_BOOK = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        ShortLink sl = new ShortLink();

        long id = System.currentTimeMillis()/100;
        System.out.println("id=" + id);
        String link = sl.encodeBase62(id);
        System.out.println("link=" + link);
        System.out.println("decode id=" + sl.decodeBase62(link));

    }

    public String encodeBase62(long id) {
        // [0,9] [a,z] [A,Z] 共62个字符，因此使用62进制
        if (id <= 0) {
            return "0";
        }
        StringBuffer ret = new StringBuffer();
        do{
            int remainder =(int) (id % 62);
            ret.insert(0, CODE_BOOK.charAt(remainder));
            id = id/62;
        }while (id > 0);

        return ret.toString();
    }

    public long decodeBase62(String link) {
        // 123 = 1*62^(3-1) + 2*62^(3-2) + 3*62^(3-3)
        // 2 = 2*62^(1-1)
        if (link.length() < 1) {
            return -1;
        }
        long ret = 0;
        for (int i = 0; i < link.length(); i++) {
            ret += CODE_BOOK.indexOf(link.charAt(i)) * Math.pow(62, link.length()-1-i);
        }
        return ret;
    }
}
