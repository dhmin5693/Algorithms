#include <iostream>
#include <queue>
using namespace std;

bool visit[100001] = { false, };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, k;
	cin >> n >> k;

	queue<pair<int, int>> q;
	q.push({ n, 0 });
	visit[n] = true;

	while (!q.empty()) {
		pair<int, int> top = q.front();

		if (top.first == k) {
			break;
		}

		q.pop();

		int num = top.first * 2;
		if (num <= 100000 && !visit[num]) {
			q.push({ num, top.second });
			visit[num] = true;
		}

		num = top.first - 1;
		if (num >= 0 && !visit[num]) {
			q.push({ num, top.second + 1 });
			visit[num] = true;
		}

		num = top.first + 1;
		if (num <= 100000 && !visit[num]) {
			q.push({ num, top.second + 1 });
			visit[num] = true;
		}
	}

	cout << q.front().second << "\n";

	return 0;
}