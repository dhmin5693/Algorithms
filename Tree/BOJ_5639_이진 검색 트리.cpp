#include <cstdio>
using namespace std;

typedef struct Node {
	int num;
	Node *left;
	Node *right;
} Node;

void postOrder(Node *node) {

	if (node == NULL) {
		return;
	}

	postOrder(node->left);
	postOrder(node->right);
	printf("%d\n", node->num);
}

int main() {

	int n;
	scanf("%d", &n);

	Node *start = new Node;
	start->num = n;
	start->left = start->right = NULL;

	while (scanf("%d", &n) != EOF) {
		Node *node = new Node;
		node->num = n;
		node->left = node->right = NULL;

		Node *cur = start;
		while (true) {
			if (cur->num > node->num) {
				if (cur->left == NULL) {
					cur->left = node;
					break;
				} else {
					cur = cur->left;
				}
			} else {
				if (cur->right == NULL) {
					cur->right = node;
					break;
				}
				else {
					cur = cur->right;
				}
			}
		}
	}

	postOrder(start);

	return 0;
}