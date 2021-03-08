import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.stream.Stream;

class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        var stringBuilders = Stream.generate(StringBuilder::new)
                                   .limit(numRows)
                                   .collect(toList());

        int row = -1;
        boolean down = true;
        for (int i = 0; i < s.length(); i++) {

            if (row == numRows - 1) {
                down = false;
            } else if (row <= 0) {
                down = true;
            }

            row = down ? row + 1 : row - 1;

            stringBuilders.get(row).append(s.charAt(i));
        }

        return stringBuilders.stream()
                             .map(StringBuilder::toString)
                             .collect(joining());
    }
}   