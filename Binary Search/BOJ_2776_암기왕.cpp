#include <iostream>
#include <algorithm>
using namespace std;

int note1[1000000] = { 0, };
int note2[1000000] = { 0, };

int n, m;

int bs(int target) {

    int left = 0, right = n - 1, mid = (left + right) / 2, num;

    while(left <= right) {

        num = note1[mid];

        if (target == num) {
            return 1;
        }

        if (target > num) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }

        mid = (left + right) / 2;
    }

    return 0;
}

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int tc;
    for (cin >> tc; tc > 0; tc--) {

        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> note1[i];
        }

        sort(note1, note1 + n);

        cin >> m;
        for (int i = 0; i < m; i++) {
            cin >> note2[i];
        }

        for (int i = 0; i < m; i++) {
            cout << bs(note2[i]) << "\n";
        }
    }

    return 0;
}
