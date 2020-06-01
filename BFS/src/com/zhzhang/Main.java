package com.zhzhang;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final int n = 4;
        final int[][] edges = {{1, 2}, {1, 3}};
        final int s = 1;

        System.out.println(Arrays.toString(bfs(n, edges, s)));
    }

    private static int[] bfs(int n, int[][] edges, int s) {
        final int[] hopCountFromStartTo = new int[n + 1];
        Arrays.fill(hopCountFromStartTo, -1);
        hopCountFromStartTo[s] = 0;

        final ArrayList<Integer>[] adjacencyArray = constructAdjacencyArray(n, edges);
        final ArrayDeque<Integer> unvisitedNodes = new ArrayDeque<>();
        unvisitedNodes.offer(s);

        while (!unvisitedNodes.isEmpty()) {
            int currentNode = unvisitedNodes.poll();
            int currentHop = hopCountFromStartTo[currentNode];
            int nextHop = currentHop + 1;

            ArrayList<Integer> neighbours = adjacencyArray[currentNode];
            for (int neighbour: neighbours) {
                if (hopCountFromStartTo[neighbour] == -1 && !unvisitedNodes.contains(neighbour)) {
                    unvisitedNodes.offer(neighbour);
                    hopCountFromStartTo[neighbour] = nextHop;
                }
            }
        }

        return hopCountFromStartTo;
    }

    private static ArrayList<Integer>[] constructAdjacencyArray(int n, int[][] edges) {
        final ArrayList<Integer>[] adjacencyArray = new ArrayList[n + 1];
        for (int i = 0; i < adjacencyArray.length; i++) {
            adjacencyArray[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            adjacencyArray[edge[0]].add(edge[1]);
            adjacencyArray[edge[1]].add(edge[0]);
        }

        return adjacencyArray;
    }
}
