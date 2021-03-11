package com.fun.leetcode;

public class Solution_338 {

    public static void main(String[] args) {
        int[] b = countBits2(127);
        print(b);

        System.out.println();
    }

    /**
     * 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     * 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     *
     * 作者：duadua
     * 链接：https://leetcode-cn.com/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int[] countBits2(int num) {

        int[] result = new int[num + 1];
        result[0] = 0;

        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                result[i] = result[i - 1] + 1;
            } else {
                result[i] = result[i / 2];
            }
        }

        return result;
    }

    public static int[] countBits(int num) {

        int[] rs = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            rs[i] = countOnes(i);

        }
        return rs;
    }

    private static int countOnes(int i) {
        int result = 0;
        String ones = Integer.toBinaryString(i);
        for (int j = 0; j < ones.length(); j++) {
            if (ones.charAt(j) == '1') result++;
        }
        return result;
    }

    private static void print(int[] a) {
        if (a == null) return;

        for (int i : a) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
