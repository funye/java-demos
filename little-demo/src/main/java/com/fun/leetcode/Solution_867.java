package com.fun.leetcode;

public class Solution_867 {
    public static void main(String[] args) {

        Solution_867 s = new Solution_867();

        int[][] matrix = new int[][]{
                new int[]{1, 3, 5},
                new int[]{2, 4, 6}
        };
        System.out.println("=======原始数据：");
        s.print(matrix);
        int[][] result = s.transpose1(matrix);
        System.out.println("=======翻转结果：");
        s.print(result);


        int[][] matrix1 = new int[][]{
                new int[]{1, 2},
                new int[]{3, 4},
                new int[]{5, 6}
        };

        System.out.println("=======原始数据：");
        s.print(matrix1);
        int[][] result1 = s.transpose1(matrix1);
        System.out.println("=======翻转结果：");
        s.print(result1);

    }

    /**
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/transpose-matrix/
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] result[] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public int[][] transpose1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int max = m > n ? m : n;
        int min = m > n ? n : m;

        int[] result[] = new int[n][m];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j <= i && j < min; j++) {
                // result[j][i] 赋值
                if (i < min) {
                    result[i][j] = matrix[j][i];
                    result[j][i] = matrix[i][j];
                } else {
                    if (i < m) {
                        result[j][i] = matrix[i][j];
                    } else {
                        result[i][j] = matrix[j][i];
                    }
                }

            }
        }

        return result;
    }


    private void print(int[][] arrs) {
        if (arrs == null) {
            System.out.println("array is null");
        }
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                System.out.print(arrs[i][j] + " ");
            }
            System.out.println();
        }
    }
}
