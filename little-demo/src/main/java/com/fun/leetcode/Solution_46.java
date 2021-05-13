package com.fun.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution_46 {

    public static void main(String[] args) {
        Solution_46 s = new Solution_46();

        int[] nums = new int[]{1,2,3};
        System.out.println(s.permute(nums));

    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>(nums.length);
        track(nums, new LinkedList<>(), result);
        return result;
    }

    /**
     * 核心算法思想是利用回溯算法（DFS） 不断查找可能的解
     */
    private void track(int[] nums, LinkedList<Integer> selected, List<List<Integer>> result) {

        // 找到一组解
        if (selected.size() == nums.length) {
            result.add(new ArrayList<>(selected));
            return;
        }

        // 尝试不同的根节点
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选项
            if (selected.contains(nums[i])) {
                continue;
            }
            // 尝试选择
            selected.addLast(nums[i]);
            // 深入下一层
            track(nums, selected, result);
            // 回溯
            selected.removeLast();
        }
    }


}
