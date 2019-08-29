/*
 * BOJ 1654. 랜선 자르기
 *
 * 2805 나무 자르기랑 같은 방법으로 풀었으므로 생략합니다..
 */
#include <cstdio>
typedef unsigned long long ull;
ull wire[10000];

ull bs(int k, int n, ull max)
{
	ull low = 1, high = max, mid = (low + high) / 2;

	while (low <= high)
	{
		int cnt = 0;

		for (int i = 0; i < k; i++)
			cnt += wire[i] / mid;

		if (cnt >= n)
			low = mid + 1;
		else
			high = mid - 1;
			
		mid = (low + high) / 2;
	}

	return mid;
}

int main()
{
	int k, n; 
	ull max = 0;
	scanf("%d %d", &k, &n);
	for (int i = 0; i < k; i++)
	{
		scanf("%llu", &wire[i]);
		max = max < wire[i] ? wire[i] : max;
	}

	printf("%llu\n", bs(k, n, max));
	
	return 0;
}