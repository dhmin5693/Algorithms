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

		int dp[1000] = { 0, }, // �ִ�ġ�� ������ DP
			time[1000] = { 0, }, // �� �ǹ� �Ǽ��� �ʿ��� �ð�
			indegree[1000] = { 0, }; // ���� ����

		vector<int> next[1000]; // �Ǽ� ������ ����� ���� �迭

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
			// ���� ������ 0�� ��带 ť�� ����
			if (!indegree[i]) {
				q.push(i);
			}
		}

		while (!q.empty()) {
			int top = q.front();
			q.pop();

			// q�� top�� ��ȣ�� ���� ��忡 ����� �ٸ� ��� Ž��
			// top�� ���������Ƿ� ���� ������ �ϳ� �����ϰ�
			// ���� ������ 0�� ��带 q�� ����
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