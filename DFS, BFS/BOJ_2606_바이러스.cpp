#include <iostream>
#include <queue>
using namespace std;

vector<int> v[101];
bool visit[101] = { false, };
queue<int> q;

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int c, // 컴퓨터의 수
		n; // 네트워크 쌍의 수

	cin >> c >> n;

	for (int i = 0, c1, c2; i < n; i++) {
		cin >> c1 >> c2;
		v[c1].push_back(c2);
		v[c2].push_back(c1);
	}

	q.push(1);
	visit[1] = true;

	int top, cnt = -1;
	while (!q.empty()) {
		top = q.front();
		q.pop();

		cnt++;

		for (int next : v[top]) {
			if (!visit[next]) {
				q.push(next);
				visit[next] = true;
			}
		}

	}

	cout << cnt << "\n";

	return 0;
}