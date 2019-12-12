#include <iostream>
#include <string>
#include <map>
#include <queue>
using namespace std;

struct Point {
	int y, x, idx, cnt;
};

const int dy[] = { 0, 0, 1, -1 };
const int dx[] = { 1, -1, 0, 0 };

Point move(Point p, int d) {
	p.y += dy[d];
	p.x += dx[d];
	p.idx = p.y * 3 + p.x;
	p.cnt++;
	return p;
}

bool range(Point p) {
	return p.y >= 0 && p.y < 3 && p.x >= 0 && p.x < 3;
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	string puz, ans = "123456789";
	Point p;

	bool num[10] = { false, };

	for (int i = 0, input; i < 9; i++) {
		cin >> input;
		num[input] = true;
		puz.push_back(input + '0');
		if (input == 0) {
			p.y = i / 3;
			p.x = i % 3;
			p.idx = i;
			p.cnt = 0;
		}
	}

	for (int i = 0; i <= 9; i++) {
		if (!num[i]) {
			ans[i - 1] = '0';
			break;
		}
	}

	map<string, int> visit;
	visit[puz] = 1;

	queue<pair<string, Point>> q;
	q.push({ puz, p });

	while (!q.empty()) {
		pair<string, Point> top = q.front();

		if (top.first.compare(ans) == 0) {
			break;
		}

		q.pop();

		for (int d = 0; d < 4; d++) {
			Point np = move(top.second, d);
			if (!range(np)) continue;

			string npuz = top.first;
			char tmp = npuz[top.second.idx];
			npuz[top.second.idx] = npuz[np.idx];
			npuz[np.idx] = tmp;

			if (visit[npuz] == 1) continue;
			visit[npuz] = 1;
			q.push({ npuz, np });
		}
	}

	if (!q.empty()) {
		cout << q.front().second.cnt << "\n";
	} else {
		cout << "-1\n";
	}

	return 0;
}