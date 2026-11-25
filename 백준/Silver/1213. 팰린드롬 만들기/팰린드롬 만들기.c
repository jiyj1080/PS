#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>

int main() {
	char word[51], res[51], a[27] = { 0 };
	int len, odd = 0, oddalp=0, tem=0;
	scanf("%s", word);
	for (len = 0; word[len] != '\0'; len++) {
		a[word[len] - 'A']++;
	}
	for (int i = 0; i < 26; i++)
		if (a[i] % 2 != 0) {
			odd++;
			oddalp = i;
		}
	if (len == 1)
		printf("%c", word[0]);
	else {
		int check;
		//길이 짝수
		if (len % 2 == 0) {
			if(odd!=0)
				check = 0;
			else {
				check = 1;
				for (int i = 0; i < 26; i++) {
					if (a[i] != 0){
						int k = a[i] / 2;
						while (a[i] > k) {
							res[tem] = 'A' + i;
							tem++;
							a[i]--;
						}
					}
				}
				for (int i = 25; i >= 0; i--) {
					if (a[i] != 0){
						int k = a[i] / 2;
						while (a[i] > 0) {
							res[tem] = 'A' + i;
							tem++;
							a[i]--;
						}
					}
				}
			}
		}
		//길이 홀수
		else {
			if (odd > 1)
				check = 0;
			else {
				check = 1;
				for (int i = 0; i < 26; i++) {
					if (a[i] > 1){
						int k = a[i] / 2;
						if (a[i] % 2 != 0)
							k++;
						while (a[i] > k) {
							res[tem] = 'A' + i;
							tem++;
							a[i]--;
						}
					}
				}
				res[tem] = 'A' + oddalp;
				tem++;
				a[oddalp]--;
				for (int i = 25; i >= 0; i--) {
					if (a[i] >= 1){
						int k = a[i] / 2;
						while (a[i] > 0) {
							res[tem] = 'A' + i;
							tem++;
							a[i]--;
						}
					}
				}
			}
		}
		//출력
		if (check == 1) {
			for (int i = 0; i < len; i++)
				printf("%c", res[i]);
		}
		else
			printf("I'm Sorry Hansoo");
	}
	return 0;
}
