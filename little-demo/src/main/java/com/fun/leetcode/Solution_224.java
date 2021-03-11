package com.fun.leetcode;

import java.util.*;

/**
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class Solution_224 {

    public static void main(String[] args) {
        System.out.println(calculate(" 2-11 +2"));
        System.out.println(calculate(" -11 +2"));
        System.out.println(calculate(" 212"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));

        System.out.println(Integer.valueOf('0'));// 48
        System.out.println(Integer.valueOf('9'));
        System.out.println(Integer.valueOf('+'));// 43
    }

    public static int calculate(String s) {
        // 去掉空格
        s = s.replaceAll(" ", "");

        // 切割, 需要保留操作符
        List<String> arr = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '(' || s.charAt(i) == ')') {
                String pre = s.substring(start, i);
                if (!pre.isBlank()) {
                    arr.add(pre);
                }
                arr.add(String.valueOf(s.charAt(i)));
                start = i + 1;
            }
        }
        if (start != s.length()) {
            arr.add(s.substring(start));
        }


        if (arr.size() == 1) {
            return Integer.valueOf(arr.get(0));
        }

        Stack<String> rs = new Stack<>();
        rs.push(String.valueOf(arr.get(0)));
        for (int i = 1; i < arr.size(); i++) {
            processPre(rs, arr.get(i));
        }

        return Integer.valueOf(rs.peek());
    }

    private static void processPre(Stack<String> rs, String right) {
        if (rs.size() == 0) {
            rs.push(right);
            return;
        }

        String pre = rs.peek();
        // 当前是数字且前一个是运算符。进行计算
        if ((!right.equals("+") && !right.equals("-") && !right.equals("(") && !right.equals(")")) && (pre.equals("+") || pre.equals("-"))) {
            rs.pop();

            String left = rs.size() > 0 ? rs.pop() : "0";
            int leftInt = Integer.valueOf(left);
            int rightInt = Integer.valueOf(right);
            rightInt = pre.equals("+") ? leftInt + rightInt : leftInt - rightInt;

            // 用rightInt 往前判断，如果需要运行则运算，不需要则入栈
            processPre(rs, String.valueOf(rightInt));
        } else {
            if (right.equals(")")) { // 当前是 ')'
                int rightInt = Integer.valueOf(rs.pop());
                rs.pop(); // 去除 '('

                // 用rightInt 往前判断，如果需要运行则运算，不需要则入栈
                processPre(rs, String.valueOf(rightInt));
            } else { // 其他
                rs.push(right);
            }
        }
    }

    /**
     * 官方解法：括号展开 + 栈
     */
    public int calculate2(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

}
