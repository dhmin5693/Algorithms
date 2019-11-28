#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int>> tree[100001];
bool visit[2][100001] = { false, };

int maxDist = 0, idx = 0;

void dfs(int order, int node, int dist) {

	bool chk = true;
	for (pair<int, int> conn : tree[node]) {
		if (visit[order][conn.first]) {
			continue;
		}

		chk = false;
		visit[order][conn.first] = true;
		dfs(order, conn.first, dist + conn.second);
	}

	if (chk) {
		if (maxDist < dist) {
			maxDist = dist;
			idx = node;
		}
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;

	for (int i = 0, node, conn, dist; i < n; i++) {
		cin >> node;

		cin >> conn;
		while (conn != -1) {
			cin >> dist;
			tree[node].push_back({ conn, dist });
			tree[conn].push_back({ node, dist });
			cin >> conn;
		}
	}

	visit[0][1] = true;
	dfs(0, 1, 0);

	maxDist = 0;
	visit[1][idx] = true;
	dfs(1, idx, 0);

	cout << maxDist << "\n";

	return 0;
}