#include <iostream>
using namespace std;

int dp[1000001] = { 0, };

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int n, cnt = 0;
    cin >> n;

    for (int i = 0, idx; i < n; i++) {
        cin >> idx;
        dp[idx] = dp[idx - 1] + 1;

        if (cnt < dp[idx])
            cnt = dp[idx];
    }

    cout << n - cnt << '\n';

    return 0;
}
