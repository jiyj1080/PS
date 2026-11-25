#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>

using namespace std;

int main() {
	int a, b;
	cin >> a >> b;
	cout << a + b << "\n" 
		<< a - b << "\n"
		<< a * b << "\n"
		<< a / b << "\n"
		<< a % b;
	return 0;
}