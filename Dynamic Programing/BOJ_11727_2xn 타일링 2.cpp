#include <cstdio>

int way[1000] = { 0, };

int main()
{
	int N;
	scanf("%d", &N);

	way[0] = 1;
	way[1] = 3;

	for (int i = 2; i < N; i++)
		way[i] = (way[i - 1] + way[i - 2] * 2) % 10007;

	printf("%d\n", way[N - 1]);
	return 0;
}