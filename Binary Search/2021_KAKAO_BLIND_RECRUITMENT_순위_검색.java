import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {

    private final ArrayList<Integer>[][][][] database = new ArrayList[4][3][3][3];

    public int[] solution(String[] infos, String[] queries) {

        Arrays.stream(infos)
              .forEach(this::insert);

        sortDatabase();

        return Arrays.stream(queries)
                     .mapToInt(this::search)
                     .toArray();
    }

    private void insert(String info) {

        String[] column = split(info);

        int[] indexes = new int[4];

        for (int i = 0; i < 4; i++) {
            indexes[i] = Constant.findIndex(column[i]);
        }

        int score = Integer.parseInt(column[4]);
        int dontCare = Constant.DONT_CARE.index;

        for (int i = 0; i < 16; i++) {
            int idx0 = (i & 1) == 1 ? dontCare : indexes[0];
            int idx1 = (i & 2) == 2 ? dontCare : indexes[1];
            int idx2 = (i & 4) == 4 ? dontCare : indexes[2];
            int idx3 = (i & 8) == 8 ? dontCare : indexes[3];
            addScore(idx0, idx1, idx2, idx3, score);
        }
    }

    private void sortDatabase() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (database[i][j][k][l] != null) {
                            Collections.sort(database[i][j][k][l]);
                        }
                    }
                }
            }
        }
    }

    private int search(String query) {

        String[] data = split(query);

        int language = Constant.findIndex(data[0]);
        int position = Constant.findIndex(data[2]);
        int level = Constant.findIndex(data[4]);
        int food = Constant.findIndex(data[6]);

        int score = Integer.parseInt(data[7]);

        return binarySearch(database[language][position][level][food], score);
    }

    private int binarySearch(List<Integer> list, int score) {

        if (list == null || list.size() == 0) {
            return 0;
        }

        if (list.size() == 1) {
            if (score <= list.get(0)) {
                return 1;
            }
            return 0;
        }

        int left = 0;
        int right = list.size();

        while (left < right) {
            int middle = (left + right) / 2;

            if (list.get(middle) < score) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return list.size() - left;
    }

    private void addScore(int idx0, int idx1, int idx2, int idx3, int score) {
        if (database[idx0][idx1][idx2][idx3] == null) {
            database[idx0][idx1][idx2][idx3] = new ArrayList<>();
        }
        database[idx0][idx1][idx2][idx3].add(score);
    }

    private String[] split(String s) {
        return s.split(" ");
    }

    enum Constant {
        ERROR(null, -1),
        DONT_CARE("-", 0),
        CPP("cpp", 1),
        JAVA("java", 2),
        PYTHON("python", 3),
        BACKEND("backend", 1),
        FRONTEND("frontend", 2),
        JUNIOR("junior", 1),
        SENIOR("senior", 2),
        CHICKEN("chicken", 1),
        PIZZA("pizza", 2);

        private static final Map<String, Constant> ALL_MAP =
            Arrays.stream(values())
                  .collect(Collectors.toMap(c -> c.value, Function.identity()));

        String value;
        int index;

        Constant(String value, int index) {
            this.value = value;
            this.index = index;
        }

        public static int findIndex(String key) {
            return ALL_MAP.getOrDefault(key, ERROR).index;
        }
    }
}