#include<stdio.h>
int main() {
    int a, b, cycle = 1;
    char num[2], aa[2];
    scanf("%d", &a);
    num[0] = a / 10;
    num[1] = a % 10;
    aa[0] = num[0];
    aa[1] = num[1];
    b = (num[0] + num[1])%10;
    num[0] = num[1];
    num[1] = b;
    while (aa[0]!=num[0]||aa[1]!=num[1] ) {
        b = (num[0] + num[1])%10;
        num[0] = num[1];
        num[1] = b;
        cycle++;
    }
    printf("%d", cycle);
    return 0;
}