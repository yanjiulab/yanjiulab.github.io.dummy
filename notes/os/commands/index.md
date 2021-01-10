---
title: Linux Commands
date: 2019-11-18 10:27:30
comments: false
---

学习 Linux 主要包括两种程序员：
1. 使用 Linux 完成工作。
2. 工作的内容是 Linux 的方方面面。

第一类人是 Linux 的使用者，本分版的内容是笔者作为 Linux 使用者的一个总结，不涉及第二类人所需的 Linux 内核等操作系统实现的知识。本版内容主要介绍各种各样的**命令行工具 (command-line tools)**，或称之为命令、命令行软件等。大致可以分为两种：
- 操作系统自带的命令或软件 (包括不同 Linux 版本自带的命令或软件)
- 用户安装的命令行界面软件 (包括 GUI 软件提供的命令行工具)

前者解决了有无的问题，自带的命令/软件保证了我们对于该操作系统可以进行基本的操作，而后者解决了使用体验好坏的问题，自行安装喜欢的软件或许可以提高我们的工作效率。

Windows 系统的操作风格是面向界面 (Graphic User Interface, GUI) 的，而 *nix (包括 Unix、Linux、macOS 以及其他 Unix-like 系统) 操作系统的操作风格是面向命令行 (Command Line Interface, CLI) 的。两种方式各有利弊，所以两种操作系统在各自偏好的操作风格的基础上，都提供了 GUI 和 CLI 两种操作方式，例如 Windows 的 CMD/PowerShell 以及 Linux 的 X Window 等。

笔者以 Linux 作为主要使用的操作系统，因此**如无特殊说明，本版内容中的命令均用于 Linux 系统**，其中部分应当可以用于 Unix 以及 Unix-like 等操作系统。

有一些命令是系统自带的，被称为**核心工具包 (Core utilities)**，它们是管理系统的基础和核心，应当首先掌握。而有一些命令是某些组织或用户开发的，这些命令 (或称为软件) 往往能帮助我们**更好**的使用系统，但并**不意味着都是必需的**，等到用时再了解这些前辈留下智慧往往使你惊艳，而不是陷入盲目学习各种命令的深渊，如果你觉得 GNU/Linux 世界的命令实在是太复杂而难以掌握，这或许是一个原因。

本文整理了包括但不限于 `GNU coreutils` 的工具，例如其他 GNU package，`util-linux`，甚至一些 shell built-ins。其中包括传统 Unix 的工具，也有一些基于 POSIX 标准开发的工具，但他们提供了更多的特性。这些工具 (命令) 的基本用法应该是首要掌握的，至于高级用法，慢慢 (`$ man man`) 探索就是了。

{% note info %}
想要获取完整的用法，请查看 man page 或 help，**想要获取一份按照用途分类的推荐应用清单，或许 {% button https://wiki.archlinux.org/index.php/List_of_applications, List of applications, home fa-fw fa-lg%} 对你具有重大参考价值**
{% endnote %}

# 基础
以下列出一些重要的命令，表格来源于 {% button https://wiki.archlinux.org/index.php/Core_utilities#Essentials, Essentials in Arch Linux, home fa-fw fa-lg%} 。

Package|Utility|Description|Alternatives
---|:---:|---|---
shell built-ins|cd|change directory|
GNU coreutils|ls|list directory|exa, tree
GNU coreutils|cat|concatenate files to stdout|tac
GNU coreutils|mkdir|make directory|
GNU coreutils|rm|remove files or directories
GNU coreutils|cp|copy files or directories
GNU coreutils|mv|move files or directories
GNU coreutils|ln|make hard or symbolic links
GNU coreutils|chown|change file owner and group
GNU coreutils|chmod|change file permissions
GNU coreutils|dd|convert and copy a file
GNU coreutils|df|report file system disk space usage
GNU tar|tar|tar archiver
GNU less|less|terminal pager
GNU findutils|find|search files or directories
GNU diffutils|diff|compare files line by line
GNU grep|grep|print lines matching a pattern
GNU sed|sed|stream editor
GNU gawk|awk|pattern scanning and processing language
util-linux|dmesg|print or control the kernel ring buffer
util-linux|dmesg|print or control the kernel ring buffer|systemd journal
util-linux|lsblk|list block devices
util-linux|mount|mount a filesystem
util-linux|umount|unmount a filesystem
util-linux|su|substitute user|sudo
util-linux|kill|terminate a process
procps-ng|pgrep|look up processes by name or attributes
procps-ng|ps|show information about processes
procps-ng|free|display amount of free and used memory

# 搜索 (Searching)
## 主要命令：find
find 命令的语法为
{% codeblock %}
find [-H] [-L] [-P] [-D debugopts] [-Olevel] [starting-point...] [expression]
{% endcodeblock %}
其中，前 5 个选项不太常用，具体含义可以查看手册，因此重点介绍**搜索路径集**和**搜索表达式**。
- starting-point... 用于指定**搜索的起始路径集合**。find 会依次**递归**的搜索该集合中列出的目录及其子目录。当没有给出该选项时，**默认为 `.` 即当前目录**
- expression 为搜索规则。用于指定**如何匹配文件以及匹配成功后的动作**。该部分是 find 的核心，同时也比较复杂。

搜索表达式由以下一系列部分构成
- Tests
- Actions
- Global options
- Optional options
- Operators

### 示例 Examples
命令|说明
---|---
`find -name "*.java"` | 在当前目录中查找以 java 结尾的文件

## 匹配过滤命令：grep
- [greq - man page](http://man7.org/linux/man-pages/man1/grep.1.html)

## 参数化命令：xargs
- [xargs 命令教程](https://www.ruanyifeng.com/blog/2019/08/xargs-tutorial.html)

## 其他搜索命令
### locate
locate命令其实是 `find -name` 的另一种写法，但是要比后者快得多，**原因在于它不搜索具体目录，而是搜索一个数据库（`/var/lib/locatedb`）**，这个数据库中含有本地所有文件信息。Linux 系统自动创建这个数据库，并且每天自动更新一次，所以使用 locate 命令查不到最新变动过的文件。为了避免这种情况，可以在使用 locate 之前，先使用 `updatedb` 命令，手动更新数据库。

### whereis
whereis 命令只能用于程序名的搜索，而且只搜索二进制文件（参数 -b）、man 说明文件（参数 -m）和源代码文件（参数 -s）。如果省略参数，则返回所有信息。

### which 
which 命令的作用是**在 PATH 变量指定的路径中，搜索某个系统命令的位置，并且返回第一个搜索结果**。也就是说，使用 which 命令，就可以看到某个系统命令是否存在，以及执行的到底是哪一个位置的命令。

### type
type 命令其实不能算查找命令，它是用来区分某个命令到底是由 shell 自带的，还是由 shell 外部的独立二进制文件提供的。如果一个命令是外部命令，那么使用 -p 参数，会显示该命令的路径，相当于 which 命令。

# 网络 (Networking)
## telnet
`telnet localhost 8080`

## nc
`nc -u localhost 8080`


# Directory listing
## ls - list directory contents
### 常用选项
Option | 解释
---|---
`-a, --all`| 全部显示，包括以 `.` 开头的文件和目录
  
### 文件分类 `ls -F`
- `@` means symbolic link (or that the file has extended attributes).
- `*` means executable.
- `=` means socket.
- `|` means named pipe.
- `>` means door.
- `/` means directory.

# Special File Types
## link - Make a hard link via the link syscall
{% codeblock link lang:sh %}
link filename linkname
{% endcodeblock %}
直接调用 syscall 创建硬链接，一般情况下不常用。

## ln - Make links between files
ln 用于创建链接，默认情况下创建**硬链接 (hard links)**，如果有 `-s, --symbolic` 参数，则创建**软连接 (soft links) / 符号链接 (symbolic links)**。
{% codeblock ln lang:sh %}
ln [OPTION]... [-T] TARGET LINK_NAME   (1st form) # 创建一个指向 TARGET 的名为 LINK_NAME 的文件
ln [OPTION]... TARGET                  (2nd form) # 在当前目录下创建一个指向 TARGET 的同名文件
ln [OPTION]... TARGET... DIRECTORY     (3rd form) # 在 DIRECTORY 目录下创建指向若干 TARGET... 的同名文件
ln [OPTION]... -t DIRECTORY TARGET...  (4th form) # 在 DIRECTORY 目录下创建指向若干 TARGET... 的同名文件 (同 3th form 相同)
{% endcodeblock %}

## mkdir - Make directories
mkdir 用于创建目录，默认情况下可以同时创建多个目录，安装 DIRECTORY 顺序依次创建。如果目录已存在，或者目录的父目录不存在则会报错。若要递归的创建目录，使用 `-p, --parents` 参数。
{% codeblock mkdir lang:sh %}
mkdir [OPTION]... DIRECTORY...
{% endcodeblock %}

例如：
{% codeblock lang:sh %}
mkdir dir1 dir2 dir3 # 在当前目录下创建三个子目录，目录名称依次为 dir1，dir2，dir3。
mkdir -p dir/subdir/subsubdir # 在当前目录下创建目录 dir，并依次创建其子目录 subdir，子目录的子目录 subsubdir。
{% endcodeblock %}

# 其他
- sudo fuser -k 6633/tcp

{% codeblock 更改 U 盘名称 %}
sudo umount [/dev/sda1]
sudo ntfslabel [/dev/sda1] [newname]
{% endcodeblock %}

# 参考 
- [Core utilities, ArchWiki](https://wiki.archlinux.org/index.php/Core_utilities)
- [GNU coreutils - Core GNU utilities, GNU Operating System](https://www.gnu.org/software/coreutils/manual/)
- [List of GNU Core Utilities commands, wikepedia](https://en.wikipedia.org/wiki/List_of_GNU_Core_Utilities_commands)
- [List of Unix commands, wikipedia](https://en.wikipedia.org/wiki/List_of_Unix_commands)