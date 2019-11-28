#include <iostream>
#define MOD 15746
using namespace std;

typedef long long ll;

ll dp[1000001] = { 0, };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;

	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i <= n; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
	}

	cout << dp[n] << '\n';

	return 0;
}