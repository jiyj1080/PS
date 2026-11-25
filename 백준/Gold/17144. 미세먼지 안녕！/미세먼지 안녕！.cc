#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>

using namespace std;

int R, C, T;
int M[50][50];
int answer, r_pos, c_pos;
int MM[50][50];

const int dr[] = { 0, 1, 0, -1 };
const int dc[] = { 1, 0, -1, 0 };

void input() {
	answer = 0;
	cin >> R >> C >> T;
	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			cin >> M[r][c];
			if (M[r][c] == -1) {
				r_pos = r;
				c_pos = c;
			}
		}
	}
}

bool check(int r, int c) {
	if (r < 0 || r >= R || c < 0 || c >= C || M[r][c] == -1) return false;
	return true;
}

void one() {
	memset(MM, 0, sizeof(MM));
	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			if (M[r][c] > 0) {
				int cnt = 0, amount;
				amount = M[r][c] / 5;
				for (int d = 0; d < 4; d++) {
					int nr, nc;
					nr = r + dr[d];
					nc = c + dc[d];
					if (check(nr, nc)) {
						cnt++;
						MM[nr][nc] += amount;
					}
				}
				MM[r][c] += M[r][c] - cnt * amount;
			}
		}
	}
	memcpy(M, MM, sizeof(MM));
}

bool check_two(int ul, int r, int c, int r_pos) {
	if (ul) {
		if (r < r_pos || r >= R || c < 0 || c >= C) return false;
		return true;
	}
	else {
		if (r < 0 || r > r_pos || c < 0 || c >= C) return false;
		return true;
	}
}

void two_sub(int ul, int r_pos, int c_pos, int d, int dir) {
	int r, c, nr, nc;
	r = r_pos;	c = c_pos;
	for (int i = 0; i < 4; i++) {
		nr = r + dr[d];
		nc = c + dc[d];
		while (check_two(ul, nr, nc, r_pos)) {
			M[r][c] = M[nr][nc];
			r = nr;
			c = nc;
			nr = r + dr[d];
			nc = c + dc[d];
		}
		d = (d + dir) % 4;
	}
	M[r_pos][c_pos] = -1;
	M[r_pos][c_pos + 1] = 0;
}

void two() {
	two_sub(1, r_pos, c_pos, 1, 3);
	two_sub(0, r_pos - 1, c_pos, 3, 1);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	input();

	for (int t = 0; t < T; t++) {
		one();
		two();
	}

	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			answer += M[r][c];
		}
	}
	answer += 2;
	cout << answer;

	return 0;
}