#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

int num[1000005];
vector<int> v;

int main() {
    
    int n, ans = 0;
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        scanf("%d", &num[i]);
    }

    v.push_back(-2100000000);

    for (int i = 0; i < n; i++) {
        if (v.back() < num[i]) {
            v.push_back(num[i]);
            ans++;
        } else {
            auto j = lower_bound(v.begin(), v.end(), num[i]);
            *j = num[i];
        }
    }

    printf("%d\n", ans);

    return 0;
}