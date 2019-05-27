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

	int food[10][10] = { 0, }; // ���
	int rand[10][10] = { 0, }; // ��

	vector<int> tree[10][10]; // ����
	int baby[10][10] = { 0, }; // �߰��� �� ������ ����

	int sum = 0; // �� ������ ����

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

				// ����
				sort(tree[i][j].begin(), tree[i][j].end(), compare);

				// ���� (���� ���� ����)
				for (int del = tree[i][j].size() - 1; del >= 0 && tree[i][j][del] == DIE; --del) {
					tree[i][j].pop_back();
				}

				int deadTree = 0; // ���� ���� ��� �ӽ� ����

				// ��, ����
				for (int idx = 0; idx < tree[i][j].size(); ++idx) {

					if (tree[i][j][idx] == DIE)
						continue;

					// ��� ����
					if (rand[i][j] >= tree[i][j][idx]) {
						rand[i][j] -= tree[i][j][idx];
						++tree[i][j][idx];

						// ���� (����)
						if (tree[i][j][idx] % 5 == 0) {
							for (int dir = 0; dir < 8; ++dir) {
								int y = i + dy[dir];
								int x = j + dx[dir];

								if (rangeCheck(y, x, n)) {
									++baby[y][x];
									++sum;
								}
							}
						} // ���� (����) ��

					} // ��� ���� ��

					// ���� ���� ó��
					else {
						deadTree += tree[i][j][idx] / 2;
						tree[i][j][idx] = DIE;
						--sum;
					} // ���� ���� ó�� ��

				} // ��, ���� ��

				// ���� (���� ������ ��� �߰�)
				rand[i][j] += deadTree;

				// �ܿ� (������ �� ��ŭ�� ��� �߰�)
				rand[i][j] += food[i][j];
			}
		}

		// ���� (����)
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