package com.zhzhang;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        long[] array = { 4, 5, 6 };
        System.out.println(minTime(array, 12));
    }

    private static long minTime(long[] machines, long goal) {
        long fastest = Arrays.stream(machines).min().getAsLong();
        long slowest = Arrays.stream(machines).max().getAsLong();

        long head = fastest * goal / machines.length;
        long tail = slowest * goal / machines.length;
        long mid = (head + tail) / 2;
        while (mid > head && mid < tail) {
            long totalProduction = calculateProduction(machines, mid);
            if (totalProduction < goal) {
                head = mid;
            } else {
                tail = mid;
            }
            mid = (head + tail) / 2;
        }

        long totalProduction = calculateProduction(machines, mid);
        if (totalProduction < goal) {
            return mid + 1;
        } else {
            return mid;
        }
    }

    private static long calculateProduction(long[] machines, long day) {
        long production = 0;
        for (long machine: machines) {
            production += day / machine;
        }
        return production;
    }
}
