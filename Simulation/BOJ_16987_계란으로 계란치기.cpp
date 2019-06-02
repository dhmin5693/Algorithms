#include <cstdio>
#include <vector>

#define MAX(a, b, c) (a) > (b) ? ((a) > (c) ? (a) : (c)) : ((b) > (c) ? (b) : (c))

using namespace std;

struct Egg {
	int hp;
	int wg;

	Egg(int hp, int wg) : hp(hp), wg(wg) {}
};

vector<Egg> eggs;

int dfs(int cur, int broken, int size) {

	// 부숴진 계란 = size || cur가 범위 내에 존재하지 않음
	if (broken == size || cur >= size || cur < 0) {
		return broken;
	}

	// 이미 부숴진 계란이면 다음 계란을 든다.
	if (eggs[cur].hp <= 0) {
		return dfs(cur + 1, broken, size);
	}

	int max = 0;

	for (int i = 0; i < size; ++i) {

		// 더 이상 볼 필요 없는 경우 바로 종료
		if (max == size) {
			return max;
		}

		// 같은 계란 || 이미 깨짐
		if (i == cur || eggs[i].hp <= 0) {
			continue;
		}

		// 계란 깨기
		eggs[i].hp -= eggs[cur].wg;
		eggs[cur].hp -= eggs[i].wg;

		// 깨진 계란 수 추가
		int brokenCur = eggs[cur].hp <= 0 ? 1 : 0;
		int brokenI = eggs[i].hp <= 0 ? 1 : 0;

		broken += (brokenCur + brokenI);

		// 0 ~ n 사이의 계란 중 하나를 깬 뒤 [cur + 1]번 계란을 든다.
		int next = dfs(cur + 1, broken, size);
		max = MAX(next, broken, max);

		// 계란 원상복귀
		eggs[i].hp += eggs[cur].wg;
		eggs[cur].hp += eggs[i].wg;

		// 깨진 계란 수 복구
		broken -= (brokenCur + brokenI);
	}

	return max;
}

int main() {

	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; ++i) {
		int hp, wg;
		scanf("%d %d", &hp, &wg);

		eggs.push_back(Egg(hp, wg));
	}

	printf("%d\n", dfs(0, 0, eggs.size()));

	return 0;
}