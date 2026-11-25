#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif
	int T;
	int a, b;
	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> a >> b;
		cout << a + b << "\n";
	}

	return 0;
}