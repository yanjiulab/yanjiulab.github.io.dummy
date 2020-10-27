import sys
import os

# 读取一个用户变量
arg = input('Please input an argument: ')
print('The argument is: ', arg)
print(type(arg))
# 读取多个用户变量
try:
    while True:
        line =  sys.stdin.readline().strip()
        if line == '':
            break
        a = list(map(int,(line).split(' ')))
        print(a)
except:
     pass