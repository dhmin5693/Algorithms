#include <iostream>
#include <vector>
#include <bitset>
#define MAX(a, b) (a) > (b) ? (a) : (b)
using namespace std;

struct Trie {
	int num;
	Trie *next[2];

	Trie() {
		num = 0;
		next[0] = next[1] = nullptr;
	}

	~Trie() {
		if (next[0] != nullptr) {
			delete next[0];
		}

		if (next[1] != nullptr) {
			delete next[1];
		}
	}
};

int input[100000] = { 0, };

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n, maxSize = 1, answer = 0;
	Trie trie;

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> input[i];

		while (input[i] > (1 << maxSize)) {
			maxSize++;
		}
	}

	maxSize++;

	for (int i = 0; i < n; i++) {

		bitset<31> bs(input[i]);
		Trie* cur = &trie;

		for (int j = maxSize - 1; j >= 0; j--) {
			int bit = bs[j];

			if (cur->next[bit] == nullptr) {
				cur->next[bit] = new Trie();
			}
			cur = cur->next[bit];
		}

		cur->num = input[i];

		cur = &trie;

		for (int j = maxSize - 1; j >= 0; j--) {
			int bit = bs[j];

			if (cur->next[!bit] != nullptr) {
				cur = cur->next[!bit];
				continue;
			} else if (cur->next[bit] != nullptr) {
				cur = cur->next[bit];
				continue;
			}

			break;
		}

		if (input[i] == cur->num) {
			continue;
		}

		int val = cur->num ^ input[i];
		answer = MAX(answer, val);
	}

	cout << answer << "\n";

	return 0;
}