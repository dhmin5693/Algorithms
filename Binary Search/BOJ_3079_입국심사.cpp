#include <iostream>
#include <vector>
using namespace std;

typedef long long ll;

int n, m;
vector<int> v;

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int max = 0;
    cin >> n >> m;
    for (int i = 0, input; i < n; i++) {
        cin >> input;
        v.push_back(input);
        max = max < input ? input : max;
    }

    ll left = 1, right = (ll)max * (ll)m, mid = (left + right) / 2, ans = right, sum;

    while (left <= right) {

        sum = 0;
        for (int item : v) {
            sum += mid / (ll) item;
        }

        if (sum >= m) {
            ans = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }

        mid = (left + right) / 2;
    }

    cout << ans << "\n";

    return 0;
}
