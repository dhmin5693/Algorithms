/*
 * BOJ 2110. 공유기 설치
 *
 * 이분탐색 응용하여 해결했습니다.
 * 먼저 n, c, p 배열을 입력받은 후 p 배열을 오름차순으로 정렬합니다.
 * 만약 c가 2인 경우, p[0]와 p[n-1]에 공유기를 설치하는 것이 최대거리입니다.
 * c >= 3인 경우, p[0]에 하나, mid값 거리를 두며 하나씩 설치합니다.
 * 이분 탐색으로 정답이 도출된다 하더라도 더 큰 거리값이 나타날 수 있기 때문에
 * 조건을 찾자마자 루프를 빠져나오지 않고 계속해서 최적해를 탐색해 나갑니다.
 */
#include <cstdio>
#include <algorithm>

int p[200000] = { 0, };

int main()
{
	int n, c;
	scanf("%d %d", &n, &c);

	for (int i = 0; i < n; ++i) {
		scanf("%d", &p[i]);
	}

	std::sort(p, p + n);
	
	// left와 right를 설정합니다.
	// 이 값은 p의 index가 아니라 최대 거리를 의미합니다.
	int left = 0, right = p[n - 1] - p[0];
	int ans = 0;

	while (left <= right) {
		int mid = (left + right) / 2;
		int idx = 0; // 마지막으로 공유기를 설치한 집의 인덱스입니다. 첫 집에 설치를 하고 시작합니다.
		int cnt = 1; // 첫 집에 설치했으므로 1로 시작합니다.

		// 1 ~ (n-1) 항에서 이전에 설치했던 집과의 거리가 mid 이상인 지점을 찾습니다.
		// 찾으면 설치 수를 증가시키고 idx를 갱신합니다.
		for (int i = 1; i < n; ++i) {
			if (p[i] - p[idx] >= mid) {
				++cnt;
				idx = i;
			}
		}

		// 조건이 맞아도(cnt == c) 혹시 있을지 모를 최적해를 더 찾습니다.
		if (cnt >= c) {
			left = mid + 1;
			ans = mid;
		}
		else {
			right = mid - 1;
		}
	}

	printf("%d\n", ans);

	return 0;
}