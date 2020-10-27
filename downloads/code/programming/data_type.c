#include <stdio.h>

int main()
{
    printf("char size: %ld-byte \n", sizeof(char));
    printf("unsigned char size: %ld-byte \n", sizeof(unsigned char));
    printf("short [int] size: %ld-byte \n", sizeof(short));
    printf("unsigned short [int] size: %ld-byte \n", sizeof(unsigned short));
    printf("int size: %ld-byte \n", sizeof(int));
    printf("unsigned int size: %ld-byte \n", sizeof(unsigned int));
    printf("long [int] size: %ld-byte \n", sizeof(long));
    printf("unsigned long [int] size: %ld-byte \n", sizeof(unsigned long));
    printf("long long [int] size: %ld-byte \n", sizeof(long long));
    printf("unsigned long long [int] size: %ld-byte \n", sizeof(unsigned long long));
    printf("float size: %ld-byte \n", sizeof(float));
    printf("double size: %ld-byte \n", sizeof(double));
    return 0;
}
