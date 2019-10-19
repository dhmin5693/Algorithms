#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int map[50][50] = { 0, };
int tmp[50][50] = { 0, };

int dy[] = { 1, 0, -1, 0 };
int dx[] = { 0, 1, 0, -1 };

struct Arr {
	int r, c, s;
} arr[10];

void init(int n, int m) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			tmp[i][j] = map[i][j];
		}
	}
}

void rotate(int r, int c, int s) {
	r--; c--;

	for (int i = 1; i <= s; i++) {
		int dir = 0,
			y = r - i, x = c - i,
			start = tmp[r - i][c - i],
			box1 = 2 * (i + 1) - 1,
			box2 = 2 * i - 1;

		int cnt = box1 * box1 - box2 * box2;

		for (int i = 1; dir < 4; i++) {
			tmp[y][x] = tmp[y + dy[dir]][x + dx[dir]];
			y += dy[dir];
			x += dx[dir];

			if (i == cnt / 4) {
				dir++;
				i = 0;
			}
		}

		tmp[r - i][c - i + 1] = start;
	}
}

int getValue(int n, int m) {

	int ret = 0x7FFFFFFF, sum;
	for (int i = 0; i < n; i++) {
		sum = 0;
		for (int j = 0; j < m; j++) {
			sum += tmp[i][j];
		}
		ret = ret > sum ? sum : ret;
	}
	return ret;
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, m, k, r, c, s, ans = 0x7FFFFFFF, val;
	cin >> n >> m >> k;

	vector<int> v;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
		}
	}

	for (int i = 0; i < k; i++) {
		cin >> arr[i].r >> arr[i].c >> arr[i].s;
		v.push_back(i);
	}

	do {
		init(n, m);

		for (int i = 0; i < k; i++) {
			rotate(arr[v[i]].r, arr[v[i]].c, arr[v[i]].s);
		}

		val = getValue(n, m);
		ans = ans > val ? val : ans;

	} while (next_permutation(v.begin(), v.end()));

	cout << ans << '\n';

	return 0;
}