#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, ans = 0;
vector<vector<int>> inning;
vector<int> v = { 1,2,3,4,5,6,7,8 };
int player[9] = { 0, };
int base[3] = { 0, };

void input() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		vector<int> v;
		int tmp;
		for (int j = 0; j < 9; j++) {
			cin >> tmp;
			v.push_back(tmp);
		}
		inning.push_back(v);
	}
}

void playerInit() {
	for (int i = 0; i < 3; i++) {
		player[i] = v[i];
	}

	player[3] = 0;

	for (int i = 4; i < 9; i++) {
		player[i] = v[i - 1];
	}
}

void baseInit() {
	base[2] = base[1] = base[0] = 0;
}

int run(int shot) {

	int score = 0;

	if (base[2])
		score++;

	switch (shot) {
	case 1:
		base[2] = base[1];
		base[1] = base[0];
		base[0] = 1;
		break;
	case 2:
		if (base[1]) score++;
		base[2] = base[0];
		base[1] = 1;
		base[0] = 0;
		break;
	case 3:
		if (base[1]) score++;
		if (base[0]) score++;
		base[2] = 1;
		base[1] = base[0] = 0;
		break;
	case 4:
		if (base[1]) score++;
		if (base[0]) score++;
		score++;
		baseInit();
		break;
	}

	return score;
}

void play() {

	int score, bat, out;

	do {
		playerInit();

		score = bat = 0;

		for (int i = 0; i < n; i++) {
			out = 0;
			baseInit();

			while (out < 3) {

				int shot = inning[i][player[bat]];

				if (shot) {
					score += run(shot);
				} else {
					out++;
				}

				bat++;
				bat %= 9;
			}
		}

		ans = score > ans ? score : ans;
	} while (next_permutation(v.begin(), v.end()));

}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	input();
	play();

	cout << ans << '\n';

	return 0;
}