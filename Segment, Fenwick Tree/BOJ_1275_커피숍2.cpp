#include <cstdio>
#define MAX 100001

using namespace std;

typedef long long ll;

ll num[MAX] = { 0, };
ll seg[MAX * 4] = { 0, };

void init(int idx, int start, int end) {
	if (start == end) {
		seg[idx] = num[start];
		return;
	}

	int mid = (start + end) / 2;
	int next = (start + end) * 2;

	init(next, start, mid);
	init(next + 1, mid + 1, end);

	seg[idx] = seg[next] + seg[next + 1];
}

void update(int pos, ll val, int idx, int start, int end) {
	if (pos < start || pos > end) return;
	if (start == end) {
		seg[idx] = val;
		return;
	}

	int mid = (start + end) / 2;
	int next = (start + end) * 2;

	update(pos, val, next, start, mid);
	update(pos, val, next + 1, mid + 1, end);

	seg[idx] = seg[next] + seg[next + 1];
}

ll sum(int left, int right, int idx, int start, int end) {
	if (left > end || right < start) return 0;
	if (left <= start && end <= right) return seg[idx];

	int mid = (start + end) / 2;
	int next = (start + end) * 2;

	return sum(left, right, next, start, mid) + sum(left, right, next + 1, mid + 1, end);
}

int main() {

	int n, q;
	scanf("%d %d", &n, &q);

	for (int i = 1; i <= n; i++) {
		scanf("%lld", &num[i]);
	}

	init(1, 1, n);

	int x, y, a, b;
	for (int i = 0; i < q; i++) {
		scanf("%d %d %d %d", &x, &y, &a, &b);

		if (x > y) {
			x ^= y;
			y ^= x;
			x ^= y;
		}

		printf("%lld\n", sum(x, y, 1, 1, n));
		update(a, b, 1, 1, n);
	}

	return 0;
}