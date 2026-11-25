#include <stdio.h>

int main(void) {
	int i, j, key;
	int num;
	int number[1000] = { 0, };

	scanf("%d", &num);

	for (i = 0; i < num; i++)
		scanf("%d", &number[i]);

	for (i = 1; i < num; i++) {
		key = number[i];
		for (j = i - 1; j >= 0 && number[j] > key; j--) {
			number[j + 1] = number[j];
			number[j] = key;
		}
	}

	for (int i = 0; i < num; i++)
		printf("%d ", number[i]);

	return 0;
}