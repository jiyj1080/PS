#include <stdio.h>
#include <stdlib.h>
typedef struct info {
	int n;
	int age;
	char name[101];
}info;
info infoo[100001];
int compare(const void* a, const void* b);

int main() {
	int i, N, count = 1;
	scanf("%d", &N);
	for (i = 0; i < N; i++) {
		scanf("%d %s", &infoo[i].age, infoo[i].name);
		infoo[i].n = count++;
	}
	qsort(infoo, N, sizeof(info), compare);
	for (i = 0; i < N; i++) {
		printf("%d %s\n", infoo[i].age, infoo[i].name);
	}
}

int compare(const void* a, const void* b) {
	info* ptrA = (info*)a;
	info* ptrB = (info*)b;

	if (ptrA->age < ptrB->age)
		return -1;
	else if (ptrA->age > ptrB->age)
		return 1;
	else {
		if (ptrA->n < ptrB->n)
			return -1;
		else if (ptrA->n > ptrB->n)
			return 1;
		return 0;
	}
}