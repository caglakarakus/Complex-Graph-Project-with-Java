package Project4.src;

import java.util.HashMap;

public class Dijkstra {

    public static int findShortestPath(Graph graph, int source, int dest) {

        int numVertices = graph.getVerticeNumbers();

        int[] route = new int[numVertices];

        int[] distance = new int[numVertices];

        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {

            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
            route[i] = -1;

        }

        distance[source] = 0;

        int currentVertice = source;

        while (currentVertice != -1) {

            visited[currentVertice] = true;

            for (int i = 0; i < numVertices; i++) {

                if (graph.checkEdge(currentVertice, i) && !visited[i]) {

                    int newDistance = distance[currentVertice] + graph.getEdge(currentVertice, i).getWeight();

                    if (newDistance < distance[i]) {

                        distance[i] = newDistance;
                        route[i] = currentVertice;

                    }

                }
            }

            currentVertice = -1;

            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < visited.length; i++) {
                if (!visited[i] && distance[i] < minDistance) {
                    currentVertice = i;
                    minDistance = distance[i];
                }
            }
        }

        if (distance[dest] == Integer.MAX_VALUE) {
            return -1;
        }

        return distance[dest];
    }

    public static int findShortestPathWithFlags(Graph graph, int[] flags) {

        HashMap<Integer, HashMap<Integer, Integer>> shortestPaths = new HashMap<>();
        for (int i : flags) {
            HashMap<Integer, Integer> paths = new HashMap<>();
            int pathLength = Integer.MAX_VALUE;
            for (int j : flags) {
                if (i != j) {
                    int path = findShortestPath(graph, i, j);
                    if (path < pathLength) {
                        pathLength = path;
                        paths.put(0, pathLength);
                        paths.put(1, j);
                    }
                }
            }
            shortestPaths.put(i, paths);
        }

        int sum = 0;

        // check if there is no shortest path
        if (shortestPaths.size() == 1) {
            return 0;
        }

        for (int i : flags) {
            // check if both nodes have the same shortest path, eliminate one of them
            if (shortestPaths.get(i).get(0) == shortestPaths.get(shortestPaths.get(i).get(1)).get(0)) {
                sum += shortestPaths.get(i).get(0);
                // assign the other nodes length to 0
                shortestPaths.get(shortestPaths.get(i).get(1)).put(0, 0);
                shortestPaths.get(i).put(0, -1);
            } else if (shortestPaths.get(shortestPaths.get(i).get(1)).get(0) == -1) {
                continue;
            } else {
                sum += shortestPaths.get(i).get(0);
            }
        }
        return sum;
    }

}