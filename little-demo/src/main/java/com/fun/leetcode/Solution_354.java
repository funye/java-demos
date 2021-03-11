package com.fun.leetcode;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * <p>
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 示例1：
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * <p>
 * 示例 2：
 * * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_354 {

    public static void main(String[] args) {
        int[][] envelopes = new int[][]{
                {5, 4},
                {6, 5},
                {6, 7},
                {2, 3},
                {5, 4},
        };
        System.out.println(maxEnvelopes(envelopes));

        envelopes = new int[][]{
                {2, 100},
                {3, 200},
                {4, 300},
                {5, 500},
                {5, 400},
                {5, 250},
                {6, 370},
                {6, 360},
                {7, 380}
        };
        System.out.println(maxEnvelopes(envelopes));

        envelopes = new int[][]{
                {4, 5},
                {4, 6},
                {6, 7},
                {2, 3},
                {1, 1},
        };
        System.out.println(maxEnvelopes(envelopes));
    }

    public static int maxEnvelopes(int[][] envelopes) {

        /* 第一步. 先按照Wi(envelopes[i][0]) 排序，Wi相等时候按照Hi(envelopes[i][1])排序
                {5,4}      {2,3},
                {6,5}, ==> {5,4},
                {6,7},     {5,4},
                {2,3}, ==> {6,5},
                {5,4},     {6,7}
           第二步. Hi(envelopes[i][1])中寻找最大递增串长度即可(可间隔)
                如上结果有Hi递增串有[3,6] [3,4,7],[4,7] 则最大长度为3
         */

        // 插入排序
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = i; j > 0; j--) {
                if (envelopes[j][0] < envelopes[j - 1][0]) { // 需要寻找插入位置并插入
                    int tmp = envelopes[j - 1][0];
                    envelopes[j - 1][0] = envelopes[j][0];
                    envelopes[j][0] = tmp;

                    tmp = envelopes[j - 1][1];
                    envelopes[j - 1][1] = envelopes[j][1];
                    envelopes[j][1] = tmp;
                }
            }
        }

        // 找(envelopes[i][1])中寻找最大递增子串长度
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1] && envelopes[i][0]>envelopes[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;

    }


}
