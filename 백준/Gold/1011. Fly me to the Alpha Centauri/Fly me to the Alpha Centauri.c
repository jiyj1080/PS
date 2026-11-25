#include <stdio.h>

void Ptr(int a);

int main(void) { 
    int a, x, y, i; 
    scanf("%d", &a); 
    for(i = 0 ; i < a ; i++) { 
        scanf("%d %d", &x, &y); 
        Ptr(y - x); 
    } 
    return 0; 
}

void Ptr(int a) { 
    long i, j, cnt; 
    i = j = 2; 
    cnt = 0; 
    if(a == 1 || a == 2) { 
        printf("%d\n", (a == 1) ? 1 : 2); 
        return; 
    } 
    while(1) { 
        if(cnt == 2) { 
            i++; 
            cnt = 0; 
        } 
        j += i; 
        cnt++; 
        if(j >= a) break; 
    } 
    printf("%ld\n", (cnt==1) ? i*2-1 : i*2); 
} 