#include<stdio.h>
int main(){
    int a[21], n;
    a[0]=0;
    a[1]=1;
    for(int i=0 ; i<19 ; i++){
        a[i+2]=a[i+1]+a[i];
    }
    scanf("%d", &n);
    printf("%d", a[n]);
}