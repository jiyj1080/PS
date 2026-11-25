#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int N, min = 1000000000, max = -1000000000;
int array[101], op[100];
int index = 1;

void insert(int value, int count) {
	for (int i=0; i<count; i++)
		op[index++] = value;
}

void swap(int a, int b) {
	int temp = op[a];
	op[a] = op[b];
	op[b] = temp;
}

void calculate() {
	int sum = array[1];
	for (int i = 1; i < N; i++) {
		switch (op[i]) {
			case 1:
				sum += array[i + 1];
				break;
			case 2:
				sum -= array[i + 1];
				break;
			case 3:
				sum *= array[i + 1];
				break;
			case 4:
				sum /= array[i + 1];
				break;
		}
	}
	if (sum > max)
		max = sum;
	if (sum < min)
		min = sum;
}

void permutation(int start) {
	if (start == N - 1) {
		calculate();
	}
	for (int i = start; i < N; i++) {
		if (i != start && op[i] == op[start])
			continue;
		swap(i, start);
		permutation(start + 1);
		swap(i, start);
	}

}

int main() {

	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		scanf("%d", &array[i]);
	
	int temp;
	for (int i = 1; i <= 4; i++) {
		scanf("%d", &temp);
		if (temp != 0) {
			insert(i, temp);
		}
	}

	permutation(1);

	printf("%d\n%d\n", max, min);

	return 0;

}