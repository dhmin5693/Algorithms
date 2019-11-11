#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> card;
vector<int> v;

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int n, m;
    cin >> n;

    for (int i = 0, input; i < n; i++) {
        cin >> input;
        card.push_back(input);
    }

    cin >> m;

    for (int i = 0, input; i < m; i++) {
        cin >> input;
        v.push_back(input);
    }

    sort(card.begin(), card.end());

    int idx1, idx2;
    for (int num : v) {
        idx1 = lower_bound(card.begin(), card.end(), num) - card.begin();
        idx2 = upper_bound(card.begin(), card.end(), num) - card.begin();

        if (card[idx1] == num) {
            cout << idx2 - idx1 << " ";
        } else {
            cout << "0 ";
        }
    }

    return 0;
}
