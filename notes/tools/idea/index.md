---
title: IntelliJ IDEA
date: 2020-01-22 16:39:32
comments: false
---
[IntelliJ IDEA](https://www.jetbrains.com/help/idea/installation-guide.html) 是一个跨 Windows，Linux，macOS 三大平台的集成开发环境，主要用于 Java 开发。其中，Intelli 表示 Intelligent，J 表示 Java，而 IDEA 具有两重含义，表示 IDE Advanced 或者仅仅就是 idea (我有一个好主意)。通常我们可以读作 IntelliJ[ɪn'telɪdʒeɪ]、IDEA[I-D-E-A] 或者 idea[aɪ'diə]。

# 安装 (Installing)
IDEA 的安装极其简单以及人性化，按照 [Install IntelliJ IDEA](https://www.jetbrains.com/help/idea/installation-guide.html) 的步骤即可完成，三大平台的安装都比较容易，主要注意选择版本即可，社区版 (Community Edition) 和 旗舰版 (Ultimate Edition) 是有区别的，前者开源免费，后者功能更多但却收费，不过在校师生可以使用教育邮箱注册高校计划，可以免费使用。

# 起步 (Getting Started)
IDEA 功能强大，但却足够易用，基本不需要进行额外的学习便可以简单上手开发，其中官网的以下两部分内容推荐阅读。
- [Create your first Java application](https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html) 快速的了解一个完整的开发流程，主要包括创建工程、编写代码、构建及运行程序、程序打包、运行 Jar 包等基本流程。
- [Mastering IntelliJ IDEA keyboard shortcuts](https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html) 根据操作系统平台可以查询常用的快捷键。

# IDE 配置 (Configuring the IDE)
[IDEA 配置](https://www.jetbrains.com/help/idea/configuring-project-and-ide-settings.html)设置项 (Setting) 和 工程结构 (Structure) 具有两种等级：
- Project-level settings and structure 工程/项目级
- IDE-level (global) settings and structure IDE/全局级

## Project-level settings and structure
工程级配置仅针对当前工程，其设置和结构同其他工程文件一同存储于 `.idea` 这个文件夹中。

工程级配置方式如下：
- Access the **project-level settings**: From the main menu, select **File | Settings** for Windows and Linux, or **IntelliJ IDEA | Preferences** for macOS.
- Access the **project-level structure**: From the main menu, select **File | Project Structure**.

## IDE-level (global) settings and structure
针对全局工程 (所有新创建的工程)，也可通过插件将配置同步给已存在的工程项目。

全局配置方式如下：
- Access the **IDE-level settings**: From the main menu, select **File | Other Settings | Settings** for New Projects for Windows and Linux, or **File | Other Settings | Preferences** for New Projects for macOS.
- Access the **IDE-level structure**: From the main menu, select **File | Other Settings | Structure** for New Projects.

# 工程配置 (Configuring Projects)
[工程配置](https://www.jetbrains.com/help/idea/working-with-projects.html)

## Projects
type, SDK, name, location

Project formats:
- file-based 
- directory-based (recommended, default)

## Modules
- Projects with multiple modules

## SDKs

## Libraries
A library is a collection of **compiled code** that a module can depend on. In IntelliJ IDEA, libraries can be defined at three levels:
- global (available for many projects) 
- project (available for all modules within a project)
- module (available for one module)

A Java library can include **class files**, **archives** and **directories with class files** as well as **directories with Java native libraries** .dll, .so or .jnilib.

Define a **global** library:
1. From the main menu, select **File | Project Structure**
2. Under the **Platform Settings**, select **Global Libraries**.

Define a **project** library:
1. From the main menu, select **File | Project Structure**
2. Under **Project Settings**, select **Libraries**.

Define a module library:
1. From the main menu, select **File | Project Structure**
2. Under **Project Settings**, select **Modules | Dependencies**.
3. Click the Add button and select **Library**.

{% note info %}
https://stackoverflow.com/questions/12269733/what-is-the-difference-between-module-dependencies-and-libraries-in-intellij
{% endnote %}

# 快捷键 (Shortcut Key)
以下记录了笔者常用的快捷键

Shortcut|Action
---|---
Ctrl+Alt+L|格式化代码
Alt+Insert|快速生成方法
Ctrl+O|快速生成重写方法
Ctrl+Shift+↑|
Ctrl+Shift+↓|

# 参考 (References)
- [What does the A stand for in Intellij IDEA?
](https://stackoverflow.com/questions/22026104/what-does-the-a-stand-for-in-intellij-idea)