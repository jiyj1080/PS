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

	int N, tmp;
	cin >> N;
	int min = 1000001, max = -1000001;
	for (int i = 0; i < N; i++) {
		cin >> tmp;
		min = min < tmp ? min : tmp;
		max = max > tmp ? max : tmp;
	}
	cout << min << " " << max;

	return 0;
}