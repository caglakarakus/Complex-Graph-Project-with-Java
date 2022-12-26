package Project4.src;

public class Graph {

    private int verticeNumbers;
    private int[][] edges;

    public Graph(int verticeNumbers) {
        this.verticeNumbers = verticeNumbers;
        edges = new int[verticeNumbers][verticeNumbers];
        for (int i = 0; i < verticeNumbers; i++) {
            for (int j = 0; j < verticeNumbers; j++) {
                edges[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public int getVerticeNumbers() {
        return verticeNumbers;
    }

    public boolean checkEdge(int source, int destination) {
        return edges[source][destination] != Integer.MAX_VALUE;
    }

    public void addEdge(Edge edge) {
        edges[edge.getSrc()][edge.getDest()] = edge.getWeight();
        edges[edge.getDest()][edge.getSrc()] = edge.getWeight();
    }

    public Edge getEdge(int source, int destination) {
        return new Edge(source, destination, edges[source][destination]);
    }

    public class Edge {
        private int destination;
        private int source;
        private int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public int getDest() {
            return this.destination;
        }

        public int getSrc() {
            return this.source;
        }

        public int getWeight() {
            return this.weight;
        }

    }

}