import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int INF = 1000000000;

    public static void main(String[] args) throws Exception {

//        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = setItems(n);
        int[][] dist = floyd(n, setDist(n, r));

        int max = 0;

        for (int area = 1; area <= n; area++) {
            int tmp = items[area];

            for (int dst = 1; dst <= n; dst++) {
                if (dist[area][dst] <= m) {
                    tmp += items[dst];
                }
            }

            max = Integer.max(max, tmp);
        }

        System.out.println(max);
    }

    static int[] setItems(int n) throws Exception {
        int[] items = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        return items;
    }

    static int[][] setDist(int n, int r) throws Exception {

        String[] inputs;

        int[][] dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < r; i++) {
            inputs = br.readLine().split(" ");

            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int val = Integer.parseInt(inputs[2]);

            dist[from][to] = val;
            dist[to][from] = val;
        }

        return dist;
    }

    static int[][] floyd(int n, int[][] dist) {

        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (start == end) {
                        continue;
                    }
                    dist[start][end] = Integer.min(dist[start][end], dist[start][mid] + dist[mid][end]);
                }
            }
        }

        return dist;
    }
}