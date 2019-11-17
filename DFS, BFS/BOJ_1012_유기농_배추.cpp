#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int m, n;
queue<pair<int, int>> q;
vector<int> ans;

short map[50][50] = { 0, };
const int dy[] = { -1, 0, 1, 0 };
const int dx[] = { 0, 1, 0, -1 };

bool check(pair<int, int> p) {
	int y = p.first;
	int x = p.second;

	return y >= 0 && y < n && x >= 0 && x < m && map[y][x];
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int t;
	cin >> t;

	for (int tc = 0; tc < t; tc++) {
		int k, cnt = 0;
		cin >> m >> n >> k;

		for (int i = 0, y, x; i < k; i++) {
			cin >> x >> y;
			map[y][x] = 1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j]) {
					cnt++;
					q.push({ i, j });
					map[i][j] = 0;

					while (!q.empty()) {
						pair<int, int> p = q.front();
						q.pop();

						for (int d = 0; d < 4; d++) {
							pair<int, int> tmp = { p.first + dy[d], p.second + dx[d] };
							if (check(tmp)) {
								q.push(tmp);
								map[tmp.first][tmp.second] = 0;
							}
						}
					}
				}
			}
		}

		cout << cnt << '\n';
	}

	return 0;
}