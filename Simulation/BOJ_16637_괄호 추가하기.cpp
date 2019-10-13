#include <iostream>
#include <vector>
#include <string>
using namespace std;

vector<int> num;
vector<char> op;

vector<int> tmpNum;
vector<char> tmpOp;

bool chk[10] = { false, };

typedef long long ll;
ll MAX = -0x7FFFFFFF;

ll calc(int num1, int num2, char op) {
	switch (op) {
	case '+':
		return num1 + num2;
	case '-':
		return num1 - num2;
	case '*':
		return num1 * num2;
	}
}

void go(int size) {

	tmpNum.clear();
	tmpOp.clear();

	ll sum = 0;
	for (int i = 0; i < size; i++) {

		if (chk[i]) {
			tmpNum.push_back(calc(num[i], num[i + 1], op[i]));
			if (i + 1 <= size - 2) {
				tmpOp.push_back(op[i + 1]);
			}
			i++;
		} else {
			tmpNum.push_back(num[i]);
			if (i <= size - 2) {
				tmpOp.push_back(op[i]);
			}
		}
	}

	if (tmpNum.size() == 1) {
		sum = tmpNum[0];
	} else {
		int num1 = tmpNum[0];
		int num2 = tmpNum[1];

		sum += calc(num1, num2, tmpOp[0]);

		int _size = tmpNum.size();
		for (int i = 2, j = 1; i < _size; i++, j++) {
			sum = calc(sum, tmpNum[i], tmpOp[j]);
		}
	}

	MAX = MAX < sum ? sum : MAX;
}

void dfs(int n, int size) {

	if (n >= size - 1) {
		go(size);
		return;
	}

	// chk[n] -> (num[i] [op[i]] num[i + 1])
	if (n == 0 || (n > 0 && !chk[n - 1])) {
		chk[n] = true;
		dfs(n + 1, size);
	}

	chk[n] = false;
	dfs(n + 1, size);
}

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;

	string eq;
	cin >> eq;

	if (n == 1) {
		cout << (eq[0] - '0') << '\n';
		return 0;
	}

	for (int i = 0; i < n; i++) {
		if (eq[i] >= '0' && eq[i] <= '9') {
			num.push_back(eq[i] - '0');
		} else {
			op.push_back(eq[i]);
		}
	}

	dfs(0, n / 2 + 1);

	cout << MAX << '\n';

	return 0;
}