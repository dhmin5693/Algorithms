#include <iostream>
using namespace std;

typedef long long ll;
typedef long double ld;

int main() {
	freopen("input.txt", "r", stdin);
	ll n, l, w, h;
	cin >> n >> l >> w >> h;

	ld left = 0.0, right = 1e15, mid;

	for (int i = 0; i < 10000; i++) {
		mid = (left + right) / 2;
		ll xCnt = (ll)(l / mid);
		ll yCnt = (ll)(w / mid);
		ll zCnt = (ll)(h / mid);

		if (xCnt * yCnt * zCnt < n) {
			right = mid;
		} else {
			left = mid;
		}
	}

	printf("%.10Lf", right);

	return 0;
}