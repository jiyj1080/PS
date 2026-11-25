#include<iostream>

int n, num;
int stack[1000], top = 0, st = 1;

int main() {
	scanf("%d", &n);

	while (n--) {
		scanf("%d", &num);
		if (st == num) {
			st++;
		}
		else {
			while (top > 0 && stack[top - 1] == st) {
				top--;
				st++;
			}
			stack[top++] = num;
		}
	}
	while (top >= 1) {
		if (st == stack[top - 1]) {
			st++; 
			top--;
		}
		else {
			printf("Sad");
			return 0;
		}
	}
	printf("Nice");
	return 0;
}
