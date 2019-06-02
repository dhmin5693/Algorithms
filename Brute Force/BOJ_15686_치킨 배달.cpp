#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
#define MIN(a, b) (a) < (b) ? (a) : (b)
using namespace std;

int getDist(int r1, int r2, int c1, int c2) {
	return abs(r1 - r2) + abs(c1 - c2);
}

int main() {

	int n, m;
	scanf("%d %d", &n, &m);

	vector<pair<int, int> > house;
	vector<pair<int, int> > chicken;

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			int tmp;
			scanf("%d", &tmp);

			if (tmp == 1) {
				// ���� ��ǥ ����
				house.push_back(make_pair(i, j));
			}
			else if (tmp == 2) {
				// ġŲ���� ��ǥ ����
				chicken.push_back(make_pair(i, j));
			}
		}
	}

	vector<int> idx;
	int hSize = house.size();
	int cSize = chicken.size();

	// m��ŭ 1�� �ְ� �������� 0���� ä���
	// ��ü ũ��� ġŲ��
	for (int i = 0; i < cSize - m; ++i) {
		idx.push_back(0);
	}
	for (int i = 0; i < m; ++i) {
		idx.push_back(1);
	}

	int iSize = idx.size();
	int ans = 0x7FFFFFFF;

	do {
		int sum = 0;
		for (int i = 0; i < hSize; ++i) {
			int minDist = 0x7FFFFFFF;
			for (int j = 0; j < cSize; ++j) {
				// ������ ���� ġŲ����
				if (idx[j]) {
					minDist = MIN(minDist, getDist(house[i].first, chicken[j].first, house[i].second, chicken[j].second));
				}
			}
			sum += minDist;
		}
		ans = MIN(sum, ans);
	} while (next_permutation(idx.begin(), idx.end()));

	printf("%d\n", ans);

	return 0;
}