---
title: Operating System
date: 2019-10-07 19:56:42
comments: false
---
在操作系统的实际学习中，其与计算机组成原理以及计算机体系架构有着很强的关联。因此，本部分内容以操作系统为主，且辅以计算机系统和计算机架构等相关的知识。正如 {% button http://csapp.cs.cmu.edu/3e/home.html, Computer Systems: A Programmer's Perspective, 3/E (CS:APP3e), home fa-fw fa-lg %} 这本书所展示的，只有对操作系统、计算机系统，甚至硬件具有相当的了解，才能写出更好的代码。

从程序员的角度来看，操作系统的学习包括两方面：
1. 操作系统的概念：为何要有某种机制？以及 OS 如何实现这种机制？
2. 操作系统的应用：OS 为软件提供了哪些功能？程序员如何使用这些功能？

# 介绍
- [操作系统介绍](/2020/08/03/os-intro/)
- [Unix/Linux 介绍](/2020/10/21/os-unix/)

# 进程
- [完成 90%+] [进程](/2020/08/02/os-process/)
- [0%] [进程间通信]

# 虚拟内存
- [虚拟内存](/2020/08/03/os-vm)
- [超越物理内存]()

# 并发
## 线程并发
- [线程及其 API](/2020/10/21/os-thread/) --- 并发梦开始的地方
- [锁](/2020/07/31/os-lock/) --- 线程并发的基石
- 基于锁的并发数据结构 --- 锁在不同场景下的性能
- [条件变量](/2020/08/01/os-cv/) --- 另一种并发的原语
- 信号量 --- 作为同步的唯一原语统一锁和条件变量
- 线程并发的问题 --- 原子性缺陷、顺序性缺陷、可见性缺陷、死锁

## 事件并发




# 文件系统实现

# 杂

## Unix I/O 模型
对于一次 IO 访问 (以 read 举例) ，数据会先被拷贝到操作系统内核的缓冲区中，然后才会从操作系统内核的缓冲区拷贝到应用程序的地址空间。所以说，当一个 read 操作发生时，它会经历两个阶段：
1. 等待数据准备完毕 (Waiting for the data to be ready)
2. 将数据从内核拷贝到进程中 (Copying the data from the kernel to the process)

Unix 下有五种基本的 I/O 模型：
- 阻塞 I/O (blocking I/O) 
- 非阻塞 I/O (nonblocking I/O) 
- I/O 多路复用 (IO multiplexing) 
- 信号驱动 I/O (signal driven IO) 
- 异步 I/O (asynchronous IO) 

# I/O 设备
## 系统架构
分层架构？物理布局和造价成本
- 越快越短
- 越快越贵

[prototypical-system-architecture](prototypical-system-architecture.png)

## 标准设备
- interface
- internal

![device](device.png)

## 标准协议
### 轮询 (polling)
### 中断 (interrupt)
合并 (coalescing)
### DMA

## 操作系统与设备
### 设备交互
- 明确的 I/O 指令
- 内存映射 I/O

### 设备驱动程序

## 典型 I/O 设备
### Hard Disk Driver
### RAID (Redundant Array of Inexpensive Disk)
评估 RAID：
- 容量
- 可靠性
- 性能

Raid
- RAID 0：无冗余
- RAID 1：镜像
    - RAID 10
    - RAID 01
- RAID 4：奇偶校验
- RAID 5：旋转奇偶校验

# 文件和目录
## 文件和目录
## 文件系统接口

接口|系统调用
---|---
创建文件|open()|
读取文件|read()|
写入文件|write()|
文件偏移|lseek()|
立即写入|fsync()|
文件重命名|rename()|mv
获取文件信息|stat()/fstat()|stat
删除文件|unlink()|rm
创建目录|mkdir()|mkdir
读取目录|opendir(), readdir(), closedir()|ls
删除目录|rmdir()|rmdir

{% note info %}
- 文件描述符
- strace 跟踪系统调用
{% endnote %}


# 参考
## 书籍
- [Operating Systems: Three Easy Pieces](http://pages.cs.wisc.edu/~remzi/OSTEP/)
- [操作系统导论](http://pages.cs.wisc.edu/~remzi/OSTEP/Chinese/01.pdf)
- [Advanced Programming in the UNIX® Environment, Third Edition](http://www.apuebook.com/about3e.html)
- [UNIX Network Programming - Volume 1 Socket Programming, Third Edition](http://www.unpbook.com/)
- UNIX Network Programming - volume 2 IPC, Second Edition

## 文章
- [Process state](https://en.wikipedia.org/wiki/Process_state)
- [CPU Scheduling in Operating Systems](https://www.geeksforgeeks.org/cpu-scheduling-in-operating-systems/)

## 问答
- [What does the Kernel Virtual Memory of each process contain?](https://stackoverflow.com/questions/2445242/what-does-the-kernel-virtual-memory-of-each-process-contain)

