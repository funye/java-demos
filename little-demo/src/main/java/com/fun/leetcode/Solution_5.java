package com.fun.leetcode;

import java.util.Scanner;

public class Solution_5 {

    public static void main(String[] args) {
        Solution_5 s = new Solution_5();

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
        // todo-yh: 双指针游走，判断 p1 p2 何时移动

        return null;
    }


}
