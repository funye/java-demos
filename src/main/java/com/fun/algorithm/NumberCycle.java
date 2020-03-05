package com.fun.algorithm;

/**
 * Created by Administrator
 *
 * @date 2017/4/27.
 */
public class NumberCycle {


    public static void main(String[] args) {

        int n = 7;

        int[][] arr = new int[n][n];

        initNumberCycle(n,arr);

        printArr(arr);
    }

    public static void initNumberCycle(int n,int[][] arr) {
        /**
         *  16 15 14 13
         *  05 04 03 12
         *  06 01 02 11
         *  07 08 09 10
         *
         */

        /**
         *  9 8 7
         *  2 1 6
         *  3 4 5
         *
         */

        int max = n*n;
        int i = 0,j=0;
        boolean isChangeJ = true; // 当前是否对j操作
        int virtualEndIndex = n; // i、j 的最大值+1
        int virtualStartIndex = 0; // i、j 的最小值

        while (max > 0 ) {

            arr[i][j] = max--;

            if (isChangeJ) { // operate j
                if (i == virtualStartIndex) { // left-->right
                    j++;
                } else { // right-->left
                    j--;
                }

                if (j > virtualEndIndex-1) { // 切换成操作 i
                    j=virtualEndIndex-1;
                    isChangeJ = false;
                    i++; // 切换成操作 i, top-->bottom,i需要向下移动一格
                }
                if(j < virtualStartIndex) { // 切换成操作 i
                    j = virtualStartIndex;
                    isChangeJ = false;
                    i--;// 切换成操作 i, bottom-->top,i 需要向上移动一格
                }

            } else {
                if (j == virtualEndIndex-1) { // top-->bottom
                    i++;
                } else { // bottom-->top
                    i--;
                }
                if (i > virtualEndIndex-1) { // 切换成操作 j
                    i = virtualEndIndex-1;
                    isChangeJ = true;
                    j--;// 切换成操作 j，right-->left,j需要向左移动一格
                }
                if (i < virtualStartIndex) { // 切换成操作 j
                    i = virtualStartIndex;
                    isChangeJ = true;
                    j++;// 切换成操作 j，left-->right,j需要向右移动一格
                }
            }

            if (i == virtualStartIndex+1 && j == virtualStartIndex) { // 二维数组一圈的值填充完成
                virtualStartIndex++;
                virtualEndIndex--;
            }
        }

    }

    public static void printArr(int[][] arr) {
        for (int[] arr1 : arr ) {
            for (int a: arr1) {
                System.out.print(a+"\t");
            }
            System.out.println();
        }
    }




}
