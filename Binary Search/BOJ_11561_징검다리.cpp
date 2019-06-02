/*
 * BOJ 11561. ¡�˴ٸ�
 *
 * �־��� ��Ģ�� ���� �ִ��� ¡�˴ٸ��� �� ������ ���� �� �ֳĴ� �����Դϴ�.
 * �����ϴ� ������ �Ű澲�� ���� 1, 2, 3, ..., k������ ���� n�� ���� �ʴ� �ִ밪�� ���ϸ� �˴ϴ�.
 * ���� ���� ���, 1 ~ 100�� ¡�˴ٸ��� ��´ٰ� ���� �� 1 + 2 + ... + 13 = 91�Դϴ�.
 * ������ 8���� �Ѵٰ� �����ϸ� 9, 10, 12, 15, ..., 100�� �Ǵ� ���Դϴ�.
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