#include <iostream>
#include <vector>
using namespace std;

int n, m, d, maxNum, sum, y;
int map[15][15] = { 0, };
int yIdx[16] = { 0, };

struct Enemy {
	int y, x;
	bool die;

	Enemy(int y, int x) {
		this->y = y;
		this->x = x;
		this->die = false;
	}
};

vector<Enemy> enemy;
int target[3] = { 0, };
int archer[3] = { 0, };

int getDistance(int r1, int c1, int r2, int c2) {
	int r = r1 - r2;
	int c = c1 - c2;

	if (r < 0) r = -r;
	if (c < 0) c = -c;

	return r + c;
}

void input() {
	cin >> n >> m >> d;

	y = n;

	for (int i = 0; i < n; i++) {
		yIdx[i] = enemy.size();
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
			if (map[i][j]) {
				enemy.push_back(Enemy(i, j));
			}
		}
	}
	yIdx[y] = enemy.size();
}

void attack() {

	int size = enemy.size();
	for (int i = 0; i < size; i++) {
		enemy[i].die = false;
	}

	// i = 현재 궁수의 위치
	for (int i = y; i >= 1; i--) {

		// 각 궁수의 공격 목표 설정
		for (int j = 0; j < 3; j++) {
			int minDist = 0x7FFFFFFF;
			int idx = -1;

			for (int k = 0; k < yIdx[i]; k++) {
				if (!enemy[k].die) {
					int dist = getDistance(i, archer[j], enemy[k].y, enemy[k].x);

					if (dist > d) {
						continue;
					}

					if (minDist > dist) {
						minDist = dist;
						idx = k;
					} else if (minDist == dist) {
						if (enemy[idx].x > enemy[k].x) {
							idx = k;
						}
					}
				}
			}

			target[j] = idx;
		}

		//사격
		for (int j = 0; j < 3; j++) {
			if (target[j] != -1) {
				enemy[target[j]].die = true;
			}
		}
	}
}

void cnt() {
	int sum = 0, size = enemy.size();
	for (int i = 0; i < size; i++) {
		if (enemy[i].die) {
			sum++;
		}
	}

	maxNum = maxNum < sum ? sum : maxNum;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	input();

	maxNum = 0;

	for (int i = 0; i < m - 2; i++) {
		archer[0] = i;
		for (int j = i + 1; j < m - 1; j++) {
			archer[1] = j;
			for (int k = j + 1; k < m; k++) {
				archer[2] = k;
				attack();
				cnt();
			}
		}
	}

	cout << maxNum << '\n';

	return 0;
}