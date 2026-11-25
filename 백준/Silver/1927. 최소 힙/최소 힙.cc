#include <iostream>
#include <queue>
#include <functional>
#include <vector>
#include <cstdio>
using namespace std;

int main() {
	priority_queue<int, vector<int>, greater<int>> q;
	int n;
	int tem;
	int res[100001] = { 0, };

	cin >> n;
	int idx = 0;
	for (int i = 0; i < n; i++) {
		cin >> tem;
		if (tem == 0) {
			if (q.empty()) res[idx] = 0;
			else {
				res[idx] = q.top();
				q.pop();
			}
			idx++;
		}
		else q.push(tem);
	}
	for (int i = 0; i < idx; i++) printf("%d\n", res[i]);
	return 0;
}