#include <iostream>
#include <vector>
#define MAX(a, b) (a) > (b) ? (a) : (b)
using namespace std;

vector<int> v[2];
vector<int> cross;

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    bool end = false;

    int ans, sum[2], size[2], idx[2], num[2];

    while(true) {
        for (int i = 0, sz; i < 2; i++) {
            cin >> sz;

            if (!sz) {
                end = true;
                break;
            }

            v[i].clear();
            for (int j = 0, input; j < sz; j++) {
                cin >> input;
                v[i].push_back(input);
            }

            v[i].push_back(10001);

            size[i] = v[i].size();
            idx[i] = 0;
        }

        if (end) {
            break;
        }

        ans = 0;
        cross.clear();

        while(idx[0] <= size[0] && idx[1] <= size[1]) {

            num[0] = v[0][idx[0]];
            num[1] = v[1][idx[1]];

            if (num[0] == num[1]) {
                cross.push_back(num[0]);
                idx[0]++;
                idx[1]++;
            } else if (num[0] > num[1]) {
                idx[1]++;
            } else {
                idx[0]++;
            }
        }

        cross.push_back(10001);

        idx[0] = idx[1] = sum[0] = sum[1] = 0;
        int crossIdx = 0;

        bool end2 = false;
        while(!end2) {

            end2 = true;
            if(v[0][idx[0]] < cross[crossIdx]) {
                sum[0] += v[0][idx[0]++];
                end2 = false;
            }

            if(v[1][idx[1]] < cross[crossIdx]) {
                sum[1] += v[1][idx[1]++];
                end2 = false;
            }

            if (v[0][idx[0]] == v[1][idx[1]] && v[0][idx[0]] == cross[crossIdx]) {
                crossIdx++;
                ans += MAX(sum[0], sum[1]);
                sum[0] = sum[1] = 0;
                end2 = false;
            }
        }

        cout << ans << "\n";
    }

    return 0;
}
