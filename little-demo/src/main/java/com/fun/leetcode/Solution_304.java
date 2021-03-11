package com.fun.leetcode;

public class Solution_304 {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix obj = new NumMatrix(matrix);
        System.out.println(obj.sumRegion(2, 1, 4, 3));
        System.out.println(obj.sumRegion(1, 1, 2, 2));
        System.out.println(obj.sumRegion(1, 2, 2, 4));
    }

    /**
     * 前缀法
     *
     */
    public static class NumMatrix {

        // 左上角为(0,0), 右下角为(i,j) 的sum值
        int[][] sumMatrix;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                this.sumMatrix = new int[m+1][n+1]; // 空一个（0，0）处理边界
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        this.sumMatrix[i+1][j+1] = sumMatrix[i][j+1] + sumMatrix[i+1][j] - sumMatrix[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumMatrix[row2+1][col2+1] - sumMatrix[row1][col2+1] - sumMatrix[row2+1][col1] + sumMatrix[row1][col1];
        }

    }

}
