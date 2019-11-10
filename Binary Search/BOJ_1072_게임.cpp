#include <iostream>
using namespace std;

typedef long long ll;

int getRate(int win, int game) {
    long long rate = 100 * (ll)win / (ll)game;
    return (int) rate;
}

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int x, y, z;
    cin >> x >> y;

    z = getRate(y, x);

    if (z == 99) {
        cout << "-1\n";
        return 0;
    }

    int left = 1, right = 1000000000, mid = (left + right) / 2;
    int ans = -1;

    while(left <= right) {

        int rate = getRate(y + mid, x + mid);

        if (rate > z) {
            right = mid - 1;
            ans = mid;
        } else {
            left = mid + 1;
        }

        mid = (left + right) / 2;
    }

    cout << ans << "\n";

    return 0;
}
