#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>

void push(int num);
void pop();
int t = 0, arr[100000] = { 0 };

int main() {
	int k, res = 0;
	scanf("%d", &k);
	for (int i = 0; i < k;  i++) {
		int a;
		scanf("%d", &a);
		if (a == 0)
			pop();
		else
			push(a);
	}

	for (int i = 0; i<t; i++)
		res += arr[i];
	printf("%d", res);
	return 0;
}

void push(int num) {
	arr[t++] = num;
}

void pop() {
	arr[t--] = 0;
}