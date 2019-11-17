#include <cstdio>
#include <queue>
#include <algorithm>
using namespace std;

int n;
queue<pair<int, int>> q;
vector<int> ans;

short map[25][25] = { 0, };
const int dy[] = { -1, 0, 1, 0 };
const int dx[] = { 0, 1, 0, -1 };

bool check(pair<int, int> p) {
	int y = p.first;
	int x = p.second;

	return y >= 0 && y < n && x >= 0 && x < n && map[y][x];
}

int main() {
	freopen("input.txt", "r", stdin);

	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%1d", &map[i][j]);
		}
	}

	int cnt;
	pair<int, int> top, p;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j]) {
				q.push({ i, j });
				cnt = map[i][j] = 0;

				while (!q.empty()) {
					top = q.front();
					q.pop();
					cnt++;
					for (int d = 0; d < 4; d++) {
						p = { top.first + dy[d], top.second + dx[d] };
						if (check(p)) {
							q.push(p);
							map[p.first][p.second] = 0;
						}
					}
				}

				ans.push_back(cnt);
			}
		}
	}

	sort(ans.begin(), ans.end());

	printf("%d\n", ans.size());

	for (int _ans : ans) {
		printf("%d\n", _ans);
	}

	return 0;
}