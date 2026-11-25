#include<iostream>
#include<vector>
 
using namespace std;
 
int n, m, people = 0;
 
vector < vector <char> > v;
vector <char> v2;
 
vector <vector<int>> visited;
vector<int> v3;
 
int dx[4] = { 1, -1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };
 
void bfs(int x, int y) {
    for (int i = 0; i < 4; i++) {
        int a, b;
 
        a = x + dx[i];
        b = y + dy[i];
        if (a >= 0 && b >= 0 && a < m && b < n && !visited[b][a] && v[b][a] != 'X') {
            visited[b][a] = 1;
            if (v[b][a] == 'P') {
                people++;
            }
            bfs(a, b);
        }
    }
}
 
int main() {
    ios::sync_with_stdio(false); 
    cout.tie(NULL); 
    cin.tie(NULL);
    
    int x, y;
 
    cin >> n >> m;
 
    for (int i = 0; i < n; i++) {
        v.push_back(v2);
        visited.push_back(v3);
    }
 
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            char a;
 
            cin >> a;
 
            if (a == 'I') {
                y = i;
                x = j;
            }
 
            visited[i].push_back(0);
            v[i].push_back(a);
        }
    }
 
    bfs(x, y);
 
    if (people == 0) {
        cout << "TT";
    }
    else {
        cout << people;
    }
 
}