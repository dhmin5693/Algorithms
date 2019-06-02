#include <cstdio>

long long memo[1002][10] = { 0 };

int main()
{
	int N;
	long long ans = 0;

	scanf("%d", &N);

	for (int i = 0; i <= 9; ++i)
		memo[1][i] = 1;

	for (int i = 2; i <= N; ++i)
	{
		for (int j = 0; j <= 9; ++j)
		{
			memo[i][j] = 0;

			for (int k = 0; k <= j; ++k)
				memo[i][j] += memo[i - 1][k];

			memo[i][j] %= 10007;
		}
	}

	for (int i = 0; i <= 9; ++i)
		ans += memo[N][i];

	ans %= 10007;

	printf("%lld\n", ans);

	return 0;
}