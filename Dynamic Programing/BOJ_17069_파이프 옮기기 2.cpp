#include <iostream>
using namespace std;

int map[33][33] = { 0, };
long long dp[33][33][3] = { 0, }; // [0]: 가로, [1]: 세로, [2]: 대각선

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}

	dp[0][1][0] = 1L;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!map[i][j + 1]) {
				dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2];
			}
			if (!map[i + 1][j]) {
				dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2];
			}
			if (!map[i][j + 1] && !map[i + 1][j] && !map[i + 1][j + 1]) {
				dp[i + 1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
			}
		}
	}

	printf("%lld\n", dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);

	return 0;
}