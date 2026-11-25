#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif
	int a, b;

	while (cin >> a>> b) {
		cout << a + b << "\n";
	}

	return 0;
}