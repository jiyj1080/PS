#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <utility>
#include <cstring>
#include <queue>
#include <tuple>
#include <vector>
#include <algorithm>

using namespace std;

int N;
int M[20][20];
int r_s, c_s, T, size_s, eat;

const int dr[] = { 0, 1, 0, -1 };
const int dc[] = { 1, 0, -1, 0 };

void input() {
	T = 0;
	size_s = 2;
	eat = 0;
	cin >> N;
	for (int r = 0; r < N; r++) {
		for (int c = 0; c < N; c++) {
			cin >> M[r][c];
			if (M[r][c] == 9) {
				r_s = r; c_s = c;
				M[r][c] = 0;
			}
		}
	}
}

tuple<int, int, int> bfs(int r, int c) {
	queue<pair<int, int>> q;
	vector<tuple<int, int, int>> fish;
	bool visited[20][20] = { false };
	int distance[20][20] = { 0 };

	q.push({ r, c });
	visited[r][c] = true;

	while (!q.empty()) {
		int rr = q.front().first;
		int cc = q.front().second;
		q.pop();

		for (int d = 0; d < 4; d++) {
			int nr, nc;
			nr = rr + dr[d];
			nc = cc + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			else if (visited[nr][nc] || M[nr][nc] > size_s) continue;

			q.push({ nr, nc });
			visited[nr][nc] = true;
			distance[nr][nc] = distance[rr][cc] + 1;
			if (M[nr][nc] > 0 && M[nr][nc] < size_s) {
				fish.push_back({ distance[nr][nc], nr, nc });
			}
		}
	}

	if (!fish.empty()) {
		sort(fish.begin(), fish.end());
		return fish.front();
	}
	else {
		return make_tuple(0, r, c);
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
#endif

	input();

	while (true) {
		tuple<int, int, int> fish = bfs(r_s, c_s);
		if (!get<0>(fish)) break;
		T += get<0>(fish);
		r_s = get<1>(fish);
		c_s = get<2>(fish);
		M[r_s][c_s] = 0;
		eat++;
		if (eat == size_s) {
			size_s++;
			eat = 0;
		}
	}
	cout << T;
	return 0;
}