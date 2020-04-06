import java.util.TreeMap;

class Solution {
    public int[] solution(String[] operations) {

        TreeMap<Integer, Integer> tree = new TreeMap<>();

        for (String oper : operations) {
            String[] op = oper.split(" ");
            int num = Integer.parseInt(op[1]);

            if (op[0].equals("I")) {
                tree.put(num, tree.getOrDefault(num, 0) + 1);
                continue;
            }

            if (tree.size() <= 0) {
                continue;
            }

            int key;
            if (num == -1) {
                key = tree.firstKey();
            } else {
                key = tree.lastKey();
            }

            if (tree.get(key) > 1) {
                tree.put(key, tree.get(key) - 1);
            } else {
                tree.remove(key);
            }
        }

        if (tree.size() == 0) {
            return new int[] { 0, 0 };
        }

        return new int[] {tree.lastKey(), tree.firstKey()};
    }
}