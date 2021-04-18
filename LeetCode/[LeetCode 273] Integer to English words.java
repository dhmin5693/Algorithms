import java.util.ArrayList;
import java.util.List;

class Solution {

    private final List<String> bigUnit = List.of("Billion", "Million", "Thousand", "Hundred");
    private final List<String> middleUnit =
        List.of("ninety", "eighty", "Seventy", "Sixty", "Fifty", "Forty", "Thirty", "Twenty");
    private final List<String> smallUnit =
        List.of("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen");

    public String numberToWords(int num) {

        var chunk = new ArrayList<Integer>();

        while(num >= 0) {
            chunk.add(num % 1000);
            num /= 1000;
        }

        return "";
    }
}

/* test with Junit5

 */