#include <cstdio>

int num[1000];
int memo[1000];

int main()
{
	int n, max = 0;
	scanf("%d", &n);

	for (int i = 0; i < n; ++i)
		scanf("%d", &num[i]);

	for (int i = 0; i < n; ++i)
	{
		memo[i] = num[i];

		for (int j = 0; j < i; ++j)
		{
			if (num[j] < num[i] && memo[i] < memo[j] + num[i])
				memo[i] = memo[j] + num[i];
		}
	}

	for (int i = 0; i < n; ++i)
		max = memo[max] < memo[i] ? i : max;

	printf("%d\n", memo[max]);

	return 0;
}