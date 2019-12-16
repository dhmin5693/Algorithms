#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	string input;

	int sum = 0, size = 0;
	while (cin >> input) {
		if (input == "<br>") {
			cout << "\n";
			sum = 0;
		} else if (input == "<hr>") {
			if (sum) {
				cout << "\n";
			}

			for (int i = 0; i < 80; i++) {
				cout << "-";
			}
			cout << "\n";
			sum = 0;
		} else {

			if (sum) {
				sum++;
				cout << " ";
			}

			size = input.size();
			sum += size;

			if (sum > 80) {
				cout << "\n" << input;
				sum = size;
			} else {
				cout << input;
			}
		}
	}

	return 0;
}