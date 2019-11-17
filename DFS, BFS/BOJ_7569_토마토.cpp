#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

struct Point {
	int z, y, x;
	Point(int z, int y, int x) {
		this->z = z;
		this->y = y;
		this->x = x;
	}
};

int m, n, h;
queue<Point> q;

short map[100][100][100] = { 0, };

int dir[][6] = {
	{ 1, 0, 0 }, // À§
	{ -1, 0, 0 }, //¾Æ·¡ 
	{ 0, 1, 0 },
	{ 0, -1, 0 },
	{ 0, 0, 1 },
	{ 0, 0, -1 }
};

bool check(Point p) {
	return p.z >= 0 && p.z < h && p.y >= 0 && p.y < n && p.x >= 0 && p.x < m && map[p.z][p.y][p.x] == 0;
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	cin >> m >> n >> h;

	int total = 0, cnt = 0, ans = 0;

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				cin >> map[i][j][k];
				if (map[i][j][k] != -1) {
					total++;
				}
				if (map[i][j][k] == 1) {
					q.push(Point(i, j, k));
					cnt++;
				}
			}
		}
	}

	if (cnt < total) {
		while (!q.empty() && cnt < total) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point p = q.front();
				q.pop();

				for (int d = 0; d < 6; d++) {
					Point tmp = Point(p.z + dir[d][0], p.y + dir[d][1], p.x + dir[d][2]);
					if (check(tmp)) {
						map[tmp.z][tmp.y][tmp.x] = 1;
						cnt++;
						q.push(tmp);
					}
				}
			}

			ans++;
		}
	}

	if (cnt != total) {
		ans = -1;
	}

	cout << ans << '\n';

	return 0;
}