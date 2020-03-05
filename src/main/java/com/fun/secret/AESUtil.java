package com.fun.secret;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * 对称加密算法 AES
 *
 * @author huanye
 * @date: 2017/7/21 下午5:35
 */
public class AESUtil {

    public static final String sKey="mljr#yyfq@2017!$";
    /**
     * AES加密
     *
     * @param sSrc 待加密字符串
     * @param sKey 秘钥字符串，秘钥长度需要为16
     * @return 加密之后的字符串，加密过程出错，返回null
     */
    public static String encrypt(String sSrc, String sKey) {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }

        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }

        try {
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param sSrc 待解密字符串
     * @param sKey 秘钥，长度为16
     * @return 解密后的字符串，解密过程出错，返回null
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }

            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }

            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);// 先用base64解密

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws Exception {

        // 身份证@openid
        String msg = "420982199111112345@oSYU1wpkP4JkVAQsXliBrMB3uc9I";

        System.out.println("before encrypt: " + msg);
        String afterEncrypt = encrypt(msg, sKey);
        System.out.println("after encrypt: " + afterEncrypt);
        System.out.println("after decrypt: " + decrypt(afterEncrypt, sKey));

    }

}
