#include <cstdio>
#include <queue>
#define INF 0x7FFFFFFF
using namespace std;

int map[1000][1000] = { 0, };
bool visit[1000][1000][2] = { false, };

int dy[] = { 0, 1, 0, -1 };
int dx[] = { 1, 0, -1, 0 };

struct Point {
	int y, x;
	bool flag; // 0 : unbroken, 1 : broken
	Point(int y, int x, bool flag) : y(y), x(x), flag(flag) {}
};

bool range(int y, int x, int n, int m) {
	return y >= 0 && y < n && x >= 0 && x < m;
}

int main() {
	freopen("input.txt", "r", stdin);

	int n, m;
	scanf("%d %d", &n, &m);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%1d", &map[i][j]);
		}
	}

	int ans = 0;
	queue<Point> q;

	q.push(Point(0, 0, false));
	visit[0][0][0] = true;
	bool escape = false;

	while (!q.empty() && !escape) {
		int size = q.size();
		ans++;
		for (int i = 0; i < size; i++) {
			Point top = q.front();
			q.pop();

			for (int d = 0; d < 4; d++) {
				int ty = top.y + dy[d];
				int tx = top.x + dx[d];

				if (!range(ty, tx, n, m) || visit[ty][tx][top.flag]) {
					continue;
				}

				if (map[ty][tx] == 1 && !top.flag) {
					q.push(Point(ty, tx, true));
					visit[ty][tx][1] = true;
				} else if (map[ty][tx] == 0) {
					q.push(Point(ty, tx, top.flag));
					visit[ty][tx][top.flag] = true;
				}

				if (ty == n - 1 && tx == m - 1) {
					escape = true;
					break;
				}
			}
		}
	}

	if (n == 1 && m == 1) {
		printf("1\n");
		return 0;
	}

	ans++;

	if (!escape) {
		ans = -1;
	}

	printf("%d\n", ans);

	return 0;
}