package com.zhzhang;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("ifailuhkqq"));
    }

    private static int sherlockAndAnagrams(String s) {
        int count = 0;
        for (int i = 1; i < s.length(); i ++) {
            for (int j = 0; j < s.length() - i; j ++) {
                String pivot = s.substring(j, j + i);
                HashMap<Character, Integer> dictionary = constructDictionary(pivot);
                for (int k = j + 1; k < s.length() - i + 1; k ++) {
                    String candidate = s.substring(k, k + i);
                    if (compare(new HashMap<>(dictionary), candidate)) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    private static HashMap<Character, Integer> constructDictionary(String s) {
        HashMap<Character, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (dictionary.containsKey(c)) {
                dictionary.put(c, dictionary.get(c) + 1);
            } else {
                dictionary.put(c, 1);
            }
        }
        return dictionary;
    }

    private static boolean compare(HashMap<Character, Integer> dictionary, String candidate) {
        for (int i = 0; i < candidate.length(); i ++) {
            char c = candidate.charAt(i);
            if (!dictionary.containsKey(c)) {
                return false;
            }

            int count = dictionary.get(c);
            if (count == 0) {
                return false;
            } else {
                dictionary.put(c, count - 1);
            }
        }
        for (Character c: dictionary.keySet()) {
            if (dictionary.get(c) != 0) {
                return false;
            }
        }
        return true;
    }
}
