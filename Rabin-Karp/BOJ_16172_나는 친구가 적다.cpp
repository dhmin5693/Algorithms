#include <iostream>
#include <string>
using namespace std;

typedef long long ll;
string in, s, k;
int inSize, sSize, kSize;

bool isNum(char ch) {
    return ch >= '0' && ch <= '9';
}

bool search(int si) {
    for (int i = 0; i < kSize; i++) {
        if (s[si + i] != k[i]) {
            return false;
        }
    }
    return true;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(0), cout.tie(0);

	cin >> in >> k;

	inSize = in.size();

	for (int i = 0; i < inSize; i++) {
	    if (!isNum(in[i])) {
	        s.push_back(in[i]);
	    }
	}

	ll sHash = 0L,
	   kHash = 0L,
	   power = 1L;

	kSize = k.size();
	for (int i = kSize - 1; i >= 0; i--) {
        kHash += power * (ll) k[i];
        sHash += power * (ll) s[i];
        if (i != 0) power *= 2;
    }

	sSize = s.size();

	if (sHash == kHash && search(0)) {
	    cout << '1' << '\n';
	    return 0;
	}

	for (int i = 1; i <= sSize - kSize; i++) {
	    sHash = 2 * (sHash - (power * s[i - 1])) + (ll) s[i + kSize - 1];

	    if (sHash == kHash && search(i)) {
	        cout << '1' << '\n';
	        return 0;
	    }
	}
	
	cout << '0' << '\n';

	return 0;
}