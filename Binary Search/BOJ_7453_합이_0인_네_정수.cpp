#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long ll;

vector<ll> v[4];
ll sum[16000000];

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int n, last;
    cin >> n;

    last = n * n;

    for (int i = 0, input; i < n; i++) {
        for (int j = 0; j < 4; j++) {
            cin >> input;
            v[j].push_back(input);
        }
    }

    int idx = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            sum[idx++] = v[2][i] + v[3][j];
        }
    }

    sort(sum, sum + last);

    ll idx1, idx2, val, ans = 0LL;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            val = v[0][i] + v[1][j];
            idx1 = lower_bound(sum, sum + last, -val) - sum;
            idx2 = upper_bound(sum, sum + last, -val) - sum;

            if (val + sum[idx1] == 0) {
                ans += idx2 - idx1;
            }
        }
    }

    cout << ans << "\n";

    return 0;
}
