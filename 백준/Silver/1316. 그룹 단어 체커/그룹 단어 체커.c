#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>

int check(char w[]);

int main() {
	char word[101];
	int num, res=0;
	scanf("%d", &num);
	for (int i=0 ; i<num ; i++) {
		scanf("%s", word);
		if (check(word))res++;
	}
	printf("%d", res);
	return 0;
}

int check(char w[]) {
	int a[26] = { 0 };
	for (int i = 0; w[i] != '\0'; i++) {
		if (w[i] != w[i + 1]) {
			if (a[w[i] - 'a'] == 0)
				a[w[i] - 'a'] = 1;
			else
				return 0;
		}
	}
	return 1;
}