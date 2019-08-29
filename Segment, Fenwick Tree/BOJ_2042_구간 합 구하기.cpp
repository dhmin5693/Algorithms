#include <iostream>
#define MAX 1000001

using namespace std;

typedef long long ll;

ll num[MAX] = { 0, };
ll fen[MAX * 2] = { 0, };

void update(int i, int _num) {
	while (i <= MAX) {
		fen[i] += _num;
		i += (i & -i);
	}
}

ll sum(int i) {
	ll ans = 0;
	while (i > 0) {
		ans += fen[i];
		i -= (i & -i);
	}
	return ans;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(0), cout.tie(0);

	int n, m, k;
	cin >> n >> m >> k;

	for (int i = 1; i <= n; i++) {
		cin >> num[i];
		update(i, num[i]);
	}

	int a, b, c;
	for (int i = 0; i < m + k; i++) {
		cin >> a >> b >> c;

		if (a == 1) {
			update(b, c - num[b]);
			num[b] = c;
		} else {
			cout << sum(c) - sum(b - 1) << '\n';
		}
	}

	return 0;
}