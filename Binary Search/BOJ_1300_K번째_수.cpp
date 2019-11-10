#include <iostream>
#define MIN(a, b) (a) < (b) ? (a) : (b)
using namespace std;

typedef long long ll;

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int n, k;
    cin >> n >> k;

    ll left = 1, right = (ll)n * (ll)n, mid, cnt, ans = right;

    while(left <= right) {
        mid = (left + right) / 2;

        cnt = 0;
        for(int i = 1; i <= n; i++) {
            cnt += MIN(n, mid / i);
        }

        if (cnt >= k) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    cout << ans << "\n";

    return 0;
}
