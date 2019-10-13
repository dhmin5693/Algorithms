#include <iostream>
#include <vector>
using namespace std;

int map[10][10];
bool visit[10][10] = { false, };
int maxPaper = 0;
int paper = 0;
int cnt[5];
int ans = 0x7FFFFFFF;

vector<pair<int, int>> p;
int pSize;

bool inBound(int y, int x) {
	return y >= 0 && y < 10 && x >= 0 && x < 10;
}

bool attach(int size, int y, int x) {

	if (cnt[size - 1] >= 5) {
		return false;
	}

	for (int i = y; i < y + size; i++) {
		for (int j = x; j < x + size; j++) {
			if (!inBound(i, j) || !map[i][j] || visit[i][j]) {
				return false;
			}
		}
	}

	for (int i = y; i < y + size; i++) {
		for (int j = x; j < x + size; j++) {
			visit[i][j] = true;
			paper++;
		}
	}

	cnt[size - 1]++;

	return true;
}

void dettach(int size, int y, int x) {
	for (int i = y; i < y + size; i++) {
		for (int j = x; j < x + size; j++) {
			visit[i][j] = false;
			paper--;
		}
	}

	cnt[size - 1]--;
}

void dfs(int idx) {

	if (paper >= maxPaper) {
		int tmp = 0;
		for (int i = 0; i < 5; i++) {
			tmp += cnt[i];
		}

		if (tmp != 0) {
			ans = ans > tmp ? tmp : ans;
		}
		return;
	}

	if (idx >= pSize) {
		return;
	}

	int y = p[idx].first;
	int x = p[idx].second;

	if (visit[y][x] || !map[y][x]) {
		dfs(idx + 1);
		return;
	}

	for (int i = 5; i >= 1; i--) {
		if (attach(i, y, x)) {
			dfs(idx + 1);
			dettach(i, y, x);
		}
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			cin >> map[i][j];

			if (map[i][j]) {
				maxPaper++;
				p.push_back({ i, j });
			}
		}
	}

	if (maxPaper == 0) {
		cout << "0\n";
		return 0;
	}

	pSize = p.size();
	dfs(0);

	if (ans == 0x7FFFFFFF) {
		ans = -1;
	}

	cout << ans << '\n';

	return 0;
}