#include <cstdio>
#include <vector>
#include <algorithm>
#define DIE 0x7FFFFFFF

using namespace std;
const int dy[8] = { -1, -1, -1,  0, 0,  1, 1, 1 };
const int dx[8] = { -1,  0,  1, -1, 1, -1, 0, 1 };

bool compare(const int a, const int b) {
	return a < b;
}

bool rangeCheck(int y, int x, int n) {
	return y >= 0 && y < n && x >= 0 && x < n;
}

int main() {

	int n, m, k;
	scanf("%d %d %d", &n, &m, &k);

	int food[10][10] = { 0, }; // 양분
	int rand[10][10] = { 0, }; // 땅

	vector<int> tree[10][10]; // 나무
	int baby[10][10] = { 0, }; // 추가할 새 나무의 개수

	int sum = 0; // 산 나무의 개수

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			scanf("%d", &food[i][j]);
			rand[i][j] = 5;
		}
	}

	for (int i = 0; i < m; ++i) {
		int x, y, age;
		scanf("%d %d %d", &x, &y, &age);
		tree[x - 1][y - 1].push_back(age);
		++sum;
	}

	for (int year = 0; year < k; ++year) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {

				// 정렬
				sort(tree[i][j].begin(), tree[i][j].end(), compare);

				// 여름 (죽은 나무 제거)
				for (int del = tree[i][j].size() - 1; del >= 0 && tree[i][j][del] == DIE; --del) {
					tree[i][j].pop_back();
				}

				int deadTree = 0; // 죽은 나무 양분 임시 저장

				// 봄, 가을
				for (int idx = 0; idx < tree[i][j].size(); ++idx) {

					if (tree[i][j][idx] == DIE)
						continue;

					// 양분 섭취
					if (rand[i][j] >= tree[i][j][idx]) {
						rand[i][j] -= tree[i][j][idx];
						++tree[i][j][idx];

						// 가을 (번식)
						if (tree[i][j][idx] % 5 == 0) {
							for (int dir = 0; dir < 8; ++dir) {
								int y = i + dy[dir];
								int x = j + dx[dir];

								if (rangeCheck(y, x, n)) {
									++baby[y][x];
									++sum;
								}
							}
						} // 가을 (번식) 끝

					} // 양분 섭취 끝

					// 죽은 나무 처리
					else {
						deadTree += tree[i][j][idx] / 2;
						tree[i][j][idx] = DIE;
						--sum;
					} // 죽은 나무 처리 끝

				} // 봄, 가을 끝

				// 여름 (죽은 나무의 양분 추가)
				rand[i][j] += deadTree;

				// 겨울 (정해진 양 만큼의 양분 추가)
				rand[i][j] += food[i][j];
			}
		}

		// 가을 (번식)
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {

				for (int idx = 0; idx < baby[i][j]; ++idx) {
					tree[i][j].push_back(1);
				}

				baby[i][j] = 0;
			}
		}
	}

	printf("%d\n", sum);

	return 0;
}