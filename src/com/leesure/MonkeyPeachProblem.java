package com.leesure;

/**
 * 这是一个经典的逆向思维数学问题。我们可以通过倒推法来解决：
 * 第 N 天：剩下 1 个桃子。
 * 第 N-1 天：剩下的桃子数 = (第 N 天的桃子数 + 1) * 2
 * 第 N-2 天：剩下的桃子数 = (第 N-1 天的桃子数 + 1) * 2
 * ...
 * 第 1 天：剩下的桃子数 = (第 2 天的桃子数 + 1) * 2
 * 通项公式为：𝑥_𝑖=(𝑥_(𝑖+1)+1)×2，其中𝑥_𝑁=1。
 *
 * N 1 N-1 4 N-2 10 N-3 22   N-1
 */

public class MonkeyPeachProblem {

    public static void main(String[] args) {
        int n = 10; // 假设第10天早上只剩1个桃子，你可以修改这个值

        System.out.println("=== 猴子吃桃问题 ===");
        System.out.println("到第 " + n + " 天早上时，只剩下一个桃子。");

        // 方法一：循环迭代（推荐，效率高）
        int totalByLoop = calculatePeachesLoop(n);
        System.out.println("【循环法】第一天共摘了: " + totalByLoop + " 个桃子");

        // 方法二：递归实现
        int totalByRecursion = calculatePeachesRecursive(n);
        System.out.println("【递归法】第一天共摘了: " + totalByRecursion + " 个桃子");
    }

    /**
     * 方法一：循环迭代法
     * 从第 N 天倒推到第 1 天
     * @param day 第几天早上只剩1个桃子
     * @return 第一天摘的桃子总数
     */
    public static int calculatePeachesLoop(int day) {
        int peaches = 1; // 第 day 天剩下的桃子数

        // 从第 day-1 天开始倒推，直到第 1 天
        for (int i = day - 1; i >= 1; i--) {
            peaches = (peaches + 1) * 2;
            // 可选：打印每天早上的桃子数
            // System.out.println("第 " + i + " 天早上有: " + peaches + " 个桃子");
        }

        return peaches;
    }

    /**
     * 方法二：递归法
     * f(n) = 1, 当 n == targetDay
     * f(n) = (f(n+1) + 1) * 2, 当 n < targetDay
     *
     * @param currentDay 当前计算的天数
     * @param targetDay 目标天数（即只剩1个桃子的那天）
     * @return 当前天数拥有的桃子数
     */
    public static int calculatePeachesRecursive(int targetDay) {
        return recursiveHelper(1, targetDay);
    }

    private static int recursiveHelper(int currentDay, int targetDay) {
        // 基准情况：到了第 targetDay 天，只剩 1 个
        if (currentDay == targetDay) {
            return 1;
        }

        // 递归公式：今天的桃子 = (明天的桃子 + 1) * 2
        int nextDayPeaches = recursiveHelper(currentDay + 1, targetDay);
        return (nextDayPeaches + 1) * 2;
    }
}