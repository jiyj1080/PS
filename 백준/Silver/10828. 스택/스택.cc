#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<string.h>

int main() {
	int n, size = 0, stack[10000];
	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		char input[6] = { 0 };
		scanf("%s", input);
		if (!strcmp(input, "push")) {
			int a;
			scanf("%d", &a);
			stack[size] = a;
			size++;
		}
		else if (!strcmp(input, "pop")) {
			if (size == 0) {
				printf("-1\n");
			}
			else {
				size--;
				printf("%d\n", stack[size]);
				stack[size] = 0;
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
		else if (!strcmp(input, "top")) {
			if (size == 0)
				printf("-1\n");
			else
				printf("%d\n", stack[size-1]);
		}
	}

	return 0;
}
