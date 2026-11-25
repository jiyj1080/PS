#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<string.h>

int main() {
	int n, idx = 0, first = 0, size = 0, que[2000000];
	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		char input[6] = { 0 };
		scanf("%s", input);
		if (!strcmp(input, "push")) {
			int a;
			scanf("%d", &a);
			que[idx] = a;
			size++;
			idx++;
		}
		else if (!strcmp(input, "pop")) {
			if (size == 0) {
				printf("-1\n");
			}
			else {
				size--;
				printf("%d\n", que[first]);
				que[first] = 0;
				first++;
			}
		}
		else if (!strcmp(input, "size")) {
			printf("%d\n", size);
		}
		else if (!strcmp(input, "empty")) {
			if (size == 0)
				printf("1\n");
			else
				printf("0\n");
		}
		else if (!strcmp(input, "front")) {
			if (size == 0)
				printf("-1\n");
			else
				printf("%d\n", que[first]);
		}
		else if (!strcmp(input, "back")) {
			if (size == 0)
				printf("-1\n");
			else
				printf("%d\n", que[idx - 1]);
		}
	}

	return 0;
}