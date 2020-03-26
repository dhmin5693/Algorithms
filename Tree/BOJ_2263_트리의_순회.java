import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] in;
    static int[] post;
    static int[] index;

    public static void main(String[] args) throws Exception {

        // br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        in = inputOrder(n);
        post = inputOrder(n);
        index = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            index[in[i]] = i;
        }

        getPreOrder(1, n, 1, n);

        bw.flush();
        bw.close();
    }

    static int[] inputOrder(int n) throws Exception {
        int[] order = new int[n + 1];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            order[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return order;
    }

    static void getPreOrder(int inST, int inED, int postST, int postED) throws Exception {

        if (inST > inED || postST > postED) {
            return;
        }

        bw.write(post[postED] + " ");

        int rootIdx = index[post[postED]];

        // left
        getPreOrder(inST, rootIdx - 1, postST, postST + (rootIdx - inST) - 1);

        // right
        getPreOrder( rootIdx + 1, inED, postST + (rootIdx - inST), postED - 1);
    }
}