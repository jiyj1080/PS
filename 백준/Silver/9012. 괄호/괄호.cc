#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>

int VPS(char arr[]);

int main() {
	int k;
	scanf("%d", &k);
	for (int i = 0; i < k;  i++) {
		char arr[51] = { 0 };
		scanf("%s", arr);
		if (VPS(arr))
			printf("YES\n");
		else
			printf("NO\n");
	}
	return 0;
}
int VPS(char arr[]) {
	int a = 0;
	for (int i = 0; arr[i] != '\0'; i++) {
		if (arr[i] == '(') {
			a++;
		}
		else {
			if (a > 0)
				a--;
			else
				return 0;
		}
	}
	if (a == 0)
		return 1;
	else
		return 0;
}