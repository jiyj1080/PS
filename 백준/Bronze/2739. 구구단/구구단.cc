#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int main() {
	int n;
	cin >> n;
	for (int i = 1; i < 10; i++) {
		cout << n << " * " << i << " = " << n * i << "\n";
	}

	return 0;
}