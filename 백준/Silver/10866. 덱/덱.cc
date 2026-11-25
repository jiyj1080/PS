#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<string.h>

int front = 10001, back = 10000, deque[20001];

int main() {
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		char str[11] = { 0 };
		scanf("%s", str);
		if (!strcmp(str, "push_front")) {
			int x;
			scanf("%d", &x);
			deque[front++] = x;
		}
		else if (!strcmp(str, "push_back")) {
			int x;
			scanf("%d", &x);
			deque[back--] = x;
		}
		else if (!strcmp(str, "pop_front")) {
			if ((front - back - 1) == 0)
				printf("-1\n");
			else {
				printf("%d\n", deque[front - 1]);
				deque[front - 1] = 0;
				front--;
			}
		}
		else if (!strcmp(str, "pop_back")) {
			if ((front - back - 1) == 0)
				printf("-1\n");
			else {
				printf("%d\n", deque[back + 1]);
				deque[back + 1] = 0;
				back++;
			}
		}
		else if (!strcmp(str, "size")) {
			printf("%d\n", front - back - 1);
		}
		else if (!strcmp(str, "empty")) {
			if ((front - back - 1) == 0)
				printf("1\n");
			else
				printf("0\n");
		}
		else if (!strcmp(str, "front")) {
			if ((front - back - 1) == 0)
				printf("-1\n");
			else
				printf("%d\n", deque[front - 1]);
		}
		else if (!strcmp(str, "back")) {
			if ((front - back - 1) == 0)
				printf("-1\n");
			else
				printf("%d\n", deque[back + 1]);
		}
	}
	return 0;
}