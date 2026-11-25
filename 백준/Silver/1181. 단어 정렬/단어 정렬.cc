#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int compare(const void* a, const void* b) {
	int alen = strlen((char*)a);
	int blen = strlen((char*)b);
	if (alen == blen) return strcmp((char*)a, (char*)b); //같으면 즉시 사전정렬
	else if (alen > blen) return 1;
	else return -1;
}

int main()
{
	int N;
	char arr[20001][51];
	scanf("%d", &N);
	for (int i = 0; i < N; i++) scanf("%s", arr[i]);

	qsort(arr, N, sizeof(arr[0]), compare);

	for (int i = 0; i < N; i++) {
		if (!strcmp(*(arr + i), *(arr + i + 1))) continue;
		else printf("%s\n", arr + i);
	}
}