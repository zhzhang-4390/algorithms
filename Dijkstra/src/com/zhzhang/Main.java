package com.zhzhang;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
            {
                1, 2, 10
            },
            {
                1, 3, 6
            },
            {
                2, 4, 8
            },
        };
        int s = 2;

        System.out.println(Arrays.toString(shortestReach(n, edges, s)));
    }

    private static int[] shortestReach(int n, int[][] edges, int s) {
        int[][] goodEdges = convertEdgeFormat(n, edges);
        int[] distancesFromRoot = goodEdges[s];

        ArrayList<Integer> visitedNodes = new ArrayList<>();

        ArrayList<Integer> nodes = new ArrayList<>();
        nodes.add(s);

        while (!nodes.isEmpty()) {
            int closestNode = findClosestNode(nodes, distancesFromRoot);
            nodes.remove(Integer.valueOf(closestNode));
            visitedNodes.add(closestNode);

            for (int i = 1; i < goodEdges[closestNode].length; i++) {
                int distanceFromCurrentNode = goodEdges[closestNode][i];
                if (distanceFromCurrentNode > 0) {
                    if (!visitedNodes.contains(i) && !nodes.contains(i)) {
                        nodes.add(i);
                    }

                    int distanceFromRoot = distancesFromRoot[closestNode] + distanceFromCurrentNode;
                    if (distancesFromRoot[i] == -1 || distanceFromRoot < distancesFromRoot[i]) {
                        distancesFromRoot[i] = distanceFromRoot;
                    }
                }
            }
        }
        
        return distancesFromRoot;
    }

    private static int[][] convertEdgeFormat(int n, int[][] badEdges) {
        int[][] goodEdges = new int[n + 1][n + 1];
        for (int[] goodEdge: goodEdges) {
            Arrays.fill(goodEdge, -1);
        }
        for (int i = 1; i <= n; i++) {
            goodEdges[i][i] = 0;
        }

        for (int[] badEdge: badEdges) {
            // Account for multiple edges between the same pair
            int existingDistance = goodEdges[badEdge[0]][badEdge[1]];
            if (existingDistance == -1 || badEdge[2] < existingDistance) {
                goodEdges[badEdge[0]][badEdge[1]] = badEdge[2];
                goodEdges[badEdge[1]][badEdge[0]] = badEdge[2];
            }
        }

        return goodEdges;
    }

    private static int findClosestNode(ArrayList<Integer> nodes, int[] distancesFromRoot) {
        int closestNode = 0;
        int minDistance = Integer.MAX_VALUE;

        for (int node: nodes) {
            if (distancesFromRoot[node] < minDistance) {
                closestNode = node;
                minDistance = distancesFromRoot[node];
            }
        }

        return closestNode;
    }
}
