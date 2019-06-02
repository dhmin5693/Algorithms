#include <cstdio>
#include <cmath>
#include <vector>
#include <queue>
#include <algorithm>

#define INF 0x7FFFFFFF
#define MIN(a, b) (a) < (b) ? (a) : (b)

using namespace std;

int dy[4] = { -1, 0, 0, 1 };
int dx[4] = { 0, -1, 1, 0 };

int space[20][20] = { 0, };
int gx, gy;

// 유효범위
bool rangeCheck(int n, int y, int x) {

	return y >= 0 && y < n && x >= 0 && x < n;
}

// 우선순위 - 위쪽, 왼쪽
bool compare(pair<int, int> &a, pair<int, int> &b) {

	if (a.first < b.first)
		return true;

	if (a.first == b.first)
		return a.second < b.second;

	return false;
}

int bfs(int n, int scale) {

	bool visit[20][20] = { false, };
	visit[gy][gx] = true;

	queue<pair<int, int> > q;
	vector<pair<int, int> > pq;

	q.push(make_pair(gy, gx));

	int time = 0;

	while (!q.empty()) {

		int size = q.size();
		
		++time;

		bool flag = false;

		for (int i = 0; i < size; ++i) {

			int y = q.front().first;
			int x = q.front().second;
			q.pop();

			for (int d = 0; d < 4; ++d) {

				int cy = y + dy[d], cx = x + dx[d];

				if (rangeCheck(n, cy, cx) && !visit[cy][cx]) {

					visit[cy][cx] = true;

					if (space[cy][cx] > 0 && space[cy][cx] < scale) {
						pq.push_back(make_pair(cy, cx));
						flag = true;
					}
					else if (space[cy][cx] == 0 || space[cy][cx] == scale) {
						q.push(make_pair(cy, cx));
					}
				}
			}
		}

		if (flag) {
			break;
		}
	}

	if (pq.size() == 0) {
		return -1;
	}

	sort(pq.begin(), pq.end(), compare);
	
	space[gy][gx] = 0;

	gy = pq[0].first;
	gx = pq[0].second;

	space[gy][gx] = 9;

	return time;
}

int main()
{
	int n;
	scanf("%d", &n);

	int num = 0, scale = 2;

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			scanf("%d", &space[i][j]);

			if (space[i][j] == 9) {
				gy = i;
				gx = j;
			}
			else if (space[i][j] != 0) {
				++num;
			}
		}
	}

	int time = 0, eat = 0;
	bool flag = true;

	while (num > 0 && flag) {
		
		int shortest = bfs(n, scale);

		if (shortest == -1) {
			flag = false;
		} else {
			++eat;
			--num;

			if (eat == scale) {
				++scale;
				eat = 0;
			}

			time += shortest;
		}
	}

	printf("%d\n", time);

	return 0;
}