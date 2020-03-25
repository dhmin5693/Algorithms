#include <iostream>
#define MIN(a, b) (a) < (b) ? (a) : (b)
#define INF 100000000
using namespace std;

int map[101][101] = { 0, };

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, m;
	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (i != j) {
				map[i][j] = INF;
			}
		}
	}

	for (int i = 0, a, b, c; i < m; i++) {
		cin >> a >> b >> c;
		map[a][b] = MIN(map[a][b], c);
	}

	for (int mid = 1; mid <= n; mid++) {
		for (int start = 1; start <= n; start++) {
			for (int end = 1; end <= n; end++) {
				if (map[start][end] > map[start][mid] + map[mid][end]) {
					map[start][end] = map[start][mid] + map[mid][end];
				}
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cout << (map[i][j] == INF ? 0 : map[i][j]) << ' ';
		}
		cout << '\n';
	}

	return 0;
}