package com.fun.leetcode;

public class Solution_303 {

    private int[] nums;

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        Solution_303 s = new Solution_303(nums);
        System.out.println(s.sumRange(0, 2));
        System.out.println(s.sumRange(2, 5));
        System.out.println(s.sumRange(0, 5));

        NumArray array = new NumArray(nums);
        System.out.println(array.sumRange(0, 2));
        System.out.println(array.sumRange(2, 5));
        System.out.println(array.sumRange(0, 5));

    }

    public Solution_303(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }


    /**
     * 官方解法
     */
    public static class NumArray {
        int[] sums;

        public NumArray(int[] nums) {
            int n = nums.length;
            sums = new int[n+1];
            for (int i = 0; i < n; i++) {
                sums[i+1] = sums[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sums[j+1] - sums[i];
        }
    }

}
