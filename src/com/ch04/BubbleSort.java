package com.ch04;

import java.util.Arrays;

public class BubbleSort {

    // An optimized version of Bubble Sort
    public static void bubbleSort(int arr[]) {
        //外部循环控制排序的趟数。冒泡排序的每一趟会将最大的元素"冒泡"到数组的末尾，因此需要执行 n-1 趟，其中 n 是元素的总数
        for (int i = 0; i < arr.length - 1; i++) {
            //内循环控制每趟比较的次数。由于每一趟都会将一个最大的元素沉到数组末尾，所以内循环次数逐渐减小。
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换arr[j]和arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


//    }

    public static void main(String[] args) {
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        BubbleSort.bubbleSort(arr);
        for (int num : arr) {
            System.out.println(num);
        }
    }
}
