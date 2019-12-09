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

	queue<int> q;
	q.push(n);

	int time = 0, cnt = 0;

	while (!q.empty()) {
		int size = q.size();

		for (int i = 0; i < size; i++) {
			int top = q.front();
			visit[top] = true;
			q.pop();

			if (top == k) {
				cnt++;
			}

			int num = top * 2;
			if (num <= 100000 && !visit[num]) {
				q.push(num);
			}

			num = top - 1;
			if (num >= 0 && !visit[num]) {
				q.push(num);
			}

			num = top + 1;
			if (num <= 100000 && !visit[num]) {
				q.push(num);
			}
		}

		if (cnt) {
			break;
		}

		time++;
	}

	cout << time << "\n" << cnt << "\n";

	return 0;
}