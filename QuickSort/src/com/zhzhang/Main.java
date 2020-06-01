package com.zhzhang;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int[] array = { 9, 6, 5, 10, 1, 3, 4, 7, 8, 2 };
        
        for (int i = 0; i < 10; i ++) {
            List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
            Collections.shuffle(list);
            array = list.stream().mapToInt(n -> n).toArray();

            System.out.println(Arrays.toString(array));
            quickSort(array, 0, array.length - 1);
            System.out.println(Arrays.toString(array));
            System.out.println();
        }
    }

    private static void quickSort(int[] array, int head, int tail) {
        // Sub-array exhausted
        if (head >= tail) {
            return;
        }

        // Find the pointer of the first element larger than the tail
        int pointerOfFirstLarger = head;
        while (pointerOfFirstLarger < tail && array[pointerOfFirstLarger] <= array[tail]) {
            pointerOfFirstLarger ++;
        }

        // Sort with the pivot being the tail
        for (int i = pointerOfFirstLarger; i < tail; i ++) {
            if (array[i] < array[tail]) {
                swap(array, pointerOfFirstLarger, i);
                pointerOfFirstLarger ++;
            }
        }

        // Put pivot in the middle
        swap(array, pointerOfFirstLarger, tail);

        // Sort left & right sub-arrays
        quickSort(array, head, pointerOfFirstLarger - 1);
        quickSort(array, pointerOfFirstLarger + 1, tail);
    }

    private static void swap(int[] array, int pointerX, int pointerY) {
        int temp = array[pointerX];
        array[pointerX] = array[pointerY];
        array[pointerY] = temp;
    }
}
