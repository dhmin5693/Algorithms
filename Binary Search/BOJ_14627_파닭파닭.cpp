#include <cstdio>
typedef unsigned long long ull;
ull vegetable[1000000];

ull bs(int s, int c, ull sum)
{
	ull low = 1LL, high = sum / c, mid = (low + high) / 2LL;

	while (low <= high)
	{
		int cnt = 0;

		for (int i = 0; i < s; i++)
			cnt += vegetable[i] / mid;

		if (cnt >= c)
			low = mid + 1LL;
		else
			high = mid - 1LL;

		mid = (low + high) / 2LL;
	}

	return sum - mid * c;
}

int main()
{
	//	freopen("input.txt", "r", stdin);

	int s, c;
	ull sum = 0LL;
	scanf("%d %d", &s, &c);

	for (int i = 0; i < s; i++)
	{
		scanf("%llu", &vegetable[i]);
		sum += vegetable[i];
	}

	printf("%llu\n", bs(s, c, sum));

	return 0;
}