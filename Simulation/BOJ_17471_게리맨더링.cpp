#include <iostream>
#include <vector>
#include <queue>
#include <map>
using namespace std;

vector<int> population;
vector<vector<int> > conn;

vector<int> g[2];
map<int, int> group;
bool visit[10];

int ans = 0x7FFFFFFF;

void bfs(int gNum) {

	queue<int> q;
	q.push(g[gNum][0]);
	visit[g[gNum][0]] = true;

	while (!q.empty()) {
		int size = q.size();
		int front = q.front();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < conn[front].size(); j++) {
				int tmp = conn[front][j];
				if (!visit[tmp] && group[tmp] == gNum) {
					q.push(tmp);
					visit[tmp] = true;
				}
			}
		}

		q.pop();
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, t, tmp;
	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> t;
		population.push_back(t);
	}

	for (int i = 0; i < n; i++) {
		cin >> t;
		vector<int> v;

		for (int j = 0; j < t; j++) {
			cin >> tmp;
			v.push_back(tmp - 1);
		}
		conn.push_back(v);
	}

	for (int i = 1; i < (1 << n) - 2; i++) {

		if (ans == 0) {
			break;
		}

		g[0].clear();
		g[1].clear();

		for (int j = 0; j < n; j++) {
			if (i & (1 << j)) {
				g[0].push_back(j);
				group[j] = 0;
			} else {
				g[1].push_back(j);
				group[j] = 1;
			}
			visit[j] = false;
		}

		bfs(0);
		bfs(1);

		bool div = true;
		for (int j = 0; j < n; j++) {
			if (!visit[j]) {
				div = false;
				break;
			}
		}

		if (div) {
			int sum1 = 0, sum2 = 0, size;

			size = g[0].size();
			for (int j = 0; j < size; j++) {
				sum1 += population[g[0][j]];
			}

			size = g[1].size();
			for (int j = 0; j < size; j++) {
				sum2 += population[g[1][j]];
			}

			int tmp = sum1 - sum2;
			if (tmp < 0) tmp = -tmp;

			ans = ans > tmp ? tmp : ans;
		}
	}

	if (ans == 0x7FFFFFFF) ans = -1;

	cout << ans << "\n";

	return 0;
}