#include <iostream>
using namespace std;

int map[20][20] = { 0, };

int n, m, y, x;

// µ¿¼­ºÏ³²
int dy[] = { 0, 0, -1, 1 };
int dx[] = { 1, -1, 0, 0 };

// ¹Ø, À§, Á¤¸é, ¿ìÃø, µÞ¸é, ÁÂÃø
int dice[6] = { 0, };

bool chk(int _y, int _x) {
	return _y >= 0 && _y < n && _x >= 0 && _x < m;
}

void roll(int cmd) {

	int tmp = dice[0];

	switch (cmd) {
	case 0:
		dice[0] = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[5];
		dice[5] = tmp;
		break;
	case 1:
		dice[0] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[3];
		dice[3] = tmp;
		break;
	case 2:
		dice[0] = dice[4];
		dice[4] = dice[1];
		dice[1] = dice[2];
		dice[2] = tmp;
		break;
	case 3:
		dice[0] = dice[2];
		dice[2] = dice[1];
		dice[1] = dice[4];
		dice[4] = tmp;
		break;
	}
}

void run(int cmd) {

	if (!chk(y + dy[cmd], x + dx[cmd])) {
		return;
	}

	y += dy[cmd];
	x += dx[cmd];

	roll(cmd);

	if (!map[y][x]) {
		map[y][x] = dice[0];
	} else {
		dice[0] = map[y][x];
		map[y][x] = 0;
	}

	cout << dice[1] << "\n";
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int k;
	cin >> n >> m >> y >> x >> k;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
		}
	}

	int cmd;
	for (int i = 0; i < k; i++) {
		cin >> cmd;
		run(cmd - 1);
	}

	return 0;
}