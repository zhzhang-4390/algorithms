package com.zhzhang;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        long[] a = { 3, 3, 9, 9, 5 };
        System.out.println(maximumSum(a, 7));
    }

    private static long maximumSum(long[] a, long m) {
        SortedMap<Long, List<Integer>> map = constructMap(a, m);
        long maxRemainder = 0;
        long prevRemainder = -m;
        for (Long remainder: map.keySet()) {
            if (remainder > maxRemainder) {
                maxRemainder = remainder;
            }
            if (remainder - prevRemainder < m - maxRemainder) {
                if (isBefore(map.get(prevRemainder), map.get(remainder))) {
                    maxRemainder = m - (remainder - prevRemainder);
                }
            }
            prevRemainder = remainder;
        }
        return maxRemainder;
    }

    private static SortedMap<Long, List<Integer>> constructMap(long[] a, long m) {
        long sum = 0;
        SortedMap<Long, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < a.length; i ++) {
            sum += a[i];
            long remainder = sum % m;
            if (map.containsKey(remainder)) {
                map.get(remainder).add(i);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                map.put(remainder, indexes);
            }
        }
        return map;
    }

    private static boolean isBefore(List<Integer> prevIndexes, List<Integer> indexes) {
        return Collections.max(prevIndexes) > Collections.min(indexes);
    }
}
