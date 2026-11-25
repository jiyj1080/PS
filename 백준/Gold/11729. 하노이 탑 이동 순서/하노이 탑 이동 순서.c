#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<math.h>

int move(int a, int b);
int hanoi(int num, int a, int b, int c);

int main() {
	int num, count;
	scanf("%d", &num);
	count = pow(2, num) - 1;
	printf("%d\n", count);
	hanoi(num, 1, 2, 3);
	return 0;
}

int move(int a, int b) {
	printf("%d %d\n", a, b);
}

int hanoi(int num, int a, int b, int c) {
	if (num == 1)move(a, c);
	else {
		hanoi(num - 1, a, c, b);
		move(a, c);
		hanoi(num - 1, b, a, c);
	}
}