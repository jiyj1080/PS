#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	int num[9];
	int max = 0, idx;
	for (int i = 0; i < 9; i++) {
		cin >> num[i];
	}
	for (int i = 0; i < 9; i++) {
		if (num[i] > max) {
			max = num[i];
			idx = i;
		}
	}
	cout << max << "\n" << idx + 1;

	return 0;
}