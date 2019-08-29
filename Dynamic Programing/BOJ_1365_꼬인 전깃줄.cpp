#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> v;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(nullptr); cout.tie(nullptr);

	int n, in, size = 0;
	cin >> n;

	v.push_back(-1);

	for (int i = 0; i < n; i++) {
		cin >> in;

		if (in > v.back()) {
			v.push_back(in);
			size++;
		} else {
			auto iter = lower_bound(v.begin(), v.end(), in);
			*iter = in;
		}
	}

	cout << n - size << '\n';

	return 0;
}