#include <cstdio>

int bread[1000];
int price[1000];
int main()
{
	int N;
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		scanf("%d", &price[i]);

	bread[0] = price[0];

	for (int i = 0; i < N; i++)
	{
		int max = price[i];
		for (int j = 0; j < i; j++)
		{
			if (max < bread[i - j - 1] + price[j])
				max = bread[i - j - 1] + price[j];
		}
		bread[i] = max;
	}

	printf("%d\n", bread[N - 1]);
	return 0;
}