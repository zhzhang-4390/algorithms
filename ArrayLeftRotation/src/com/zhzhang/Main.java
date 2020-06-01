package com.zhzhang;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        for (int d = 1; d < 10; d ++) {
            int[] a = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            System.out.println(Arrays.toString(rotLeft(a, d)));
        }
    }

    private static int[] rotLeft(int[] a, int d) {
        reverse(a, 0, a.length - 1);
        reverse(a, 0, a.length - d - 1);
        reverse(a, a.length - d, a.length - 1);
        return a;
    }

    private static void reverse(int[] array, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;

        int frontPointer = startIndex;
        int rearPointer = endIndex;
        while (frontPointer <= midIndex) {
            swap(array, frontPointer, rearPointer);
            frontPointer ++;
            rearPointer --;
        }
    }

    private static void swap(int[] array, int index, int theOtherIndex) {
        int temp = array[index];
        array[index] = array[theOtherIndex];
        array[theOtherIndex] = temp;
    }
}
