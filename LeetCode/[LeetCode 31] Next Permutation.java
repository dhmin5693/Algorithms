class Solution {

    public void nextPermutation(int[] A) {

        if(A == null || A.length <= 1) {
            return;
        }

        int i = A.length - 2;
        while(i >= 0 && A[i] >= A[i + 1]) {   // Find 1st id i that breaks descending order
            i--;
        }

        if(i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while(A[j] <= A[i]) {              // Find rightmost first larger id j
                j--;
            }
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
    }

    private void swap(int[] A, int i, int j) {
        A[i] ^= A[j];
        A[j] ^= A[i];
        A[i] ^= A[j];
    }

    private void reverse(int[] A, int i, int j) {
        while(i < j) {
            swap(A, i++, j--);
        }
    }
}

/* test with Junit5

public class SolutionTest {

    private final Solution solution = new Solution();

    @MethodSource("testcase")
    @ParameterizedTest
    void test(int[] actual, int[] expected) {
        solution.nextPermutation(actual);
        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> testcase() {
        return Stream.of(
            Arguments.of(new int[] {1,2,3}, new int[] {1,3,2}),
            Arguments.of(new int[] {3,2,1}, new int[] {1,2,3}),
            Arguments.of(new int[] {1,1,5}, new int[] {1,5,1}),
            Arguments.of(new int[] {1}, new int[] {1})
        );
    }
}

 */