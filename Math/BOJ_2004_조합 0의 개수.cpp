/*
 * BOJ 2004. 조합 0의 개수
 *
 * 입력받는 정수가 최대 20억이므로, 조합 결과를 구할 수는 없습니다.
 * 각 숫자를 소인수분해했을 때 2*5 = 10, 2*2*5*5 = 100, ...이 되므로,
 * 2, 5 쌍의 개수를 세면 정답입니다.
 * 이 문제에서는 2, 5 중 더 적은 값을 정답으로 출력합니다.
 *
 * nCm = n! / m!(n-m)! 공식에서 각 factorial이 갖는 2와 5의 숫자를 셌습니다.
 */
#include <cstdio>
#define MIN(a, b) (a) < (b) ? (a) : (b)

/**
 * @param num 나눌 수(2 또는 5), fac 구할 factorial값
 * @return fac!을 인수분해 했을 때 num의 개수
 */
long long count(int num, int fac) {

	long long cnt = 0;

	if (fac > 0) {
		for (long long i = num; i <= fac; i *= num) {
			cnt += fac / i;
		}
	}

	return cnt;
}

int main()
{
	int n, m;
	scanf("%d %d", &n, &m);

	printf("%lld\n", MIN(count(2, n) - count(2, m) - count(2, n - m), count(5, n) - count(5, m) - count(5, n - m)));

	return 0;
}