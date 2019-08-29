#include <cstdio>
#include <vector>

using namespace std;

typedef struct {
	int num;
	int parent;
	vector<int> child;
} Node;

Node node[50] = { 0, };
bool isVisited[50] = { false, };

int dfs(int nodeNumber, int deletedNode) {
	
	if (isVisited[nodeNumber]) {
		return 0;
	}

	isVisited[nodeNumber] = true;

	if (nodeNumber == deletedNode) {
		return 0;
	}

	if (node[nodeNumber].child.size() == 0 && node[nodeNumber].parent != -1) {
		return 1;
	}

	if (node[nodeNumber].child.size() == 1 && node[nodeNumber].child[0] == deletedNode) {
		return 1;
	}

	int childSize = node[nodeNumber].child.size();

	int num = 0;

	for (int i = 0; i < childSize; ++i) {
		num += dfs(node[nodeNumber].child[i], deletedNode);
	}

	return num;
}

int main() {

	int n;
	scanf("%d", &n);

	int root;

	for (int i = 0; i < n; ++i) {
		node[i].num = i;
		scanf("%d", &node[i].parent);

		if (node[i].parent != -1) {
			node[node[i].parent].child.push_back(i);
		} else {
			root = i;
		}
	}

	int deletedNode;
	scanf("%d", &deletedNode);

	printf("%d\n", dfs(root, deletedNode));

	return 0;
}