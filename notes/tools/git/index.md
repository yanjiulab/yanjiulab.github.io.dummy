---
title: Git
date: 2019-12-26 09:30:34
comments: false
---
本文基于 Git 官方参考书籍 {% button https://git-scm.com/book/en/v2/, Pro Git, book fa-fw fa-lg %} 总结提炼而成，详细的实例和用法请参见原书籍。本文同样也不是 Pro Git 的中文翻译，如果想阅读中文版，可以选择 {% button https://gitee.com/progit/, Pro Git 中文版, home fa-fw fa-lg %}

命令|解释
git config|配置 git 参数，包含系统级、用户级和仓库级三种等级。
git diff|显示当前工作树中还未提交的更改

# Git 简介
## 版本控制系统 (VCS)
版本控制系统大致可以分为三种：
- 本地版本控制系统 (Local Version Control Systems, LVCS)
- 集中式版本控制系统 (Centralized Version Control Systems, CVCS)
- 分布式版本控制系统 (Distributed Version Control System, DVCS)

{% tabs VCS %}
<!-- tab 本地版本控制系统 -->
![local](index/local.png)
<!-- endtab -->
<!-- tab 集中式版本控制系统 -->
![centralized](index/centralized.png)
<!-- endtab -->
<!-- tab 分布式版本控制系统 -->
![distributed](index/distributed.png)
<!-- endtab -->
{% endtabs %}

## Git 特性
- 直接记录快照，而非差异比较
- 近乎所有操作都是本地执行
- Git 保证完整性 (使用 SHA-1 hash 计算校验和)
- Git 一般只添加数据

## 工作区域和文件状态
Git 仓库的文件有三种状态：
1. 已修改 (modified) 表示修改了某个文件，但还没有提交保存；
2. 已暂存 (staged) 表示把已修改的文件放在下次提交时要保存的清单中；
3. 已提交 (committed) 表示该文件已经被安全地保存在本地数据库中了。

由此我们看到 Git 管理项目时，文件流转的三个工作区域为：
1. 工作目录 (Working Directory) 
2. 暂存区域 (Staging Area)
3. Git 仓库 (`.git` directory or Repository)

![areas](index/areas.png)

**从项目中取出某个版本的所有文件和目录，用以开始后续工作的叫做工作目录**。这些文件实际上都是从 Git 目录中的压缩对象数据库中提取出来的，工作目录也是我们可以看到的目录，接下来就可以在工作目录中对这些文件进行编辑。

**所谓的暂存区域只不过是个简单的文件**，一般都放在 .git 目录中。在 Git 中也把这个文件叫做**索引文件 (index)**。

**Git 仓库是用来保存元数据和对象数据库的地方**，该目录非常重要。每次克隆镜像仓库的时候，实际拷贝的就是这个目录里面的数据。

## Git 配置
Git 自带一个 `git config` 的工具来帮助设置控制 Git 外观和行为的配置变量。这些变量存储在三个不同的位置，因此具有三种级别，**每一个级别会覆盖上一级别的配置。**

文件|作用域|命令行参数参数
---|---|---
`/etc/gitconfig`|系统上每一个用户及他们仓库的通用配置|`git config --system`
`~/.gitconfig` 或 `~/.config/git/config`|当前用户配置|`git config --global`
`.git/config`|当前仓库配置|`git config [--local]`

当安装完 Git 应该做的第一件事就是设置你的**用户名称**与**邮件地址**。 这样做很重要，因为每一个 Git 的提交都会使用这些信息，并且它会写入到你的每一次提交中，不可更改：

{% codeblock lang:sh %}
git config --global user.name "John Doe"
git config --global user.email johndoe@example.com
{% endcodeblock %}

如果想要检查你的配置，可以使用 `git config --list` 命令来列出所有 Git 当时能找到的配置。或者通过 `git config <key>` 来检查某一项配置，如 `git config user.email`。 

{% note info %}
如果使用了 `--global` 选项，那么该命令只需要运行一次，因为之后无论你在该系统上做任何事情， Git 都会使用那些信息。**当你想针对特定项目使用不同的用户名称与邮件地址时，可以在那个项目目录下运行没有 `--global` 选项的命令来配置。**
{% endnote %}

## 获取帮助
若你使用 Git 时需要获取帮助，有三种方法可以找到 Git 命令的使用手册：

{% codeblock lang:sh %}
git help <verb>
git <verb> --help
man git-<verb>
{% endcodeblock %}

# Git 状态迁移

# 参考
- [GIT CHEAT SHEET](https://education.github.com/git-cheat-sheet-education.pdf)
- [Tutorial 19.1 - Git and version control](http://www.flutterbys.com.au/stats/tut/tut19.1.html)