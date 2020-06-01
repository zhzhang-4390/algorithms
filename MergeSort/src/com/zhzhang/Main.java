package com.zhzhang;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    int[] array = new int[] { 1, 12, 5, 111, 200, 1000, 10 };
        System.out.println(Arrays.toString(split(array, 0, array.length)));
    }

    private static int[] split(int[] array, int head, int tail) {
        if (head == tail - 1) {
            return new int[] { array[head] };
        } else {
            int midPointer = (head + tail) / 2;
            return merge(
                    split(array, head, midPointer),
                    split(array, midPointer, tail)
            );
        }
    }

    private static int[] merge(int[] arrayX, int[] arrayY) {
        int[] mergedArray = new int[arrayX.length + arrayY.length];
        int pointerMerged = 0;

        int pointerX = 0;
        int pointerY = 0;
        while (pointerX < arrayX.length && pointerY < arrayY.length) {
            if (arrayX[pointerX] < arrayY[pointerY]) {
                mergedArray[pointerMerged] = arrayX[pointerX];
                pointerX ++;
            } else {
                mergedArray[pointerMerged] = arrayY[pointerY];
                pointerY ++;
            }
            pointerMerged ++;
        }
        while (pointerX < arrayX.length) {
            mergedArray[pointerMerged] = arrayX[pointerX];
            pointerX ++;
            pointerMerged ++;
        }
        while (pointerY < arrayY.length) {
            mergedArray[pointerMerged] = arrayY[pointerY];
            pointerY ++;
            pointerMerged ++;
        }

        return mergedArray;
    }
}
