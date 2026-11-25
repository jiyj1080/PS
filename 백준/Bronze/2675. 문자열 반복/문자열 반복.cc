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

	int T;
	cin >> T;
	string S, tmp;
	int R;
	for (int t = 0; t < T; t++) {
		S.clear(); tmp.clear();
		cin >> R >> S;
		for (auto c : S) {
			for (int i = 0; i < R; i++) {
				tmp += c;
			}
		}
		cout << tmp << "\n";
	}

	return 0;
}