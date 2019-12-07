#include <iostream>
using namespace std;

int t[46] = { 0, };
int dp[1001] = { 0, };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int val = 0;
	for (int i = 1; val <= 1000; i++) {
		val = i * (i + 1) / 2;
		t[i] = val;
	}

	int idx;
	for (int i = 1; i < 46; i++) {
		for (int j = 1; j < 46; j++) {
			for (int k = 1; k < 46; k++) {
				idx = t[i] + t[j] + t[k];
				if (idx > 1000) {
					break;
				}
				dp[idx] = 1;
			}
		}
	}

	int n, input;
	for (cin >> n; n > 0; n--) {
		cin >> input;
		cout << dp[input] << "\n";
	}

	return 0;
}