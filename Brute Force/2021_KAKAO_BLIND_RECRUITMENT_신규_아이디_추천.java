public class Solution {

    public String solution(String newId) {
        String answer = newId;

        answer = step1(answer);
        answer = step2(answer);
        answer = step3(answer);
        answer = step4(answer);
        answer = step5(answer);
        answer = step6(answer);

        return step7(answer);
    }

    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    private String step1(String s) {
        if (isEmpty(s)) {
            return s;
        }
        return s.toLowerCase();
    }

    private String step2(String s) {
        if (isEmpty(s)) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String step3(String s) {
        if (isEmpty(s)) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        boolean isDot = false;

        for (int i = 0; i < s.length(); i++) {
            char c  = s.charAt(i);

            if (c != '.') {
                sb.append(c);
                isDot = false;
                continue;
            }

            if (!isDot) {
                sb.append(c);
            }

            isDot = true;
        }

        return sb.toString();
    }

    private String step4(String s) {
        if (isEmpty(s)) {
            return s;
        }

        if (s.length() == 1 && s.equals(".")) {
            return "";
        }

        int startIndex = s.charAt(0) == '.' ? 1 : 0;
        int endIndex = s.charAt(s.length() - 1) == '.' ? s.length() - 1 : s.length();

        return s.substring(startIndex, endIndex);
    }

    private String step5(String s) {
        if (isEmpty(s)) {
            return "a";
        }
        return s;
    }

    private String step6(String s) {
        if (s.length() > 15) {
            s = s.substring(0, 15);

            if (s.charAt(s.length() - 1) == '.') {
                s = s.substring(0, 14);
            }
        }
        return s;
    }

    private String step7(String s) {

        if (s.length() >= 3) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s);
        while(sb.length() < 3) {
            sb.append(sb.charAt(sb.length() - 1));
        }
        return sb.toString();
    }
}
