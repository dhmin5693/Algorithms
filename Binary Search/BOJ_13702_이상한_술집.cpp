#include <iostream>
#include <vector>
using namespace std;

typedef long long ll;
vector<int> sul;

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int n, k;
    cin >> n >> k;

    ll left = 0L, right = 0L;

    for (int i = 0, input; i < n; i++) {
        cin >> input;
        sul.push_back(input);

        right += (ll) input;
    }

    ll mid = (left + right) / 2;
    int sum;

    while(left <= right) {

        sum = 0;
        for (int item : sul) {
            sum += item / mid;
        }

        if (sum < k) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }

        mid = (left + right) / 2;
    }

    cout << mid << '\n';

    return 0;
}
