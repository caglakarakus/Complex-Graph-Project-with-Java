package Project4.src;

import java.io.*;

public class project4 {

    private static void calculateDistances(String filename) throws NumberFormatException, IOException {

        Graph graph;
        int M;
        int start;
        int end;
        int[] flags;

        BufferedReader br = new BufferedReader(new FileReader(filename));

        int V = Integer.parseInt(br.readLine());

        graph = new Graph(V);

        M = Integer.parseInt(br.readLine());

        String[] startEndPoints = br.readLine().split(" ");

        startEndPoints[0] = startEndPoints[0].trim();
        startEndPoints[0] = startEndPoints[0].substring(1);

        startEndPoints[1] = startEndPoints[1].trim();
        startEndPoints[1] = startEndPoints[1].substring(1);

        start = Integer.parseInt(startEndPoints[0]);
        end = Integer.parseInt(startEndPoints[1]);

        String[] flagPoints = br.readLine().split(" ");
        flags = new int[M];
        for (int i = 0; i < M; i++) {

            flagPoints[i] = flagPoints[i].trim();
            flagPoints[i] = flagPoints[i].substring(1);

            flags[i] = Integer.parseInt(flagPoints[i]);
        }

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" ");

            data[0] = data[0].trim();
            data[0] = data[0].substring(1);

            int source = Integer.parseInt(data[0]);

            for (int i = 1; i < data.length; i += 2) {

                data[i] = data[i].trim();
                data[i] = data[i].substring(1);

                int dest = Integer.parseInt(data[i]);
                int weight = Integer.parseInt(data[i + 1]);
                graph.addEdge(graph.new Edge(source, dest, weight));
            }

        }

        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter("answers.txt", true));

        // find the shortest path from start to end by passing flags
        int path = Dijkstra.findShortestPath(graph, start, end);
        System.out.println(path);

        bw.write(path + "\n");

        // find the shortest path that passes all flags
        int path2 = Dijkstra.findShortestPathWithFlags(graph, flags);
        System.out.println(path2);
        bw.write(path2 + "\n\n");

        bw.close();

    }

    public static void main(String[] args) throws IOException {

        System.out.println("SMALL CASES:\n");
        for (int i = 1; i < 14; i++) {
            System.out.println(i + "th case result: ");
            calculateDistances("./smallCases/input/inp" + i + ".txt");
            System.out.println("\n");
        }

        System.out.println("\n\nLARGE CASES:\n");
        for (int i = 1; i < 4; i++) {
            System.out.println(i + "th case result: ");
            calculateDistances("./largeCases/input/stress" + i + ".txt");
            System.out.println("\n");
        }
    }

}