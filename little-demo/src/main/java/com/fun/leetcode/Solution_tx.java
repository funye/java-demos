package com.fun.leetcode;

import org.apache.commons.lang.StringUtils;

/**
 * M 进制的字符串转N进制字符串
 * M>=2, N<=16
 */
public class Solution_tx {

    public static void main(String[] args) {

        String result = convertToNString("31", 10, 16);
        System.out.println(result);

    }


    public static String convertToNString(String input, int m, int n) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        // m进制转换成10进制
        int value = 0;
        int power = 0;
        for (int i=input.length()-1; i>=0; i--) {
            value += Integer.parseInt(input.charAt(i)+"")*Math.pow(m, power);
            power++;
        }

        System.out.println(value);
        String result = "";
        // 10进制转换成n进制
        while(value != 0) {
            int tmp = value % n;
            result = convertNumToString(tmp) + result;
            value = value/n;
        }
        return result;
    }

    private static String convertNumToString (int num) {
        if (0<=num && num<=9) {
            return num+"";
        } else {
            char a = 'A';
            return (char)(a + num - 10) + "";
        }
    }
}
