import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Set<Integer> visit = new HashSet<>();
            visit.add(a);

            Queue<Item> q = new LinkedList<>();
            q.add(new Item("", a));

            while(!q.isEmpty()) {
                Item top = q.poll();

                for (int next = 0; next < 4; next++) {
                    Type type = Type.values()[next];
                    int nextNum = type.cmd(top.num);

                    if (visit.contains(nextNum)) {
                        continue;
                    }

                    visit.add(nextNum);
                    Item item = new Item(top.cmd + type.name(), nextNum);

                    if (item.num == b) {
                        bw.write(item.cmd + "\n");
                        q.clear();
                        break;
                    }

                    q.add(item);
                }
            }
        }

        bw.flush();
        bw.close();
    }

    static class Item {
        String cmd;
        int num;

        Item(String cmd, int num) {
            this.cmd = cmd;
            this.num = num;
        }
    }

    enum Type {
        D {
            public int cmd(int num) {
                return (num * 2) % 10000;
            }
        }, S {
            public int cmd(int num) {
                return --num >= 0 ? num : 9999;
            }
        }, L {
            public int cmd(int num) {
                num *= 10;
                int tmp = num / 10000;
                num %= 10000;
                return tmp + num;
            }
        }, R {
            public int cmd(int num) {
                int tmp = (num % 10) * 1000;
                num /= 10;
                return tmp + num;
            }
        };

        abstract int cmd(int num);
    }
}