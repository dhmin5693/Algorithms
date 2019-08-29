#include <cstdio>
using namespace std;

typedef long long int lli;

lli dp[16][16] = { 0, };

void calc(int startY, int startX, int endY, int endX) {

	dp[startY][startX - 1] = 0;
	dp[startY - 1][startX] = 1;

	for (int i = startY; i <= endY; ++i) {
		for (int j = startX; j <= endX; ++j) {
			dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
		}
	}
}

int main()
{
	int n, m, k;
	scanf("%d %d %d", &n, &m, &k);

	if (k == 0) {
		calc(1, 1, n, m);
		printf("%lld\n", dp[n][m]);
		
		return 0;
	}

	int row = k / m + 1;
	int col = k % m;

	if (k % m == 0) {
		--row, col = m;
	}

	calc(1, 1, row, col);
	lli ans = dp[row][col];

	calc(row, col, n, m);
	printf("%lld\n", ans * dp[n][m]);

	return 0;
}