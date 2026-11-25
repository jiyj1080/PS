#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <ctime>
#include <iomanip>

using namespace std;

int main() {
	time_t timer = time(NULL);
	struct tm* t = localtime(&timer);
	cout << t->tm_year + 1900 << "-" 
		<< setw(2) << setfill('0') << t->tm_mon + 1 << "-"
		<< setw(2) << setfill('0') << t->tm_mday;
}