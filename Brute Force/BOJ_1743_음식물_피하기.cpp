#include <iostream>
#include <queue>
using namespace std;

int map[100][100] = { 0, };
int visit[100][100] = { false, };

int dy[] = { 0, 0, 1, -1 };
int dx[] = { 1, -1, 0, 0 };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, m, k, ans = 0;
	cin >> n >> m >> k;

	for (int i = 0, r, c; i < k; i++) {
		cin >> r >> c;
		map[r - 1][c - 1] = 1;
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j]) {
				queue<pair<int, int>> q;
				q.push({ i, j });
				visit[i][j] = true;
				int cnt = 0;

				while (!q.empty()) {
					pair<int, int> top = q.front();
					q.pop();
					cnt++;

					for (int d = 0; d < 4; d++) {
						int ty = top.first + dy[d];
						int tx = top.second + dx[d];

						if (ty >= 0 && ty < n && tx >= 0 && tx < m && map[ty][tx] && !visit[ty][tx]) {
							q.push({ ty, tx });
							visit[ty][tx] = true;
						}
					}
				}

				ans = ans < cnt ? cnt : ans;
			}
		}
	}

	cout << ans << "\n";

	return 0;
}