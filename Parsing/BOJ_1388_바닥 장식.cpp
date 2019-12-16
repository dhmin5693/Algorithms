#include <iostream>
using namespace std;

char map[150][150] = { 0, };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, m, ans = 0, cnt;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
		}
	}

	for (int y = 0; y < n; y++) {
		cnt = 0;
		for (int x = 0; x < m; x++) {
			if (map[y][x] == '-') {
				cnt++;
			} else {
				if (cnt >= 1) {
					ans++;
				}
				cnt = 0;
			}
		}
		if (cnt >= 1) {
			ans++;
		}
	}

	for (int x = 0; x < m; x++) {
		cnt = 0;
		for (int y = 0; y < n; y++) {
			if (map[y][x] == '|') {
				cnt++;
			} else {
				if (cnt >= 1) {
					ans++;
				}
				cnt = 0;
			}
		}
		if (cnt >= 1) {
			ans++;
		}
	}

	cout << ans << "\n";

	return 0;
}