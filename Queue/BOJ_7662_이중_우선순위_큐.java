import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            runTestCase(br);
        }
    }

    static void runTestCase(BufferedReader br) throws Exception {
        int k = Integer.parseInt(br.readLine());

        TreeMap<Integer, Integer> map = new TreeMap<>();

        String[] cmd;

        for (int i = 0; i < k; i++) {
            cmd = br.readLine().split(" ");
            int num = Integer.parseInt(cmd[1]);

            if (cmd[0].charAt(0) == 'I') {

                if (map.containsKey(num)) {
                    int cnt = map.get(num);
                    map.put(num, cnt + 1);
                    continue;
                }

                map.put(num, 1);
            } else {

                if (map.size() == 0) {
                    continue;
                }

                int key = geyKey(map, num);
                int cnt = map.get(key);

                if (cnt == 1) {
                    map.remove(key);
                } else {
                    map.put(key, cnt - 1);
                }
            }
        }

        if (map.size() == 0) {
            System.out.println("EMPTY");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(map.lastKey()).append(' ').append(map.firstKey());

        System.out.println(sb.toString());
    }

    static int geyKey(TreeMap<Integer, Integer> map, int type) {

        if (type == 1) {
            return map.lastKey();
        }

        return map.firstKey();
    }
}