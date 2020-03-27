import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

//        br = new BufferedReader(new FileReader("input.txt"));

        String[] inputs = br.readLine().split(" ");
        int v = Integer.parseInt(inputs[0]);
        int e = Integer.parseInt(inputs[1]);
        int k = Integer.parseInt(br.readLine());

        int[] dist = new int[v + 1];

        List<List<Node>> edges = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            dist[i] = INF;
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int val = Integer.parseInt(inputs[2]);

            edges.get(from).add(new Node(to, val));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(k, 0));

        while(!pq.isEmpty()) {
            Node top = pq.poll();
            int edge = top.edge;
            int val = top.val;

            if (dist[edge] != INF) {
                continue;
            }

            dist[edge] = val;

            for (Node node : edges.get(edge)) {
                if (dist[node.edge] != INF) {
                    continue;
                }

                pq.add(new Node(node.edge, val + node.val));
            }
        }

        for (int i = 1; i <= v; i++) {
            bw.write(((dist[i] == INF) ? "INF" : dist[i]) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node> {
        int edge;
        int val;
        Node(int edge, int val) {
            this.edge = edge;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
}