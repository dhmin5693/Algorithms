#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int num[1000001];
vector<int> v;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr); cout.tie(nullptr);

	int n, size = 0;
	cin >> n;

	v.push_back(-1);

	for (int i = 0; i < n; i++) {
		cin >> num[i];

		if (num[i] > v.back()) {
			v.push_back(num[i]);
			size++;
		} else {
			auto iter = lower_bound(v.begin(), v.end(), num[i]);
			*iter = num[i];
		}
	}

	cout << size << '\n';

	return 0;
}