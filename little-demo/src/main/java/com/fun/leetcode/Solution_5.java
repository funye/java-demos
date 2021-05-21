package com.fun.leetcode;

import java.util.Scanner;

public class Solution_5 {

    public static void main(String[] args) {
        Solution_5 s = new Solution_5();

        // "xaabacxcabaaxcabaax" --> "xaabacxcabaax"
        //
        // "aacabdkacaa" --> aca
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("请输入字符串，输入 q 结束：");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("===========结束测试！！！==========");
                System.exit(0);
            }
            System.out.print("计算结果：");
            System.out.println(s.longestPalindrome(input));
        }
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }


}
