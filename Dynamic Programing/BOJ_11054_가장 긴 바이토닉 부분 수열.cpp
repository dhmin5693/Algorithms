#include <cstdio>

int num[1000];
int memo1[1000];
int memo2[1000];

int main()
{
	int n, ans = 0;
	scanf("%d", &n);

	for (int i = 0; i < n; ++i)
		scanf("%d", &num[i]);

	for (int i = 0; i < n; ++i)
	{
		memo1[i] = 1;

		for (int j = 0; j < i; ++j)
		{
			if (num[j] < num[i] && memo1[i] < memo1[j] + 1)
				memo1[i] = memo1[j] + 1;
		}
	}

	for (int i = n - 1; i >= 0; --i)
	{
		memo2[i] = 1;

		for (int j = i + 1; j < n; ++j)
		{
			if (num[j] < num[i] && memo2[i] < memo2[j] + 1)
				memo2[i] = memo2[j] + 1;
		}
	}

	for (int i = 0; i < n; ++i)
		ans = ans < memo1[i] + memo2[i] - 1 ? memo1[i] + memo2[i] - 1 : ans;

	printf("%d\n", ans);

	return 0;
}