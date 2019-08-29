#include <cstdio>
#include <vector>
#include <queue>

#define MAX(a, b) (a) > (b) ? (a) : (b)

using namespace std;

int main() {

	int t;

	for (scanf("%d", &t); t > 0; t--) {
		int n, k;
		scanf("%d %d", &n, &k);

		int dp[1000] = { 0, }, // 최대치를 결정할 DP
			time[1000] = { 0, }, // 각 건물 건설에 필요한 시간
			indegree[1000] = { 0, }; // 진입 간선

		vector<int> next[1000]; // 건설 순서를 기록할 동적 배열

		for (int i = 0; i < n; ++i) {
			int d;
			scanf("%d", &d);
			dp[i] = time[i] = d;
		}

		for (int i = 0; i < k; ++i) {
			int x, y;
			scanf("%d %d", &x, &y);
			--x, --y;
			next[x].push_back(y);
			++indegree[y];
		}

		queue<int> q;

		for (int i = 0; i < n; ++i) {
			// 진입 간선이 0인 노드를 큐에 삽입
			if (!indegree[i]) {
				q.push(i);
			}
		}

		while (!q.empty()) {
			int top = q.front();
			q.pop();

			// q의 top의 번호를 갖는 노드에 연결된 다른 노드 탐색
			// top을 제거했으므로 진입 간선을 하나 제거하고
			// 진입 간선이 0인 노드를 q에 삽입
			for (int i = 0; i < next[top].size(); ++i) {
				int nodeNum = next[top][i];

				dp[nodeNum] = MAX(dp[nodeNum], dp[top] + time[nodeNum]);

				if (--indegree[nodeNum] == 0) {
					q.push(nodeNum);
				}
			}
		}

		int w;
		scanf("%d", &w);
		printf("%d\n", dp[w - 1]);
	}

	return 0;
}