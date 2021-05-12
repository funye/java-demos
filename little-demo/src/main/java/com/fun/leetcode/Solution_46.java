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

    private void track(int[] nums, LinkedList<Integer> selected, List<List<Integer>> result) {

        if (selected.size() == nums.length) {
            result.add(new ArrayList<>(selected));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (selected.contains(nums[i])) {
                continue;
            }
            selected.addLast(nums[i]);
            track(nums, selected, result);
            selected.removeLast();
        }
    }


}
