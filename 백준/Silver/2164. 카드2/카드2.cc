#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>

void push(int num);
void pop();
int front = 0, end = 0, arr[1000000] = { 0 };

int main() {
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		push(i + 1);
	}
	for (int i = 0; (end - front) != 1; i++) {
		pop();
		push(arr[front]);
		pop();
	}
	printf("%d", arr[end-1]);
	return 0;
}

void push(int num) {
	arr[end++] = num;
}

void pop() {
	arr[front++] = 0;
}