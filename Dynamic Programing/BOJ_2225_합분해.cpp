#include <cstdio>
#define MOD 1000000000

unsigned long long dp[201][201];

int main() {

	int n, k;
	scanf("%d %d", &n, &k);

	for (int i = 1; i <= 200; i++) {
		dp[0][i] = dp[i][1] = 1LLU;
	}

	for (int i = 2; i <= k; i++) {
		for (int j = 1; j <= n; j++) {
			dp[j][i] = (dp[j - 1][i] % MOD + dp[j][i - 1] % MOD) % MOD;
		}
	}

	printf("%llu\n", dp[n][k]);

	return 0;
}