---
title: Python
date: 2020-06-04 01:05:30
comments: false
---
{% centerquote %}
人生苦短，我用 Python！
Life is short, you need Python！
{% endcenterquote %}

本文集长期更新，记录 Python 学习路上的点点滴滴。更多内容可以查看英文笔记 [learn-python3](https://github.com/liyanjiu/learn-python3) 。
<!-- more -->
# 起步 (Getting Started)
## 挑选 Python 解释器 (3 vs. 2)
Python 是一种**解释型**语言，由于历史的演进，出现了许多方面不兼容或重新设计的 2 和 3 两个大版本，目前 Python 的现状 (3 & 2) 如下：
- Python 2.7 已于 2020 年停止更新，不再维护。
- 仍有相当一部分应用是用 Python 2.x 写成。
- 目前 Python 3.x 是官方的推荐版本。

所以推荐如下：
- ~~新软件编写时使用 Python 3，但最好做到 2 和 3 兼容，因为有些人仍然使用 2。如果写新模块，用 Python 3 就好了。~~
- 无论是写软件还是模块，应当使用 Python 3！要不要兼容 2 取决于你的精力和心情。

Python 语言有多种实现方式，官方使用的是 C 语言实现的版本。

Names|Language|Speed|Popular|Aim
---|---|---|---|---
CPython|C|fast|reference|Reach the widest possible audience
PyPy|Python|5 times faster than CPython||Maximum compatibility, performance
Jython|Java|||Be able to import and use any Java class like a Python module
IronPython|.NET|||For the .NET framework and Window developer

## 安装 Python 
首先，基于你的系统安装 Python 。参考如下：
- [官方文档](https://www.python.org/downloads/)
- [Properly Installing Python - The Hitchhiker's Guide to Python, by Kenneth Reitz](https://docs.python-guide.org/starting/installation/)

## 开发环境
开发环境基于**操作系统类型，主要包括是否使用IDE，使用何种编辑器**。这部分根据个人喜好配置就好了。笔者的配置如下，推荐 PyCharm IDE：

- IDE: [PyCharm](https://www.jetbrains.com/pycharm/) (推荐，适合写中大型项目)    
- Text editor：VS Code/Vim (配合终端适合写简单脚本)
- Notebook：[Jupyter Notebook](http://jupyter.org/)

## 包管理器 (Package manager)
这里的**包 (package)** 指的是软件的一个发行版 (distribution)，而不是在源码中导入的包 (container of modules)，通俗的理解来说，包管理就是管理那些你下载的别人或官方用 Python 编写的软件。

### pip
pip 是官方推荐使用的包管理器，关于如何下载和使用 pip 的详细说明，请移步官方教程 [Installing Packages](https://packaging.python.org/tutorials/installing-packages/)。当然，现在默认情况下，pip 是随着 Python 的安装而默认安装的，因此基本上不需要自行下载了。

可以通过 `pip --version` 查看 pip 版本，以及是否和使用的 Python 的版本相同。这是因为使用官方的安装脚本安装 pip 时会探测系统上 Python 的版本，具体来说，`pip2` 代表管理 Python 2 的包，`pip3` 或 `pip3.6` 之类的代表管理 Python 3 的包，而 `pip` 代表什么取决于你的系统上的主要的 Python 版本。

{% codeblock %}
$ python --version
Python 3.8.2

$ pip --version
pip 20.1.1 from /usr/local/lib/python3.8/dist-packages/pip (python 3.8)
{% endcodeblock %}

#### 安装模式
安装好 pip 之后，可以直接在命令行运行 `pip` 来查看使用说明。需要特别说明的是安装包有**全局模式**和**用户模式**的区别：
{% codeblock %}
pip install --user SomeProject
{% endcodeblock %}

以上的命令会将包安装到用户存放二进制文件的目录中，**不加参数的默认行为是全局安装**。
- 在 Linux 或 macOS 中，执行 `$ python -m site --user-base` 来查看用户的目录，一般是 `～/.local/`，最好将该路径加入 `PATH` 中，否则每次安装包时 pip 会报警告。
- 在 Windows 中，执行 `py -m site --user-site` 查看用户目录，一般是 `C:\Users\Username\AppData\Roaming\Python36\site-packages`，最好将 `C:\Users\Username\AppData\Roaming\Python36\Scripts` 加入环境变量 `PATH` 中。

还有一些其他的用法，具体查看上述官网的教程以及 `pip --help`。

#### 更换镜像
使用 pip 安装太慢？不如换国内镜像源试试。笔者习惯于清华的 TUNA 镜像站，5 min 同步一次。从 pip 10 开始可以使用 config 子命令可用来改配置，因此我们无需关心不同操作系统下配置文件路径。只需如下一条命令即可：
{% codeblock %}
$ pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple
Writing to C:\Users\%USER%\AppData\Roaming\pip\pip.ini  # Windows
Writing to $HOME/.config/pip/pip.conf                   # Linux
{% endcodeblock %}

#### Pip 多版本冲突
Pip 多版本冲突问题主要存在于 Linux 环境中，首先是发行版可能预装了 Python 2 或 Python 3，以 Ubuntu 为例，即使没有预装，用户一般使用 apt 包管理器安装 Python，这就导致了 Python 其实是由操作系统管理的，因此操作需要管理员权限。

如果通过 pip 的官方脚本 get-pip.py 安装，需要特别注意 get-pip.py 与这些包管理器工具不协调（脚本默认安装到用户目录），并且可能使我们的系统处于不一致状态，这种情况可以使用 `python get-pip.py --prefix=/usr/local` 来安装到系统目录。

另一种解决方法是直接使用包管理器来安装 pip。
{% codeblock %}
sudo apt install python-pip     # for pip
sudo apt install python3-pip    # for pip3
{% endcodeblock %}

{% note info %}
由于 Python 2 已经停止更新，从 Ubuntu 20.04 之后将不再支持 python-pip，随着 Python 2 使用的减少，或许冲突问题将会减少。
{% endnote %}

如果系统中确实存在多个 pip，尽量使用 `python -m pip [command]`。这指明了将 pip 模块作为 `python` 命令环境下的脚本来运行，也就是根据 Python 的版本和权限选择对应的 pip，这将自动为我们解决一些冲突问题，如：
- pip 多版本共存问题，如 pip2 以及 pip3。这时将自动选择适合 Python 版本的 pip。
- pip 多权限版本共存问题，如系统目录下的 pip 以及用户目录下的 pip。

{% note warning %}
没有特别需求的话，一种 Python 版本不应当在多个目录下存在 pip 模块。好的做法是：
1. 仅使用系统目录（系统权限）下的 pip。换句话说，Python 和 Pip 的权限应当保持一致，由于大多数情况下 Python 是系统管理的，因此 Pip 也安装的系统目录下。
2. 使用 `--user` 选项将库安装在用户目录下（这将不会让你提供 root 权限）。

因此，当升级 pip 时，**请确保执行 `python -m pip install --upgrade pip` 命令的 shell 具有系统权限**。否则将升级失败。**不要**使用 `python -m pip install --user --upgrade pip` 来升级你的 pip，这将会导致在用户目录下重新下载一个 pip，从而引发 pip 的冲突问题，使得 `pip xxx` 命令报错。假如你这么做了，仍然可以使用 `python -m pip xxx` 来执行命令，原因如上，不过多个 pip 并不是一种优雅的方式。
{% endnote %}

### conda
除了 pip，还有其他的包管理工具，如 conda，随着 Anaconda 的安装而安装的，Anaconda 是一个针对于科学计算人员的软件发布包，其内置了 conda 这样的包管理器 (考虑到 conda 实现了下文中接着提到的虚拟环境管理器的功能，叫做包管理器可能不太恰当)，**许多科学计算的库 (通过 pip 逐一安装它们可是要费不少劲的)**，编辑器，IDE 以及 Jupiter 等工具。

{% note info %}
由于 anaconda 的跨平台的特性，在国内有清华的镜像站，且还可以和 pip 协同工作，十分适合使用 Python 做机器学习，科学计算等需求的初学者，可以一键式打造开发环境。如果嫌它太臃肿，试试 miniconda？
{% endnote %}

## 虚拟环境
包管理器为我们解决了将**依赖**从网络上下载到本地这个过程，然而却不能保证任何本地项目的调用是否会成功。

当使用 Python 编写项目时，可能通过 `import module` (可以先不理解) 引入他人编写的模块，这样你就可以愉快的使用 `module.func()` 来调用别人写好的函数，这些模块就叫做当前项目的依赖 (dependencies)，用着是爽了，但会带来一个问题，如果项目 A 使用的是 module 版本 1.3，而项目 B 使用的是 module 版本 2.1，可是系统上安装的 module 只有一个版本，这下可麻烦了，不同的项目有不同的依赖，难免会出错。其他编程语言同样也存在类似的问题，并且具有类似的解决方案。
- 例如 Java 可以使用 Maven 来构建工程，通过在项目目录下包含一个 `pom.xml` 文件来管理当前的依赖包。
- 例如 nodejs 使用 npm 来构建项目，通过在项目目录下包含一个 `package.json` 文件来管理当前的依赖包。

Python 使用了一种称为**虚拟环境**的技术来实现这个需求。简单的讲，虚拟环境就是把所有运行 Python 文件的环境（包括解释器、库等）打包到一个单独的路径里面，然后用该路径来替换本机的全局环境，只不过替换不是通过手动更改环境变量等一系列设置，而是由工具自动完成。为每个项目创建一个虚拟环境，并安装不同版本的模块，这样每个项目可以调用自己的依赖版本库而互不影响。官方教程在这里：[Managing Application Dependencies](https://packaging.python.org/tutorials/managing-dependencies/)

在 pipenv 之前一般通过 **venv** (Python 3 官方库) 或 **virtualenv** (第三方工具但比 venv 更早，也更受欢迎) 来管理虚拟环境，但多少有些麻烦，在 Kenneth Reitz 将自己的个人项目 pipenv 转交给 PyPA以后，**pipenv 成为了官方推荐的虚拟环境管理工具**。pipenv 是对 pip、virtualenv 等工具的进一步封装，结果就是我们可以像管理 Java 和 JavaScript 项目那样顺畅丝滑的管理 Python 的虚拟环境。pipenv 会在当前项目路径下创建 pipfile 文件，记录所有的依赖关系。这类似于 Java 的 Maven 生成的 pom.xml，node.js 的 npm 生成的 package.json。

{% note info %}
其实还有一个管理 Python 版本的工具 pyenv 可以使用，目的是为了管理系统上不同版本的 Python，但我个人很少使用，因为每个项目的虚拟环境可以通过 pipenv 来管理，系统上的 Python 并没有太多管理的需求。如果个人有这方面的需求，不妨可以尝试下 pyenv。
{% endnote %}

### Pipenv
pipenv 的使用十分简单，首先通过 pip 安装 pipenv：
{% codeblock %}
pip install --user pipenv
{% endcodeblock %}

接着，进入工程目录下，安装 requests 库：
{% codeblock %}
mkdir myproject && cd myproject
pipenv install requests
{% endcodeblock %}

最后，使用 requests 库，创建一个 `main.py` 的文件，并写下以下代码，通过 Python 解释器运行这个文件，你会得到你当前的 IP 地址。
{% codeblock %}
import requests
response = requests.get('https://httpbin.org/ip')
print('Your IP is {0}'.format(response.json()['origin']))
{% endcodeblock %}

如果在终端中使用 `python main.py` 来运行脚本，需要输入 `pipenv shell` 激活这个虚拟环境，否则不会使用虚拟环境，导致报错。当激活完虚拟环境之后，你会发现你的 Python 路径现在已经是虚拟环境里的 Python 解释器了。如果想推出这个环境，只需 `exit` 即可。更多的使用请见 `pipenv --help`。

## Python 相关组织
### Python Software Foundation
{% blockquote Mission Statement page %}
The mission of the Python Software Foundation is to promote, protect, and advance the Python programming language, and to support and facilitate the growth of a diverse and international community of Python programmers.
{% endblockquote %}

Python 软件基金会的使命是促进和保护 Python 编程语言的发展，致力于形成一个多样化和国际化的 Python 社区。其维护了 [官方网站](https://www.python.org) ，包括：
- Python Language：Python 语言
- Python Documentation：Python 语言和官方库的文档
- Python Community：Python 的社区
- PyPI (Python Package Index)：Python 的官方第三方库索引
- PyPA (Python Packaging Authority)：负责维护 Python 包管理的相关工具

其中，Python 官方文档是笔者认为目前最好的**初学者学习资源**，没有之一。

### PEPs (标准提案)
PEP是 Python Enhancement Proposals 的缩写，就是改进 Python 建议书。每个 PEP 文件可能属于：
- 信息类 (Information PEP)，如 Python 的编程规范等；
- 流程类 (Process PEP)，如 Python 的工具，仓库迁移等；
- 标准类 (Standards Track PEP)，包括新功能和新的实现等。

PEPs 可以当做设计文档来读，包含了Python 这门语言许多有价值的的技术规范和发展历程，在[PEP 0（Index of Python Enhancement Proposals (PEPs)）](https://www.python.org/dev/peps/)里，官方列举了所有的PEP，你可以按序号、按类型以及按状态进行检索。当然 PEPs 很多，全部通读很花费时间，**但其中有一些相当重要的 PEP 还是应该读一读的，它们包括但不限于**：
- [PEP 8 -- Style Guide for Python Code](https://www.python.org/dev/peps/pep-0008/)
- [PEP 257 -- Docstring Conventions](https://www.python.org/dev/peps/pep-0257/)
- [PEP 20 -- The Zen of Python](https://www.python.org/dev/peps/pep-0020/) (彩蛋)
- [PEP 333/3333 -- Python Web Server Gateway Interface v1.0/v1.0.1](https://www.python.org/dev/peps/pep-0333/)

在知乎上有一个问答 [python 的众多PEP 之中，除了PEP8 ，还有哪一些是值得阅读的？](https://www.zhihu.com/question/23484654)，里面有更多详细的回答。

## Python 学习资源
主要包括笔者读过的**好书**和遇到的有价值的资源（包括博客，第三方库等），这部分随时更新，用英文写的是英文资源，用中文写的是中文资源。

- The Hitchhiker's Guide to Python, by Kenneth Reitz
- ...

# 基础 (Basics)
假设读者有其他编程语言的基础，那么理解 Python 代码将是非常容易的。

## 缩进和注释
Python 的语法比较简单，采用**缩进方式**。当语句以冒号:结尾时，缩进的语句视为代码块。缩进有利有弊。好处是强迫你写出格式化的代码，但没有规定缩进是几个空格还是 Tab。按照约定俗成的惯例，**应该始终坚持使用 4 个空格的缩进**。

以 # 开头的语句是注释，注释是给人看的，可以是任意内容，解释器会忽略掉注释。

## 变量
在 Python中，等号 `=` 是赋值语句，可以把任意数据类型赋值给变量，同一个变量可以反复赋值，而且可以是不同类型的变量，这种变量本身类型不固定的语言称之为**动态语言**，与之对应的是静态语言。静态语言在定义变量时必须指定变量类型，如果赋值的时候类型不匹配，就会报错。

在 Python 中，通常用全部大写的变量名表示常量。但 Python 没有任何机制保证常量不被修改。

## 数据类型
在 Python 中，能够直接处理的数据类型有以下几种：
- 整数：Python 可以处理任意大小的整数，当然包括负整数。
- 浮点数
- 字符串：字符串是以单引号 `'` 或双引号 `"` 括起来的任意文本。
- 布尔值：布尔值和布尔代数的表示完全一致，一个布尔值只有 `True`、`False` 两种值。
- 空值是：Python 里一个特殊的值，用None表示。

### 字符串和编码
Python 3 的字符串使用 Unicode，直接支持多语言。

## 数据结构
除了基本的数据类型，Python 内置了常用的数据结构，包括：
- list 列表
- tuple 元组
- dict 字典
- set 集合

### list & tuple
Python 内置的一种数据类型是列表：list。list 是一种有序的集合，可以随时添加和删除其中的元素。

另一种有序列表叫元组：tuple。tuple 和 list 非常类似，但是 tuple 一旦初始化就不能修改。

### dict & set
Python 内置了字典：dict的支持，dict 全称 dictionary，在其他语言中也称为 map，使用键-值（key-value）存储，具有极快的查找速度。

set 和 dict 类似，也是一组 key 的集合，但不存储 value。由于 key 不能重复，所以在 set 中没有重复的 key。

## 控制流
控制流与 C 十分相似，包括：
- if
- for...in...
- while
- break
- continue


## 函数

## 模块和包

# 构建和分发 (Building and Distributing)
Python 有非常丰富的第三方库可以使用，很多开发者会向 pypi 上提交自己的 Python 包。要想向 pypi 包仓库提交自己开发的包，首先要将自己的代码打包，才能上传分发。

`distutils` 是标准库中负责建立 Python 第三方库的安装器，使用它能够进行 Python 模块的安装和发布。`distutils` 对于简单的分发很有用，但功能缺少。大部分 Python 用户会使用更先进的 `setuptools` 模块

`setuptools` 是 Python `distutils` 增强的集合，允许开发人员更轻松地构建和分发 Python 软件包，尤其是依赖于其他软件包的软件包。具体参阅 [Building and Distributing Packages with Setuptools](https://setuptools.readthedocs.io/en/latest/setuptools.html#building-and-distributing-packages-with-setuptools)。

## setup.py 文件
### 最小安装脚本
以下是一个最小的 setup 安装脚本，只需要将脚本放到项目目录下，与包目录同级。
{% codeblock minimal lang:py %}
from setuptools import setup, find_packages
setup(
    name="HelloWorld",
    version="0.1",
    packages=find_packages(),
)
{% endcodeblock %}

### 更多信息
当然，在将项目发布到PyPI之前，您需要向设置脚本中添加更多信息，以帮助人们找到或了解您的项目。
到那时，也许您的项目将会发展壮大，以包括一些依赖项，以及一些数据文件和脚本：

{% codeblock more information lang:py %}
from setuptools import setup, find_packages

try:
    f = open("requirements.txt", "rb")
    REQUIRES = [i.strip() for i in f.read().decode("utf-8").split("\n")]
except:
    print("'requirements.txt' not found!")
    REQUIRES = list()

setup(
    name="HelloWorld",
    version="0.1",
    packages=find_packages(),
    scripts=["say_hello.py"],

    # Project uses reStructuredText, so ensure that the docutils get
    # installed or upgraded on the target machine
    install_requires=["docutils>=0.3"],

    package_data={
        # If any package contains *.txt or *.rst files, include them:
        "": ["*.txt", "*.rst"],
        # And include any *.msg files found in the "hello" package, too:
        "hello": ["*.msg"],
    },

    # metadata to display on PyPI
    author="Me",
    author_email="me@example.com",
    description="This is an Example Package",
    keywords="hello world example examples",
    url="http://example.com/HelloWorld/",   # project home page, if any
    project_urls={
        "Bug Tracker": "https://bugs.example.com/HelloWorld/",
        "Documentation": "https://docs.example.com/HelloWorld/",
        "Source Code": "https://code.example.com/HelloWorld/",
    },
    classifiers=[
        "License :: OSI Approved :: Python Software Foundation License"
    ]

    # could also include long_description, download_url, etc.
)
{% endcodeblock %}

### 复杂项目
复杂项目通常会添加更丰富的信息，例如一个使用 Github 托管并发布在 PyPI 的项目可以按照如下脚本编写，脚本来自于 [MacHu-GWU/Python-with-GitHub-PyPI-and-Readthedoc-Guide](https://github.com/MacHu-GWU/Python-with-GitHub-PyPI-and-Readthedoc-Guide/blob/master/setup.py)

{% codeblock lang:py %}
from __future__ import print_function
from setuptools import setup, find_packages
from datetime import date
import os

try:
    f = open("requirements.txt", "rb")
    REQUIRES = [i.strip() for i in f.read().decode("utf-8").split("\n")]
except:
    print("'requirements.txt' not found!")
    REQUIRES = list()

...

setup(
    name=NAME,
    description=SHORT_DESCRIPTION,
    long_description=LONG_DESCRIPTION,
    version=VERSION,
    author=AUTHOR,
    author_email=AUTHOR_EMAIL,
    maintainer=MAINTAINER,
    maintainer_email=MAINTAINER_EMAIL,
    packages=PACKAGES,
    include_package_data=INCLUDE_PACKAGE_DATA,
    package_data=PACKAGE_DATA,
    url=URL,
    download_url=DOWNLOAD_URL,
    classifiers=CLASSIFIERS,
    platforms=PLATFORMS,
    license=LICENSE,
    install_requires=REQUIRES,
)
{% endcodeblock %}

## setup.py 命令
setup.py 文件有很多内置命令可供使用，查看所有支持的命令：
{% codeblock %}
usage: setup.py [global_opts] cmd1 [cmd1_opts] [cmd2 [cmd2_opts] ...]
   or: setup.py --help [cmd1 cmd2 ...]
   or: setup.py --help-commands
   or: setup.py cmd --help
{% endcodeblock %}

其中比较常用的有：
命令|解释
---|---
build         |build everything needed to install
clean         |clean up temporary files from 'build' command
install       |install everything from build directory
sdist         |create a source distribution (tarball, zip file, etc.)
register      |register the distribution with the Python package index
bdist         |create a built (binary) distribution
bdist_dumb    |create a "dumb" built distribution
bdist_rpm     |create an RPM distribution
bdist_wininst |create an executable installer for MS Windows
upload        |upload binary package to PyPI
check         |perform some checks on the package

### 安装 install
软件包的安装也十分简单
{% codeblock lang:sh %}
cd <project-directory>
python setup.py install    # 手动安装
python -m pip install .    # 使用 pip 进行安装
{% endcodeblock %}

必须注意的是，手动安装的话会分散到各个目录中，因此在软件包卸载时无法定位，因此需要到各个目录下手动删除安装的文件。因此**推荐使用 pip 进行安装，卸载时比较方便**。强迫症用户可以通过以下脚本重新安装并记录，然后再卸载。
{% codeblock lang:sh %}
python setup.py install --record files.txt
cat files.txt | xargs rm -rf
{% endcodeblock %}



## 错误处理

## IO

# 高级特性
## 函数式编程

# 面向对象编程
## 

# 并发
## 进程
## 线程

# Python 标准库
Python 之所以自称 “batteries included”，就是因为内置了许多非常有用的模块，无需额外安装和配置，即可直接使用。

# 参考
- [What is the difference between dict.items() and dict.iteritems()?](https://stackoverflow.com/questions/10458437/what-is-the-difference-between-dict-items-and-dict-iteritems)


