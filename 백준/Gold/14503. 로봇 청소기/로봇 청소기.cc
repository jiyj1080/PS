#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int N, M, r, c, d, ans = 0;
bool able = true;
int info[50][50];

const int dr[] = { -1, 0, 1, 0 };
const int dc[] = { 0, 1, 0, -1 };

void input() {
	cin >> N >> M >> r >> c >> d;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> info[i][j];
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	input();

	while (able) {
		if (info[r][c] == 0) {
			ans++; info[r][c]--;
		}
		else if (info[r + dr[0]][c + dc[0]] != 0 &&
			info[r + dr[1]][c + dc[1]] != 0 &&
			info[r + dr[2]][c + dc[2]] != 0 &&
			info[r + dr[3]][c + dc[3]] != 0
			) {
			int nr, nc;
			nr = r - dr[d]; nc = c - dc[d];
			if (info[nr][nc] != 1) {
				r = nr; c = nc;
			}
			else {
				able = false;
			}
			continue;
		}
		else {
			d = (d + 3) % 4;
			int nr, nc;
			nr = r + dr[d]; nc = c + dc[d];
			if (info[nr][nc] == 0) {
				r = nr; c = nc;
			}
			continue;
		}
	}
	cout << ans;
	return 0;
}