#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int ans = 0x7FFFFFFF;

int n, total;
int map[50][50] = { 0, };
int _map[50][50] = { 0, };
bool visit[50][50] = { 0, };

int dy[] = { 0, 1, 0, -1 };
int dx[] = { 1, 0, -1, 0 };

struct Virus {
	int y, x;
	int status;

	Virus(int y, int x, int status) {
		this->y = y;
		this->x = x;
		this->status = status;
	}
};

vector<Virus> v;
vector<int> pick;

void input() {
	int m;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> _map[i][j];

			if (_map[i][j] == 0) {
				total++;
			}

			if (_map[i][j] == 2) {
				v.push_back(Virus(i, j, 0));
			}
		}
	}

	int size = v.size();
	total += size - m;

	for (int i = 0; i < size - m; i++) {
		pick.push_back(0);
	}

	for (int i = 0; i < m; i++) {
		pick.push_back(1);
	}
}

void init() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			map[i][j] = _map[i][j];
			visit[i][j] = false;
		}
	}

	int size = v.size();
	for (int i = 0; i < size; i++) {
		if (pick[i]) {
			v[i].status = 1;
		} else {
			v[i].status = 0;
		}
	}
}

bool chk(int y, int x) {
	return y >= 0 && y < n && x >= 0 && x < n;
}

void bfs() {

	queue<pair<int, int> > q;

	int size = v.size(),
		sum = 0,
		time = -1;

	for (int i = 0; i < size; i++) {
		if (pick[i]) {
			q.push({ v[i].y, v[i].x });
			visit[v[i].y][v[i].x] = true;
			map[v[i].y][v[i].x] = 2;
		} else {
			map[v[i].y][v[i].x] = 0;
		}
	}

	while (!q.empty()) {

		time++;
		size = q.size();

		for (int i = 0; i < size; i++) {
			int y = q.front().first,
				x = q.front().second;

			q.pop();

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d],
					nx = x + dx[d];

				if (chk(ny, nx) && !visit[ny][nx] && map[ny][nx] == 0 && sum != total) {
					sum++;
					q.push({ ny, nx });
					visit[ny][nx] = true;
					map[ny][nx] = 2;
				}
			}
		}
	}

	if (total == sum) {
		ans = ans > time ? time : ans;
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	input();

	do {
		init();
		bfs();
	} while (next_permutation(pick.begin(), pick.end()));

	if (ans == 0x7FFFFFFF) ans = -1;
	cout << ans << '\n';

	return 0;
}