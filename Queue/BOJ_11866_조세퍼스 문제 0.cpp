#include <cstdio>
#include <queue>

using namespace std;

int main()
{
	queue<int> q;
	int n, m;
	scanf("%d %d", &n, &m);

	for (int i = 1; i <= n; i++)
		q.push(i);

	printf("<");
	while (1)
	{
		for (int i = 0; i < m - 1; i++)
		{
			q.push(q.front());
			q.pop();
		}

		printf("%d", q.front());
		q.pop();

		if (!q.empty())
			printf(", ");
		else
			break;
	}
	printf(">\n");

	return 0;
}