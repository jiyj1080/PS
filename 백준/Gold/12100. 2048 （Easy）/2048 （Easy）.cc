#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>

using namespace std;

int N;
int B[20][20];

void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> B[i][j];
		}
	}
}

void move(int dir, int B[20][20]) {
	switch (dir) {
	case 0:
		for (int i = 0; i < N; i++) {
			int tmp[20];
			int nonzero = 0;
			for (int j = 0; j < N; j++) {	// tmp에 1줄 복사
				if (B[i][j] != 0) {
					tmp[nonzero++] = B[i][j];
					B[i][j] = 0;
				}
			}

			for (int j = nonzero - 1; j >= 0; j--) {	// 합체
				int k;
				for (k = j - 1; k >= 0; k--) {
					if (tmp[k] == 0) continue;
					if (tmp[k] == tmp[j]) {
						tmp[j] *= 2;
						tmp[k] = 0;
						j--;
					}
					else break;
				}
			}
			int idx = N - 1;
			for (int j = nonzero - 1; j >= 0; j--) {	// B에 입력
				if (tmp[j] != 0) {
					B[i][idx--] = tmp[j];
				}
			}
		}
		break;
	case 1:
		for (int j = 0; j < N; j++) {
			int tmp[20];
			int nonzero = 0;
			for (int i = 0; i < N; i++) {	// tmp에 1줄 복사
				if (B[i][j] != 0) {
					tmp[nonzero++] = B[i][j];
					B[i][j] = 0;
				}
			}

			for (int i = nonzero - 1; i >= 0; i--) {	// 합체
				int k;
				for (k = i - 1; k >= 0; k--) {
					if (tmp[k] == 0) continue;
					if (tmp[k] == tmp[i]) {
						tmp[i] *= 2;
						tmp[k] = 0;
						i--;
					}
					else break;
				}
			}
			int idx = N - 1;
			for (int i = nonzero - 1; i >= 0; i--) {	// B에 입력
				if (tmp[i] != 0) {
					B[idx--][j] = tmp[i];
				}
			}
		}
		break;
	case 2:
		for (int i = 0; i < N; i++) {
			int tmp[20];
			int nonzero = 0;
			for (int j = 0; j < N; j++) {	// tmp에 1줄 복사
				if (B[i][j] != 0) {
					tmp[nonzero++] = B[i][j];
					B[i][j] = 0;
				}
			}

			for (int j = 0; j < nonzero; j++) {	// 합체
				int k;
				for (k = j + 1; k < nonzero; k++) {
					if (tmp[k] == 0) continue;
					if (tmp[k] == tmp[j]) {
						tmp[j] *= 2;
						tmp[k] = 0;
						j++;
					}
					else break;
				}
			}
			int idx = 0;
			for (int j = 0; j < nonzero; j++) {	// B에 입력
				if (tmp[j] != 0) {
					B[i][idx++] = tmp[j];
				}
			}
		}
		break;
	case 3:
		for (int j = 0; j < N; j++) {
			int tmp[20];
			int nonzero = 0;
			for (int i = 0; i < N; i++) {	// tmp에 1줄 복사
				if (B[i][j] != 0) {
					tmp[nonzero++] = B[i][j];
					B[i][j] = 0;
				}
			}

			for (int i = 0; i < nonzero; i++) {	// 합체
				int k;
				for (k = i + 1; k < nonzero; k++) {
					if (tmp[k] == 0) continue;
					if (tmp[k] == tmp[i]) {
						tmp[i] *= 2;
						tmp[k] = 0;
						i++;
					}
					else break;
				}
			}
			int idx = 0;
			for (int i = 0; i < nonzero; i++) {	// B에 입력
				if (tmp[i] != 0) {
					B[idx++][j] = tmp[i];
				}
			}
		}
		break;
	}
}

int biggest(int B[20][20]) {
	int answer = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			answer = max(B[i][j], answer);
		}
	}
	return answer;
}

void board_copy(int from[20][20], int to[20][20]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			to[i][j] = from[i][j];
		}
	}
}

int dfs(int depth, int B[20][20]) {
	if (depth == 5) return biggest(B);
	int big = 0;
	for (int d = 0; d < 4; d++) {
		int copy[20][20];
		board_copy(B, copy);
		move(d, copy);
		big = max(big, dfs(depth + 1, copy));
	}
	return big;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE 
	freopen("input.txt", "r", stdin); 
#endif

	input();

	cout << dfs(0, B);

	return 0;
}