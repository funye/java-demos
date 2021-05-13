package com.fun.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution_77 {

    public static void main(String[] args) {
        Solution_77 s = new Solution_77();
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("请输入整数n, k，输入 q 结束：");
            String numsArray = scanner.nextLine().trim();
            if (numsArray.equalsIgnoreCase("q")) {
                System.out.println("===========结束测试！！！==========");
                System.exit(0);
            }
            String[] numsInput = numsArray.split(" ");
            int n = Integer.parseInt(numsInput[0].trim());
            int k = Integer.parseInt(numsInput[1].trim());

            System.out.print("计算结果：");
            System.out.println(s.combine(n, k));
        }
    }


    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(n, k, new LinkedList<>(), 0, result);
        return result;
    }

    // 选择K个数字的集合
    private void backtrack(int n, int k, LinkedList<Integer> selected, int lastIndex, List<List<Integer>> result) {

        if (selected.size() == k) {
            List<Integer> tmp = new ArrayList<>(selected);
            result.add(tmp);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (selected.contains(i) || i <= lastIndex) {
                continue;
            }

            selected.addLast(i);

            backtrack(n, k, selected, i, result);

            selected.removeLast();
        }

    }

}
