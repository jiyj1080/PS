#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
#include <queue>
#include <utility>

using namespace std;

int N, K, L, T;
int x, y, d, rot_idx;
int M[100][100];
int rot[100][2];
queue<pair<int, int>> snake;

const int dx[] = { 0, 1, 0, -1 };
const int dy[] = { 1, 0, -1, 0 };

void input() {
	cin >> N >> K;
	int x, y;
	for (int i = 0; i < K; i++) {
		cin >> x >> y;
		M[x - 1][y - 1] = 2;
	}
	cin >> L;
	int X;
	char C;
	for (int i = 0; i < L; i++) {
		cin >> X >> C;
		rot[i][0] = X;
		rot[i][1] = (C == 'D' ? 1 : 3);
	}
}

bool end_check(int x, int y) {
	if (x < 0 || x > N - 1 || y < 0 || y > N - 1 || M[x][y] == 1) return true;
	return false;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif
	T = 0;
	x = 0;
	y = 0;
	d = 0;
	rot_idx = 0;
	memset(M, 0, sizeof(M));
	M[0][0] = 1;
	snake.push({ x, y });

	input();

	while (true) {
		T++;
		int nx, ny;
		nx = x + dx[d];
		ny = y + dy[d];
		if (end_check(nx, ny)) {
			break;
		}
		x = nx; y = ny;
		int apple = M[x][y];
		M[x][y] = 1;
		snake.push({ x, y });
		if (apple != 2) {
			pair<int, int> tail = snake.front();
			snake.pop();
			M[tail.first][tail.second] = 0;
		}
		if (T == rot[rot_idx][0]) {
			d = (d + rot[rot_idx][1]) % 4;
			rot_idx++;
		}
	}
	cout << T;
	return 0;
}