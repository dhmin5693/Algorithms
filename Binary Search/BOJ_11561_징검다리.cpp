/*
 * BOJ 11561. 징검다리
 *
 * 주어진 규칙에 따라 최대의 징검다리는 몇 개까지 밟을 수 있냐는 문제입니다.
 * 시작하는 지점은 신경쓰지 말되 1, 2, 3, ..., k까지의 합이 n을 넘지 않는 최대값을 구하면 됩니다.
 * 쉬운 예를 들면, 1 ~ 100의 징검다리를 밟는다고 했을 때 1 + 2 + ... + 13 = 91입니다.
 * 시작을 8에서 한다고 생각하면 9, 10, 12, 15, ..., 100이 되는 것입니다.
 */
#include <cstdio>
typedef unsigned long long ll;

ll chk(ll x)
{
	return x * (x + 1LL);
}

int main()
{
	int t;
	for (scanf("%d", &t); t > 0; t--)
	{
		ll n;
		scanf("%llu", &n);

		ll low = 1LL, high = n, mid = 1LL;

		while (low <= high)
		{
			mid = (low + high) / 2LL;

			if (chk(mid) > n * 2)
				high = mid - 1LL;
			else
				low = mid + 1LL;
		}

		if (chk(mid) > n * 2)	mid--;

		printf("%llu\n", mid);
	}

	return 0;
}