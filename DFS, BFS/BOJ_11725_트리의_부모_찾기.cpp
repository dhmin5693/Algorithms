#include <iostream>
#include <vector>
#include <stack>
using namespace std;

vector<int> tree[100001];
bool visit[100001] = { false, };
int parent[100001] = { 0, };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;

	for (int i = 0, n1, n2; i < n; i++) {
		cin >> n1 >> n2;
		tree[n1].push_back(n2);
		tree[n2].push_back(n1);
	}

	stack<int> st;
	st.push(1);
	visit[1] = true;

	int top;
	while (!st.empty()) {
		top = st.top();
		st.pop();

		for (int node : tree[top]) {
			if (visit[node]) {
				continue;
			}

			parent[node] = top;
			visit[node] = true;
			st.push(node);
		}
	}

	for (int i = 2; i <= n; i++) {
		cout << parent[i] << "\n";
	}

	return 0;
}