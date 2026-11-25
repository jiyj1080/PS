#include<stdio.h>
int main(){
    int a[10], res;
    res=0;
    for(int i=0 ; i<10 ; i++){
        scanf("%d", &a[i]);
        a[i]=a[i]%42;
    }
    for(int i=0 ; i<10 ; i++){
        int b = 0;
        for(int j=0 ; j<i ; j++){
            if(a[i]==a[j])b++;
        }
        if(b==0)res+=1;
    }
    printf("%d", res);
    return 0;
}