#include <stdio.h>

#include <stdlib.h>

int N, count;

int queen[15];

int pass(int line)
{
    for (int i = 0; i < line; i++)
    {
        if (queen[i] == queen[line] || abs(queen[line] - queen[i]) == line - i)
            return 0;
    }
    return 1;
}

void nQueen(int line)
{
    if (line == N - 1)
    {
        count++;
        return;
    }
    for (int i = 0; i < N; i++)
    {
        queen[line + 1] = i;
        if (pass(line + 1))
        {
            nQueen(line + 1);
        }
    }
}

int main()
{
    scanf("%d", &N);
    nQueen(-1);
    printf("%d", count);
}