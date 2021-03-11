package com.fun.leetcode;


import java.util.Arrays;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 */
public class Solution_503 {
    public static void main(String[] args) {

        int[] nums = new int[]{1,2};

        int[] rs = nextGreaterElements2(nums);

        for (int i : rs) {
            System.out.print(i + " ");
        }
        System.out.println();

    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] rs = new int[nums.length];
        if (nums.length == 1) {
            rs[0] = -1;
            return rs;
        }

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            j = j == nums.length ? 0 : j;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    rs[i] = nums[j];
                    break;
                } else {
                    j++;
                    j = j == nums.length ? 0 : j;
                }
            }
            if (j == i) {
                rs[i] = -1;
            }
        }
        return rs;
    }

    // 单调栈解法 + 循环数组
    // 本题中可以直接使用取模来代替循环数组操作
    // 算法思想: 如果元素是单调减的, 那么这些元素存在着相同的下一个最大元素
    // 例如 [5,4,3,1,6], 其中 [5,4,3,1]存在相同的下一个最大元素6
    public static int[] nextGreaterElements2(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // 创建一个单调递减栈, 但是栈中存储的是下标
        int[] stack = new int[10010];
        int tt = -1;

        for (int i = 0; i < 2 * n; i++) {

            // 维持单调栈性质, 若当前元素大于栈顶元素, 则不断进行出栈
            while (tt != -1 && nums[i % n] > nums[stack[tt]]) {
                result[stack[tt--]] = nums[i % n];
            }

            // 若当前元素小于栈顶元素, 则下标元素进栈
            stack[++tt] = i % n;

        }

        return result;
    }
}
