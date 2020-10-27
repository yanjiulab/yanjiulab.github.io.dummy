#include <iostream>
using namespace std;

int main() 
{

    cout << "Data Type|Size|" << endl;
    cout << "---|---|---" << endl;
    cout << "bool|" << sizeof(bool) << "-byte|" << endl;

    cout << "char|" << sizeof(char) << "-byte|" << endl;
    cout << "signed char|" << sizeof(signed char) << "-byte|" << endl;
    cout << "unsigned char|" << sizeof(unsigned char) << "-byte|" << endl;
    cout << "char16_t|" << sizeof(char16_t) << "-byte|" << endl;
    cout << "char32_t|" << sizeof(char32_t) << "-byte|" << endl;
    cout << "wchar_t|" << sizeof(wchar_t) << "-byte|" << endl;

    cout << "short [int]|" << sizeof(short) << "-byte|" << endl;
    cout << "unsigned short [int]|" << sizeof(unsigned short) << "-byte|" << endl;
    cout << "int|" << sizeof(int) << "-byte|" << endl;
    cout << "unsigned [int]|" << sizeof(unsigned int) << "-byte|" << endl;
    cout << "long [int]|" << sizeof(long) << "-byte|" << endl;
    cout << "unsigned long [int]|" << sizeof(unsigned long) << "-byte|" << endl;
    cout << "long long [int]|" << sizeof(long long) << "-byte|" << endl;
    cout << "unsigned long long [int]|" << sizeof(unsigned long long) << "-byte|" << endl;

    cout << "float|" << sizeof(float) << "-byte|" << endl;
    cout << "double|" << sizeof(double) << "-byte|" << endl;
    cout << "long double|" << sizeof(long double) << "-byte|" << endl;

    return 0;
}