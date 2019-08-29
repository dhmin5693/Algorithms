/*
* BOJ 2613. 숫자구슬
*
* 정답이 되는 최대값의 최소를 이분탐색으로 도출했습니다.
* mid값을 최대값으로 두고, 각 원소를 더할 때 mid가 넘어가면 새로운 그룹을 형성합니다.
* 각 그룹 당 숫자는 정답을 도출한 뒤 한 번 더 반복문을 돌려서 구했습니다.
*/
#include <cstdio>

int b[300] = { 0, };
int c[300] = { 0, };

int main()
{
	int n, m, ans = 0x7FFFFFFF, left = 0, right = 0;
	scanf("%d %d", &n, &m);

	for (int i = 0; i < n; ++i) {
		scanf("%d", &b[i]);
		right += b[i];
	}

	// 최소값 0, 최대값 right = 구슬들의 합
	while (left <= right) {
		int mid = (left + right) / 2;
		int cnt = 1;
		int flag = false;

		// 각 원소를 더하여 그룹의 개수 측정
		for (int i = 0, sum = 0; i < n; ++i) {

			// 하나의 원소가 mid값 이상이면 left 재조정
			if (b[i] > mid) {
				flag = true;
				break;
			}

			sum += b[i];

			// 새로운 그룹으로 처리할 때 그룹 개수 1개 추가
			if (sum > mid) {
				++cnt;
				sum = b[i];
			}
		}

		if (flag || cnt > m) {
			left = mid + 1;
		}
		else {
			right = mid - 1;
			ans = mid < ans ? mid : ans;
		}
	}

	int idx = 0, max = 0, maxIdx = -1;

	// 그룹 당 포함하는 원소 개수 구하기
	for (int i = 0, sum = 0; i < n; ++i) {
		sum += b[i];

		if (sum > ans) {
			++idx;
			sum = b[i];
		}

		++c[idx];

		if (c[idx] > max) {
			max = c[idx];
			maxIdx = idx;
		}
	}

	if (idx < m && maxIdx != -1) {
		for (int i = 0; i < m; ++i) {
			if (c[i] == 0) {
				++c[i];
				--c[maxIdx];
			}
		}
	}

	printf("%d\n", ans);
	for (int i = 0; i < m; ++i) {
		printf("%d ", c[i]);
	}
	printf("\n");

	return 0;
}