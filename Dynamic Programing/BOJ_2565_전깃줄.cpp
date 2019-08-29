#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {

    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

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

    int ans = 0;

    for (int i = 0; i < e.size(); i++) {
        if (e[i].second > v.back()) {
            v.push_back(e[i].second);
            ans++;
        } else {
            auto j = lower_bound(v.begin(), v.end(), e[i].second);
            *j = e[i].second;
        }
    }

    printf("%d\n", n - ans);

    return 0;
}