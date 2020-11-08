#include <stdio.h>
#include <string.h>

void dump(char* str);

int main()
{
    char s[5] = { 's', 'a', '\0', 'c', 'h' };

    char membuff[5] = { 0 };
    char strbuff[5] = { 0 };

    strcpy(strbuff, s);
    memcpy(membuff, s, 5);

    dump(membuff); // 73 61 00 63 68  sa ch
    dump(strbuff); // 73 61 00 00 00  sa

    return 0;
}

void dump(char* str)
{
    char* p = str;
    for (int n = 0; n < 5; ++n) {
        printf("%2.2x ", *p);
        ++p;
    }
    printf("\t");
    p = str;
    for (int n = 0; n < 5; ++n) {
        printf("%c", *p ? *p : ' ');
        ++p;
    }
    printf("\n");
}