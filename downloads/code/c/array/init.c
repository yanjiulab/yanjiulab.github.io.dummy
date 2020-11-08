#include <stdio.h>
#include <string.h>

void dump_int(int* str);
void dump_char(char* str);

int arr_g[5];

int main()
{
    static int arr_s[5];
    int arr1[5];
    int arr2[5] = { 0 };
    char arr3[5];
    char arr4[5] = "abc";

    dump_int(arr_g); //(int) 0 0 0 0 0
    dump_int(arr_s); //(int) 0 0 0 0 0
    dump_int(arr1); // (int)  0 0 1346658496 21995 407207840
    dump_int(arr2); // (int)  0 0 0 0 0
    dump_char(arr3); // (char)  NUL NUL -32 -124 t
    dump_char(arr4); // (char) a b c NUL NUL

    return 0;
}

void dump_int(int* str)
{
    printf("(int) ");
    for (int n = 0; n < 5; ++n) {
        printf("%d ", *(str + n));
    }
    printf("\n");
}

void dump_char(char* str)
{
    printf("(char) ");
    for (int n = 0; n < 5; ++n) {
        if (str[n] < 0 || str[n] > 127) {
            printf("%d ", str[n]);
        } else if (str[n] == 0) {
            printf("NUL ");
        } else {
            printf("%c ", str[n]);
        }
    }
    printf("\n");
}