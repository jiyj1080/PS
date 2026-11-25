#include<stdio.h>
int main() {
    int num, a;
    scanf("%d", &num);
    a = num;
    for (int i = 2; i <= a; i++) {
        if (num == 1)return;
        while (num % i == 0) {
            printf("%d\n", i);
            num = num / i;
        }
    }
    return 0;
}