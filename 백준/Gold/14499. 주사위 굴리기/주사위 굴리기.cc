#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int N, M, x, y, K, nxt;
int info[20][20];
int d[7] = { 0 };

const int dx[] = { 0, 0, -1, 1 };
const int dy[] = { 1, -1, 0, 0 };

void input() {
	cin >> N >> M >> x >> y >> K;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> info[i][j];
		}
	}
	return;
}

void dice(int d[7], int nxt) {
	int temp[7]; for (int i = 0; i < 7; i++) temp[i] = d[i];
	switch (nxt) {
	case 1:	// east
		if (info[x][y] == 0) {
			d[6] = temp[3];
			info[x][y] = d[6];
		}
		else {
			d[6] = info[x][y];
			info[x][y] = 0;
		}
		d[1] = temp[4];
		d[2] = temp[2];
		d[3] = temp[1];
		d[4] = temp[6];
		d[5] = temp[5];
		break;
	case 2:	// west
		if (info[x][y] == 0) {
			d[6] = temp[4];
			info[x][y] = d[6];
		}
		else {
			d[6] = info[x][y];
			info[x][y] = 0;
		}
		d[1] = temp[3];
		d[2] = temp[2];
		d[3] = temp[6];
		d[4] = temp[1];
		d[5] = temp[5];
		break;
	case 3:	// north
		if (info[x][y] == 0) {
			d[6] = temp[2];
			info[x][y] = d[6];
		}
		else {
			d[6] = info[x][y];
			info[x][y] = 0;
		}
		d[1] = temp[5];
		d[2] = temp[1];
		d[3] = temp[3];
		d[4] = temp[4];
		d[5] = temp[6];
		break;
	case 4:	// south
		if (info[x][y] == 0) {
			d[6] = temp[5];
			info[x][y] = d[6];
		}
		else {
			d[6] = info[x][y];
			info[x][y] = 0;
		}
		d[1] = temp[2];
		d[2] = temp[6];
		d[3] = temp[3];
		d[4] = temp[4];
		d[5] = temp[1];
		break;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	input();

	for (int i = 0; i < K; i++) {
		cin >> nxt;
		x += dx[nxt - 1];
		y += dy[nxt - 1];
		if (x < 0 || x > N - 1 || y < 0 || y > M - 1) {
			x -= dx[nxt - 1];
			y -= dy[nxt - 1];
			continue;
		}
		else {
			//if (i) cout << "\n";
			dice(d, nxt);
			cout << d[1] << "\n";
		}
	}
	return 0;
}