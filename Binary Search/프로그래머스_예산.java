class Solution {
    public int solution(int[] budgets, int M) {
        int answer = 0;
        int max = 0;
        long sum = 0;

        for (int budget : budgets) {
            sum += budget;
            max = Integer.max(max, budget);
        }

        if (sum <= M) {
            return max;
        }

        int left = 0;
        int right = max;
        int middle = 0;

        while(left <= right) {
            middle = (left + right) / 2;
            sum = 0;

            for (int budget : budgets) {
                if (budget > middle) {
                    sum += middle;
                    continue;
                }
                sum += budget;
            }

            if (sum <= M) {
                answer = Integer.max(answer, middle);
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return answer;
    }
}