package com.fun.leetcode;

import java.util.*;

public class Solution_51 {

    public static void main(String[] args) {
        Solution_51 s = new Solution_51();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入皇后数 N (输入0结束)：");
        int n = scanner.nextInt();
        while (n != 0) {
            // solveNQueens
            System.out.println(s.solveNQueens(n));
            System.out.println("请输入皇后数 N (输入0结束)：");
            n = scanner.nextInt();
        }

        System.out.println("结束测试！！！");
    }

    public List<List<String>> solveNQueens(int n) {
        // 初始化
        List<List<String>> result = new ArrayList<>();
        backTrack(n, new LinkedList<>(), result, 0);

        return result;
    }

    private void backTrack(int n, LinkedList<Integer> selected, List<List<String>> result, int y) {
        if (selected.size() == n) {
            List<String> one = new ArrayList<>();
            for (Integer p : selected) {
                String s = "";
                for (int i = 0; i < n; i++) {
                    if (i == p) {
                        s = s + "Q";
                    } else {
                        s = s + ".";
                    }

                }
                one.add(s);
            }
            result.add(one);
            return;
        }
        if (y == n) {
            return;
        }

        for (int x = 0; x < n; x++) {
            if (!canSet(selected, x, y)) {
                continue;
            }
            selected.addLast(x);
            backTrack(n, selected, result, ++y);
            selected.removeLast();
        }
    }

    private boolean canSet(LinkedList<Integer> selected, int x, int y) {
        for (int i = 0; i < selected.size(); i++) {
            // x 差值绝对值=y差值绝对值 或者X相等
            int sX = selected.get(i);
            int sY = i;
            if (sX == x || Math.abs(x-sX) == Math.abs(y-sY)) {
                return false;
            }
        }
        return true;
    }

}
