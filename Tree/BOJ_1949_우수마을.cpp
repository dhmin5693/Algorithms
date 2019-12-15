#include <iostream>
#include <vector>
#define MAX(a, b) (a) > (b) ? (a) : (b)
using namespace std;

vector<int> cnt;
vector<int> node[10001];
bool visit[10001] = { false, };
int dp[2][10001] = { 0, };

// Tree DP 구성
void dfs(int cur) {

	if (visit[cur]) {
		return;
	}

	// 초기값 정의
	// dp[0][idx] : 일반 마을
	// dp[1][idx] : 우수 마을
	visit[cur] = true;
	dp[0][cur] = 0;
	dp[1][cur] = cnt[cur];

	for (int next : node[cur]) {
		if (visit[next]) continue;

		dfs(next);
		dp[0][cur] += MAX(dp[0][next], dp[1][next]);
		dp[1][cur] += dp[0][next];
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;

	cnt.push_back(0);

	for (int i = 0, input; i < n; i++) {
		cin >> input;
		cnt.push_back(input);
	}

	for (int i = 0, a, b; i < n; i++) {
		cin >> a >> b;
		node[a].push_back(b);
		node[b].push_back(a);
	}

	dfs(1);

	cout << (MAX(dp[0][1], dp[1][1])) << "\n";

	return 0;
}