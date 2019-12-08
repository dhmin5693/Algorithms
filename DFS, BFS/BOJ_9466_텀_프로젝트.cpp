#include <iostream>
#include <vector>
using namespace std;

int sel[100001] = { 0, };
int visit[100001] = { 0, };
bool team[100001] = { false, };
bool solo[100001] = { false, };

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int t;
    vector<int> st;
    st.reserve(100000);

    for (cin >> t; t > 0; t--) {
        int n, cnt = 0;
        cin >> n;

        for (int i = 1; i <= n; i++) {
            cin >> sel[i];
            team[i] = solo[i] = false;
            visit[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            if (team[i] || solo[i]) {
                continue;
            }

            st.clear();
            int cur = i;
            bool loop = false;

            while (visit[cur] != i) {
                st.push_back(cur);
                visit[cur] = i;
                cur = sel[cur];
                if (team[cur] || solo[cur]) {
                    loop = true;
                    break;
                }
            }

            if (loop) {
                while (!st.empty()) {
                    solo[st.back()] = true;
                    st.pop_back();
                }
                continue;
            }

            int end = cur;
            int student;

            while (true) {
                student = st.back();
                team[student] = true;
                st.pop_back();
                cnt++;
                if (student == end) {
                    break;
                }
            }

            while (!st.empty()) {
                solo[st.back()] = true;
                st.pop_back();
            }
        }

        cout << n - cnt << "\n";
    }

    return 0;
}
