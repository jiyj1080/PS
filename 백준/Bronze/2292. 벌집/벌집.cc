#include<stdio.h>
int main(){
    int a, b=1, res=1;
    scanf("%d", &a);
    while(a>b){
        b = b + 6*res;
        res++;
    }
    printf("%d", res);
    return 0;
}