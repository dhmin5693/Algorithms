#include <iostream>
#include <vector>
using namespace std;

int b[9][9];
vector<pair<int, int>> p;

bool validRow(int y) {
    int valid = 0, tmp;

    for (int x = 0; x < 9; x++) {
        if (!b[y][x]) {
            continue;
        }
        tmp = (1 << b[y][x]);
        if (valid & tmp) {
            return false;
        }
        valid |= tmp;
    }

    return true;
}

bool validCol(int x) {
    int valid = 0, tmp;

    for (int y = 0; y < 9; y++) {
        if (!b[y][x]) {
            continue;
        }
        tmp = (1 << b[y][x]);
        if (valid & tmp) {
            return false;
        }
        valid |= tmp;
    }

    return true;
}

bool validBox(pair<int, int> _p) {
    int valid = 0, tmp;

    int y = _p.first / 3 * 3;
    int x = _p.second / 3 * 3;

    for (int i = y; i < y + 3; i++) {
        for (int j = x; j < x + 3; j++) {
            if (!b[i][j]) {
                continue;
            }
            tmp = (1 << b[i][j]);
            if (valid & tmp) {
                return false;
            }
            valid |= tmp;
        }
    }

    return true;
}

void dfs(int idx) {

    if (idx == p.size()) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cout << b[i][j] << ' ';
            }
            cout << '\n';
        }

        exit(0);
    }

    for (int i = 1; i <= 9; i++) {
        b[p[idx].first][p[idx].second] = i;
        if (validRow(p[idx].first) && validCol(p[idx].second) && validBox(p[idx])) {
            dfs(idx + 1);
        }
        b[p[idx].first][p[idx].second] = 0;
    }
}

int main() {
    freopen("../input.txt", "r", stdin);
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> b[i][j];
            if (!b[i][j]) {
                p.push_back({i, j});
            }
        }
    }

    dfs(0);

    return 0;
}
