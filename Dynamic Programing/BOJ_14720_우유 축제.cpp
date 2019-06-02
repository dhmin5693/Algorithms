#include <cstdio>

// milk�� ��
// 0: ����, 1: ����, 2: �ٳ���
int milk[1000] = { 0, };
int dp[1000] = { 0, };

int main()
{
	int n;
	scanf("%d", &n);

	bool flag = false;
	int idx = -1;

	for (int i = 0; i < n; ++i) {
		scanf("%d", &milk[i]);

		if (!flag) {
			if (milk[i] == 0) {
				flag = true;
				++dp[i];
				idx = i;
			}
			continue;
		}

		if (flag && (milk[idx] + 1) % 3 == milk[i]) {
			dp[i] = dp[i - 1] + 1;
			idx = i;
		}

		else {
			dp[i] = dp[i - 1];
		}
	}

	printf("%d\n", dp[n - 1]);
	return 0;
}