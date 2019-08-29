#include <cstdio>
using namespace std;

int tree[26][26] = { 0, };

void setChild(int* left, int* right, int idx, int n) {

	for (int i = 0; i < n; ++i) {
		*left = (tree[idx][i] == 1) ? i : *left;
		*right = (tree[idx][i] == 2) ? i : *right;
	}
}

void preOrder(int idx, int n) {

	if (idx == -1) {
		return;
	}

	int left, right;
	left = right = -1;

	setChild(&left, &right, idx, n);

	printf("%c", idx + 'A');
	preOrder(left, n);
	preOrder(right, n);
}

void inOrder(int idx, int n) {

	if (idx == -1) {
		return;

	}

	int left, right;
	left = right = -1;

	setChild(&left, &right, idx, n);

	inOrder(left, n);
	printf("%c", idx + 'A');
	inOrder(right, n);
}

void postOrder(int idx, int n) {

	if (idx == -1) {
		return;
	}

	int left, right;
	left = right = -1;

	setChild(&left, &right, idx, n);

	postOrder(left, n);
	postOrder(right, n);
	printf("%c", idx + 'A');
}

int main() {

	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; ++i) {
		char node, left, right;
		scanf(" %c %c %c", &node, &left, &right);

		if (left != '.') {
			tree[node - 'A'][left - 'A'] = 1;
		}

		if (right != '.') {
			tree[node - 'A'][right - 'A'] = 2;
		}
	}

	preOrder(0, n);		printf("\n");
	inOrder(0, n);		printf("\n");
	postOrder(0, n);	printf("\n");

	return 0;
}