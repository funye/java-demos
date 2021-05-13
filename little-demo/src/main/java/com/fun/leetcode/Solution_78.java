package com.fun.leetcode;

import java.util.*;

public class Solution_78 {

    public static void main(String[] args) {
        Solution_78 s = new Solution_78();
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("请输入测试数组nums，输入 q 结束：");
            String numsArray = scanner.nextLine().trim();
            if (numsArray.equalsIgnoreCase("q")) {
                System.out.println("===========结束测试！！！==========");
                System.exit(0);
            }
            String[] numsInput = numsArray.split(" ");
            int[] nums = new int[numsInput.length];
            for (int i = 0; i < numsInput.length; i++) {
                nums[i] = Integer.parseInt(numsInput[i]);
            }

            System.out.print("计算结果：");
            System.out.println(s.subsets(nums));
        }
    }


    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            backtrack(nums, i, new LinkedList<>(), -1, result);
        }
        return result;
    }

    // 选择K个数字的集合
    private void backtrack(int[] nums, int k, LinkedList<Integer> selected, int lastIndex, List<List<Integer>> result) {

        if (selected.size() == k) {
            List<Integer> tmp = new ArrayList<>(selected);
            result.add(tmp);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (selected.contains(nums[i]) || i <= lastIndex) {
                continue;
            }

            selected.addLast(nums[i]);

            backtrack(nums, k, selected, i, result);

            selected.removeLast();
        }

    }

}
