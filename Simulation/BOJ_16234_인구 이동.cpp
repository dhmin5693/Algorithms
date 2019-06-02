#include <cstdio>
#include <queue>
#include <cmath>

using namespace std;

typedef pair<int, int> point;

const int dy[4] = { -1, 0, 1, 0 };
const int dx[4] = { 0, 1, 0, -1 };

bool check(point cur, point target, int n, int r, int l, const int(*a)[50], const bool(*visit)[50]) {

	// 탈출 조건
	// 범위 내에 없음 || 이미 연합을 구성한 지점(visit)
	if (target.first < 0 || target.first >= n || target.second < 0 || target.second >= n || visit[target.first][target.second]) {
		return false;
	}

	// 두 국가 간 인구수 차이(diff)가 r ~ l이면 true
	int diff = abs(a[cur.first][cur.second] - a[target.first][target.second]);
	return r <= diff && diff <= l;
}

int main() {

	int n, r, l;
	scanf("%d %d %d", &n, &r, &l);

	int a[50][50] = { 0, };

	// 입력
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			scanf("%d", &a[i][j]);
		}
	}

	int ans = 0;
	bool move = true;

	while (move) {
		move = false;

		bool visit[50][50] = { false, };

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {

				if (visit[i][j]) {
					continue;
				}

				bool open = false;
				queue<point> q, result;
				q.push(make_pair(i, j));
				result.push(make_pair(i, j));

				visit[i][j] = true;

				// 연합의 인구 수를 결정할 변수 sum
				int sum = a[i][j];

				while (!q.empty()) {
					point cur = q.front();
					q.pop();

					// 인접 국가와 연합이 가능한지 검사
					for (int d = 0; d < 4; ++d) {
						point target = make_pair(cur.first + dy[d], cur.second + dx[d]);

						if (check(cur, target, n, r, l, a, visit)) {
							open = move = true;

							q.push(target);
							result.push(target);

							visit[target.first][target.second] = true;
							sum += a[target.first][target.second];
						}
					}
				}

				// 한 번이라도 국경선을 연 경우
				if (open) {

					// 연합 인구 결정
					int num = sum / result.size();

					// 연합 인구 조정
					while (!result.empty()) {
						point cur = result.front();
						result.pop();

						a[cur.first][cur.second] = num;
					}
				}
			}
		}

		// 인구 이동이 발생한 경우
		if (move) {
			++ans;
		}
	}

	printf("%d\n", ans);
	return 0;
}