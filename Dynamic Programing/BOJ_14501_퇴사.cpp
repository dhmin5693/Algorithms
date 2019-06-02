#include <cstdio>

int dp[25];

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		int t, p;
		scanf("%d %d", &t, &p);

		if (dp[i] > dp[i + 1])
			dp[i + 1] = dp[i];

		if (dp[i + t] < dp[i] + p)
			dp[i + t] = dp[i] + p;
	}

	printf("%d\n", dp[n]);
	return 0;
}