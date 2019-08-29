#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int num[1000001];
int dp[1000001] = { 0, };
vector<int> v, ans;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr); cout.tie(nullptr);

	int n, size = 0;
	cin >> n;

	v.push_back(-1);

	for (int i = 0; i < n; i++) {
		cin >> num[i];

		if (num[i] > v.back()) {
			v.push_back(num[i]);
			dp[i] = ++size;
		} else {
			auto iter = lower_bound(v.begin(), v.end(), num[i]);
			*iter = num[i];
			dp[i] = iter - v.begin();
		}
	}

	cout << size << '\n';

	int tmp = size;
	for (int i = n - 1; i >= 0; i--) {
		if (dp[i] == tmp) {
			tmp--;
			ans.push_back(num[i]);
		}
	}

	for (int i = size - 1; i >= 0; i--) {
		cout << ans[i] << ' ';
	}

	cout << '\n';

	return 0;
}