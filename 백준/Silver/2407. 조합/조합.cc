#include <iostream>
#include <string>

using namespace std;

// nCm : pascal[i][j]
string pascal[101][101];

string add_string_num(string a, string b)
{
    int aidx = a.size() - 1;
    int bidx = b.size() - 1;
    string result = "";
    int carry = 0;

    while (aidx >= 0 && bidx >= 0)
    {
        int temp = a[aidx--] - '0' + b[bidx--] - '0' + carry;
        //int temp = stoi("123");
        result = to_string(temp % 10) + result;
        carry = temp / 10;
    }

    while (aidx >= 0)
    {
        int temp = a[aidx--] - '0' + carry;
        result = to_string(temp % 10) + result;
        carry = temp / 10;
    }

    while (bidx >= 0)
    {
        int temp = b[bidx--] - '0' + carry;
        result = to_string(temp % 10) + result;
        carry = temp / 10;
    }
    if (carry)
        result = to_string(carry) + result;
    return result;
}

int main()
{
    int n, m;
    cin >> n >> m;

    pascal[0][0] = "1";

    for (int i = 1; i < 101; i++)
    {
        for (int j = 0; j < i + 1; j++)
        {
            if (j - 1 >= 0)
                pascal[i][j] = add_string_num(pascal[i - 1][j - 1], pascal[i - 1][j]);
            else
                pascal[i][j] = pascal[i - 1][j];
        }
    }

    cout << pascal[n][m] << "\n";
}