#include<stdio.h>
#include<math.h>

int prime(int num);

int main() {
    int m, n;
    scanf("%d %d", &m, &n);
    for (int i = m; i <= n; i++) {
        if (prime(i))printf("%d ", i);
    }
    return 0;
}

int prime(int num) {
    if (num == 1)return 0;
    for (int i = 2; i <= sqrt((double)num); i++) {
        if (num % i == 0)return 0;
    }
    return 1;
}