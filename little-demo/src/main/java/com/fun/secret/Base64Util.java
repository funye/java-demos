package com.fun.secret;

import java.util.Base64;

/**
 * base64算法工具
 *
 * @author huanye
 * @date: 2017/7/21 下午4:36
 */
public class Base64Util {

    /**
     * BASE64解密
     *
     * @param key
     * @throws Exception
     */
    public static String decryptBASE64(String key) throws Exception {
        return new String(Base64.getDecoder().decode(key));
    }

    /**
     * BASE64加密
     *
     * @param key
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return Base64.getEncoder().encodeToString(key.getBytes("UTF-8"));
    }


    public static void main(String[] args) {

        String msg = "Man";
        try {
            String enMsg = encryptBASE64(msg);
            System.out.println("encrypt with base64: " + enMsg);
            String deMsg = new String(decryptBASE64(enMsg));
            System.out.println("decrypt with base64: " + deMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
