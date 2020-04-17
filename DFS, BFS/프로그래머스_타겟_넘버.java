class Solution {

    int length = 0;
    int[] numbers;
    int target = 0;
    int answer = 0;

    void dfs(int idx, int sum) {

        if (idx >= length) {
            if (sum == target) {
                answer++;
            }

            return;
        }

        dfs(idx + 1, sum + numbers[idx]);
        dfs(idx + 1, sum - numbers[idx]);
    }

    public int solution(int[] numbers, int target) {
        answer = 0;
        this.numbers = numbers;
        this.target = target;
        length = numbers.length;

        dfs(0, 0);

        return answer;
    }
}