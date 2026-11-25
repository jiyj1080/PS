#include <stdio.h> 
#include <algorithm> 
#include <vector> 
#include <queue> 
#include <map> 
#define MAX 100000 
using namespace std; 

map<int, int> map2; 

priority_queue <int, vector<int>, greater<int> > q; 

int main(void){ 
	int n, num; 
	scanf("%d", &n); 
	for(int i = 0; i < n; i++){ 
		scanf("%d", &num); 
		if(num < 0){ 
			num *= -1; 
			map2[num] += 1; 
			q.push(num); 
		} 
		else if(num == 0){ 
			if(q.size() == 0){ 
				printf("0\n"); 
			} 
			else{ 
				if(map2[q.top()] > 0){ 
					map2[q.top()] -= 1; 
					num = q.top() * -1 ; 
					printf("%d\n", num); q.pop(); 
				} 
				else{ 
					printf("%d\n", q.top()); 
					q.pop(); 
				} 
			} 
		} 
		else{ 
			q.push(num); 
		} 
	} 
}