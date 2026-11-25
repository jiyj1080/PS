#include<iostream>
#include<algorithm>
using namespace std;
int a[10];
int main() {
	int sum = 0;
	for (int i = 0; i < 9; i++) {
		cin >> a[i];
		sum += a[i];
	}
	sort(a, a + 9);
	for (int i = 0; i < 9; i++) {
		for (int j = i+1; j < 9; j++) {
			if ((sum - (a[i] + a[j])) == 100) {
				for (int k = 0; k < 9; k++) {
					if (i == k || j == k) continue; 
					cout << a[k] << '\n';
				}
				return 0;
			}
		}
	}
}