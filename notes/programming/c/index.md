---
title: c
date: 2020-09-09 09:15:49
---
# 数据类型
C 语言标准层面没有明确定义基本数据类型的大小，只能确定 sizeof(char) <= sizeof(short) <= sizeof(int) <= sizeof(long) <= sizeof(long long)。

具体某种类型的大小与编译器和系统有关，具体来说和实现采用的**数据模型**有关，

![primitive-type-bits](primitive-type-bits.svg)

- LLP64: Microsoft Windows (x86-64 and IA-64) using Visual C++; and MinGW
- LP64: Most Unix and Unix-like systems, e.g., Solaris, Linux, BSD, macOS. Windows when using Cygwin; z/OS

{% note info %}
当数据类型的位数重要时：
- 请使用 stdint.h 中定义的 fixed-size types，例如 int8_t、uint32_t 等。
- 或使用 sizeof() 计算位数。
{% endnote %}



# 指针
我们知道，每个变量代表一个存储位置，并且每个存储位置都有一个可以使用 `＆` 运算符进行访问的内存地址，该地址表示内存中的地址。

指针就是一个变量，该变量存储的值为另一个变量的地址。也就是说，指针就是一个内存地址。

关于指针：
- **总是将指针初始化为 NULL 是一个好习惯**！
- 指针可以进行加减运算（`++`、`--`、`+`、`-`），其加减的**单位为指向数据类型的大小**。
- 指针与普通类型一样也可以存储在数组中，成为指针数组。
- 指针与普通类型一样也可以被另一个指针指向，构成**多级指针**。
- 指针可以作为参数传给函数，可以在调用函数时改变该参数的值。
- 指针可以作为函数返回值，**一般用于动态内存分配**时返回该堆指针，需要注意若该指针指向局部变量，则在函数退出时，该变量也不复存在。

# 数组
## 数组初始化
1. 局部数组未初始化时，其值为随机值。
2. 全局或静态数组未初始化时，其值 NUL 或 0。

{% note info %}
当数组初始化之后，会将未初始化的位置设置为 NUL 或 0，应当养成每次都初始化数组的好习惯。
{% endnote %}

{% include_code c/array/init.c %}

# 字符串
## 表示方式
C 语言使用**字符数组**来表示字符串，字符数组和字符串之间的区别在于**字符串以特殊字符 `\0` 结尾**。
{% codeblock lang:c %}
char array[5] = {'h', 'e', 'l', 'l', 'o'};
char str[6] = "hello";
char str1[6] = {'h', 'e', 'l', 'l', 'o', '\0'}; // equivalent to str
{% endcodeblock %}

**字符指针**在某种程度上也可以表示字符串，这并不惊讶，因为字符数组名本身就是一个常指针。

char* str1 和 char str2[] 都可以表示字符串，然而两者具有很大区别：
- str1 是一个指向 char 变量的指针变量，指向一个字符串常量（其位于常量区），指针变量的值可以改变，而字符串常量的值不能改变。
- str2 是一个指针常量，指向一个字符数组，指针常量值不可改变，但数组内容可以改变。

{% codeblock lang:c %}
int main()
{
    char* str1 = "hello";
    char str2[10] = "world";
    printf("str1: %s\n", str1); // hello
    printf("str2: %s\n", str2); // world

    //str1[0] = 'W'; // Segment Fault
    str2[0] = 'W'; // OK
    printf("str2: %s\n", str2); // World

    str1 = "hello c language"; // OK
    printf("str1: %s\n", str1); // hello c language
    str1 = str2; // OK
    printf("str1: %s\n", str1); // World
    //str2 = "hello"; // Error，expression must be a modifiable lvalue

    return 0;
}
{% endcodeblock %}

同样，str1 和 str2 位于不同的内存区。显然，str2 位于栈，而 str1 同全局变量、堆内存一样位于堆中。 
{% codeblock lang:c %}
char str[10] = "C"; // address: 0x55a607eb1010
int main()
{
    char* str1 = "hello";   // address: 0x55a607eaf004
    char str2[10] = "world";  // address: 0x7ffc2940e042
    char* str3 = (char*) malloc(10); // address: 0x55a609b752a0
    ...
}
{% endcodeblock %}

## string.h 头文件
通常字符串函数都位于 `string.h` 头文件中。该头文件引入了以下内容：
- 一个变量：`size_t` 表示无符号整数
- 一个宏：`NULL` 表示空字符串常量
- 若干函数：主要可以分为两类：
    1. 以 `mem` 开头的操作内存字节的函数
    2. 以 `str` 开头的操作字符的函数

函数名|作用
---|---
strlen(str)|计算 str 长度，不包括 NUL 字符。
strcat(dst, src)|将 src 加到 dst 后面。
strcmp(fir, sec)|相同为 0，否则返回第一个不匹配字符的 ASCII 值的差，即 fir - sec。
strcpy(dst, src)|将 src 拷贝到 dst，遇到 NUL 字符停止。
strncpy(dst, src, n)|将 src 的 n 字节拷贝到 dst。<br>如果 src 的前 n 字节不包括 NUL 字符，则 dst 将不会有 NUL 结束符！<br>如果 src 不足 n 字节，则剩余空间使用 NUL 字符补齐。
memcpy(dst, src, n)|将 src 的 n 字节拷贝到 dst。
memset(s, c, n)|将 s 的 n 字节设为 c。

具有相同后缀的两种函数功能相似，但稍有不同。其中 `mem` 系列函数将字符序列视为字节操作，而 `str` 系列函数将字符序列视为字符串操作。逻辑上而言 `mem` 系列表达的语义更加底层。

## 字符串拷贝
以 `memcpy` vs `strcpy` 作为例子。

{% include_code c/string/str_mem_cpy.c %}

在网络编程时，应当使用 memcpy 拷贝数据。

## 字符串格式化
除了 `memcpy` 和 `strcpy` 以及其系列函数外，`sprintf` 函数也常用于字符串拷贝，但该函数操作的源对象不限于字符串，源对象可以是字符串、也可以是任意基本类型的数据。因此 `sprintf` 主要是实现将其他数据类型转换为字符串功能。同时，由于该函数属于 printf 家族函数，因此也可以完成字符串拼接效果。

{% codeblock lang:c %}
char path[100];
sprintf(path, "/proc/%d/uid_map", pid);
{% endcodeblock %}


# 结构体

## 内存布局与字节对齐
结构体字节对齐的细节和具体的编译器实现相关，但一般来说遵循 3 个准则：
- 结构体变量的首地址能够被其最宽基本类型成员的大小(sizeof)所整除。
- 结构体每个成员相对结构体首地址的偏移量 offset 都是成员大小的整数倍，如有需要编译器会在成员之间加上填充字节。
- 结构体的总大小 sizeof 为结构体最宽基本成员大小的整数倍，如有需要编译器会在最末一个成员之后加上填充字节。

直接使用 `pragma pack` 预处理宏可以改变结构体的字节对齐方式：
- `#pragma pack(n)`：结构体将按照 n 个字节对齐，其取值为 1、2、4、8、16，默认是 8。
- `#pragma pack(1)`：结构体没有填充字节，实现空间“无缝存储”，这对跨平台传输数据来说是友好和兼容的。

如果只想改变个别结构体的字节对齐方式，可以使用 GCC 编译器指定结构体**类型属性 (Type Attribute)** 为 packed，即： `__attribute__((__packed__))`。

{% codeblock lang:c %}
struct padding {
    char c; //1
    short s; //2
    int i; //4
    double d; //8
};
// sizeof() is 16

struct nopadding {
    char c; //1
    short s; //2
    int i; //4
    double d; //8
} __attribute__((__packed__));
// sizeof() is 15
{% endcodeblock %}

{% note info %}
`__attribute__` 必须紧挨着 struct 关键字或者结构体 `}` 之后，否则将会报错或不起作用。 
{% endnote %}

# 存储级别关键字
## static 关键字
- **静态局部变量**使用 static 修饰符定义，即使在声明时未赋初值，编译器也会把它初始化为 0，且静态局部变量存储于进程的全局数据区，即使函数返回，它的值也会保持不变。
- **静态全局变量**仅对当前文件可见，其他文件不可访问，其他文件可以定义与其同名的变量，两者互不影响。
- **静态函数**只能在声明它的文件中可见，其他文件不能引用该函数

{% note info %}
static 关键字标识该变量/函数作用域为本“模块”，C 语言以文件的方式来组织模块。

C++ 和 Java 的类成员变量/函数被 static 修饰时则为类共享，因为此时程序以类的方式组织模块。
{% endnote %}

## const 关键字
指定变量不可被当前线程/进程改变（但有可能被系统或其他线程/进程改变）。

## volatile 关键字
指定变量的值有可能会被系统或其他进程/线程改变，强制编译器每次从内存中取得该变量的值。

# 参考
- [GNU Compiler Collection](https://gcc.gnu.org/onlinedocs/gcc/index.html#SEC_Contents)
- [64 位数据模型](https://en.m.wikipedia.org/wiki/64-bit_computing#64-bit_data_models)