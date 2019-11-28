#include <iostream>
#include <vector>
using namespace std;

vector<pair<int, int>> tree[10001];
bool visit[2][10001] = { false, };

int maxDist = 0, idx = 0;

void dfs(int order, int parent, int dist) {

	bool chk = true;
	for (pair<int, int> child : tree[parent]) {
		if (visit[order][child.first]) {
			continue;
		}

		chk = false;
		visit[order][child.first] = true;
		dfs(order, child.first, dist + child.second);
	}

	if (chk) {
		if (maxDist < dist) {
			maxDist = dist;
			idx = parent;
		}
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;

	for (int i = 0, parent, child, dist; i < n; i++) {
		cin >> parent >> child >> dist;
		tree[parent].push_back({ child, dist });
		tree[child].push_back({ parent, dist });
	}

	visit[0][1] = true;
	dfs(0, 1, 0);

	maxDist = 0;
	visit[1][idx] = true;
	dfs(1, idx, 0);

	cout << maxDist << "\n";

	return 0;
}