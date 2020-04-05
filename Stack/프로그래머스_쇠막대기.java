import java.util.Stack;

class Solution {
    public int solution(String arrangement) {

        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arrangement.length(); i++) {
            if (arrangement.charAt(i) == '(') {
                stack.push(i);
            } else {
                int top = stack.pop();

                if (top == i - 1) {
                    answer += stack.size();
                } else {
                    answer++;
                }
            }
        }

        return answer;
    }
}