package com.fun.leetcode;

public class Solution_1052 {

    public static void main(String[] args) {

        int[] customers = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;

        Solution_1052 s = new Solution_1052();
        System.out.println(s.maxSatisfied(customers, grumpy, X));
    }

    /**
     * <pre>
     * 1 <= X <=customers.length ==grumpy.length <= 20000
     * 0 <=customers[i] <= 1000
     * 0 <=grumpy[i] <= 1
     * </pre>
     *
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * </p>
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // 暂时不做输入校验
        int maxWindStart = 0;
        int sum = 0;
        // 计算初始窗口从0开始的sum
        for (int j = 0; j < customers.length; j++) {
            if (j > 0 + X - 1 && grumpy[j] == 0) { // 不连续部分
                sum += customers[j];
            }
            if (j <= 0 + X - 1) { // 连续部分
                sum += customers[j];
            }
        }

        int max = sum;
        // 窗口右移，释放一个左边[i-1]，获取一个右边[i+X-1]，计算sum的变化
        for (int i = 1; i <= customers.length - X; i++) {
            if (grumpy[i-1] == 1) { // 被释放的1
                sum -= customers[i-1];
            }
            if (grumpy[i+X-1] == 1) { // 新加入窗口的为1
                sum += customers[i+X-1];
            }
            if (sum > max) {
                max = sum;
                maxWindStart = i;
            }
        }

//        System.out.println("max index:" + maxWindStart);
//        System.out.println("max sum:" + max);

        return max;
    }

    /**
     * 时间复杂的n^2
     *
     */
    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        // 暂时不做输入校验
        int max = 0;
        int maxWindStart = 0;

        // 使用滑动窗口, 窗口大小为x，异常次向后滑动 计算max
        for (int i = 0; i <= customers.length - X; i++) {

            int sum = 0;
            for (int j = 0; j < customers.length; j++) {
                if ((j < i || j > i + X - 1) && grumpy[j] == 0) { // 不连续部分
                    sum += customers[j];
                }
                if (i <= j && j <= i + X - 1) { // 连续部分
                    sum += customers[j];
                }
            }

            if (sum > max) {
                max = sum;
                maxWindStart = i;
            }

        }
        return max;
    }


}
