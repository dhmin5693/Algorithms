import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    private ArrayList<Integer>[][][][] database = new ArrayList[4][3][3][3];

    public int[] solution(String[] infos, String[] queries) {

        for (String info : infos) {
            setDatabase(info);
        }

        sortDatabase();

        List<Integer> answer = new ArrayList<>();
        for (String query : queries) {
            answer.add(findData(query));
        }

        return answer.stream()
                     .mapToInt(i -> i)
                     .toArray();
    }

    private void setDatabase(String info) {

        String[] split = info.split(Constant.BLANK.val);

        int[] indexes = new int[] {
            setLanguage(split[0]), setPosition(split[1]), setLevel(split[2]), setFood(split[3])
        };

        int data = Integer.parseInt(split[4]);

        for (int i = 0; i < 16; i++) {
            int idx0 = (i & 1) == 1 ? Constant.LANGUAGE_ALL.idx : indexes[0];
            int idx1 = (i & 2) == 2 ? Constant.OTHER_ALL.idx : indexes[1];
            int idx2 = (i & 4) == 4 ? Constant.OTHER_ALL.idx : indexes[2];
            int idx3 = (i & 8) == 8 ? Constant.OTHER_ALL.idx : indexes[3];
            addData(idx1, indexes[1], indexes[2], indexes[3], data);
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

    private int findData(String query) {
        String[] split = query.split(Constant.BLANK.val);
        return 0;
    }

    private void addData(int idx0, int idx1, int idx2, int idx3, int data) {
        if (database[idx0][idx1][idx2][idx3] == null) {
            database[idx0][idx1][idx2][idx3] = new ArrayList<>();
        }
        database[idx0][idx1][idx2][idx3].add(data);
    }

    private int setLanguage(String s) {
        if (Constant.CPP.val.equals(s)) {
            return Constant.CPP.idx;
        }
        if (Constant.JAVA.val.equals(s)) {
            return Constant.JAVA.idx;
        }
        if (Constant.PYTHON.val.equals(s)) {
            return Constant.PYTHON.idx;
        }
        return Constant.DONT_CARE.idx;
    }

    private int setPosition(String s) {
        if (Constant.BACKEND.val.equals(s)) {
            return Constant.BACKEND.idx;
        }
        if (Constant.FRONTEND.val.equals(s)) {
            return Constant.FRONTEND.idx;
        }
        return Constant.DONT_CARE.idx;
    }

    private int setLevel(String s) {
        if (Constant.JUNIOR.val.equals(s)) {
            return Constant.JUNIOR.idx;
        }
        if (Constant.SENIOR.val.equals(s)) {
            return Constant.SENIOR.idx;
        }
        return Constant.DONT_CARE.idx;
    }

    private int setFood(String s) {
        if (Constant.CHICKEN.val.equals(s)) {
            return Constant.CHICKEN.idx;
        }
        if (Constant.PIZZA.val.equals(s)) {
            return Constant.PIZZA.idx;
        }
        return Constant.DONT_CARE.idx;
    }

    enum Constant {
        BLANK(" ", -1),
        DONT_CARE("-", -1),
        LANGUAGE_ALL("", 3),
        OTHER_ALL("", 2),
        CPP("cpp", 0),
        JAVA("java", 1),
        PYTHON("python", 2),
        BACKEND("backend", 0),
        FRONTEND("frontend", 1),
        JUNIOR("junior", 0),
        SENIOR("senior", 1),
        CHICKEN("chicken", 0),
        PIZZA("pizza", 1);

        String val;
        int idx;

        Constant(String val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}