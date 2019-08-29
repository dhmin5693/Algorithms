/*
 * BOJ 2805. 나무 자르기
 *
 * 이분탐색으로 자를 지점을 탐색합니다 (O(lgN))
 * 해당 지점에서 잘랐을 때 합을 구합니다 (O(N))
 * O(NlgN)으로 답을 도출할 수 있습니다.
 */
#include <cstdio>
#define MAX(a, b) (a) > (b) ? (a) : (b)

typedef long long ll;
ll tree[1000000] = { 0, };

int main()
{
	ll n, m, max = 0, answer = 0;
	scanf("%lld %lld", &n, &m);

	for (int i = 0; i < n; i++) {
		scanf("%lld", &tree[i]);
		max = MAX(max, tree[i]);
	}

	ll left = 0, right = max;

	while (left <= right) {

		ll sum = 0, mid = (left + right) / 2;

		for (int i = 0; i < n; ++i) {
			sum += (tree[i] - mid > 0) ? tree[i] - mid : 0;
		}

		if (sum >= m) {
			left = mid + 1;
			answer = mid;
		}
		else {
			right = mid - 1;
		}
	}

	printf("%d\n", answer);

	return 0;
}