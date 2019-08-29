#include <iostream>
#include <vector>
#include <algorithm>
#define N 500001

using namespace std;

int dp[N] = { 0, };
bool chk[N] = { false, };

int main() {

    ios::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int n, a, b;
    cin >> n;

    vector<pair<int, int>> e;
    for (int i = 0; i < n; i++) {
        cin >> a >> b;
        e.push_back({a, b});
    }

    sort(e.begin(), e.end());

    vector<int> v;
    v.push_back(-1);

    int size = 0;
    for (int i = 0; i < e.size(); i++) {
        if (e[i].second > v.back()) {
            v.push_back(e[i].second);
            dp[i] = ++size;
        } else {
            auto j = lower_bound(v.begin(), v.end(), e[i].second);
            *j = e[i].second;
            dp[i] = j - v.begin();
        }
    }

    int max = 0, idx = 0;
    for (int i = 0; i < e.size(); i++) {
        if (dp[i] > max) {
            max = dp[i];
            idx = i;
        }
    }

    cout << n - max << '\n';

    for (int i = idx; i >= 0; i--) {
        if (dp[i] == max) {
            max--;
            chk[e[i].first] = true;
        }
    }

    for (int i = 0; i < e.size(); i++) {
        if (!chk[e[i].first]) {
            cout << e[i].first << '\n';
        }
    }

    return 0;
}