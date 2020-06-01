package com.zhzhang;

import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	    int[] a = { 1, 2, 3, 4, 1 };
	    int[] b = { 3, 4, 1, 2, 1, 3 };
        System.out.println(Arrays.toString(longestCommonSubsequence(a, b)));
    }

    private static int[] longestCommonSubsequence(int[] a, int[] b) {
        int[][] table = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i ++) {
            int elementA = a[i - 1];
            for (int j = 1; j <= b.length; j ++) {
                int elementB = b[j - 1];
                if (elementA == elementB) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }

        Stack<Integer> toReturnStack = new Stack<>();
        int pointerA = a.length;
        int pointerB = b.length;
        while (pointerA > 0 && pointerB > 0) {
            if (table[pointerA][pointerB] == table[pointerA - 1][pointerB]) {
                pointerA --;
            } else if (table[pointerA][pointerB] == table[pointerA][pointerB - 1]) {
                pointerB --;
            } else {
                toReturnStack.push(a[pointerA - 1]);
                pointerA --;
                pointerB --;
            }
        }

        return convertIntegerStackToIntArray(toReturnStack);
    }

    private static int[] convertIntegerStackToIntArray(Stack<Integer> stack) {
        int[] array = new int[stack.size()];
        int pointer = 0;

        while (!stack.isEmpty()) {
            array[pointer ++] = stack.pop();
        }

        return array;
    }
}
