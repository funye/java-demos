package com.fun.leetcode;

import java.util.Stack;

/**
 * Date: 2021/5/11 9:33 PM
 */
public class Solution_20 {

    public static void main(String[] args) {

        Solution_20 s = new Solution_20();
        System.out.println(s.isValid("()[]{}"));
        System.out.println(s.isValid("()[{[}"));
        System.out.println(s.isValid("{[]}"));

    }

    public boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }

        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        int pushCount = 0, popCount = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if ('(' == c || '[' == c || '{' == c) {
                stack.push(c);
                pushCount++;
            } else {
                if (stack.empty()) {
                    return false;
                }
                Character pre = stack.pop();
                popCount++;
                if (pre == '(' && c == ')' || pre == '[' && c == ']' || pre == '{' && c == '}') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return pushCount == popCount;
    }
}
