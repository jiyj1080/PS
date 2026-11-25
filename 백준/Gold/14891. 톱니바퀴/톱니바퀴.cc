#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>

using namespace std;

int K;
bool wheel[4][8];
int rot[100][2];

bool link[3];

void input() {
	for (int i = 0; i < 4; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < 8; j++) {
			wheel[i][j] = s[j] == '1';
		}
	}
	cin >> K;
	for (int k = 0; k < K; k++) {
		cin >> rot[k][0] >> rot[k][1];
	}
}

void link_update() {
	link[0] = wheel[0][2] != wheel[1][6];
	link[1] = wheel[1][2] != wheel[2][6];
	link[2] = wheel[2][2] != wheel[3][6];
}

void rotate(int w, int dir) {
	int tmp;
	if (dir == 1) {
		tmp = wheel[w][7];
		for (int i = 7; i > 0; i--) {
			wheel[w][i] = wheel[w][i - 1];
		}
		wheel[w][0] = tmp;
	}
	else {
		tmp = wheel[w][0];
		for (int i = 0; i < 7; i++) {
			wheel[w][i] = wheel[w][i + 1];
		}
		wheel[w][7] = tmp;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	input();

	for (int k = 0; k < K; k++) {
		int w_num = rot[k][0];
		int dir = rot[k][1];

		link_update();

		switch (w_num) {
		case 1:
			rotate(0, dir);
			if (link[0]) {
				rotate(1, dir * (-1));
				if (link[1]) {
					rotate(2, dir);
					if (link[2]) {
						rotate(3, dir * (-1));
					}
				}
			}
			break;
		case 2:
			rotate(1, dir);
			if (link[0]) {
				rotate(0, dir * (-1));
			}
			if (link[1]) {
				rotate(2, dir * (-1));
				if (link[2]) {
					rotate(3, dir);
				}
			}
			break;
		case 3:
			rotate(2, dir);
			if (link[2]) {
				rotate(3, dir * (-1));
			}
			if (link[1]) {
				rotate(1, dir * (-1));
				if (link[0]) {
					rotate(0, dir);
				}
			}
			break;
		case 4:
			rotate(3, dir);
			if (link[2]) {
				rotate(2, dir * (-1));
				if (link[1]) {
					rotate(1, dir);
					if (link[0]) {
						rotate(0, dir * (-1));
					}
				}
			}
		}
	}

	cout << (wheel[0][0] + wheel[1][0] * 2 + wheel[2][0] * 4 + wheel[3][0] * 8);
	return 0;
}