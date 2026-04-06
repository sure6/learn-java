package com.leesure;

public class MatrixTranspose {

    // 原地转置 3x3 矩阵
    public static void transposeInPlace(int[][] matrix) {
        // 检查数组是否为 3x3 (可选的安全检查)
        if (matrix == null || matrix.length != 3 || matrix[0].length != 3) {
            throw new IllegalArgumentException("输入必须是 3x3 的二维数组");
        }

        // 只需要遍历上三角部分 (j > i)，并与下三角部分交换
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // 辅助函数：打印矩阵
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("原始矩阵:");
        printMatrix(matrix);

        transposeInPlace(matrix);

        System.out.println("\n转置后的矩阵:");
        printMatrix(matrix);
    }
}
