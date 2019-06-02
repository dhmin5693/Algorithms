#include <cstdio>

int t[1025][1025];

int main()
{
	//	freopen("input.txt", "r", stdin);
	int n, m;
	scanf("%d %d", &n, &m);

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
		{
			scanf("%d", &t[i][j]);
			t[i][j] += t[i][j - 1] + t[i - 1][j] - t[i - 1][j - 1];
		}

	for (int i = 0; i < m; i++)
	{
		int x1, y1, x2, y2;
		scanf("%d %d %d %d", &x1, &y1, &x2, &y2);

		printf("%d\n", t[x2][y2] - t[x1 - 1][y2] - t[x2][y1 - 1] + t[x1 - 1][y1 - 1]);
	}

	return 0;
}