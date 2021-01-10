---
title: VS Code
date: 2020-04-22 16:44:22
---
VS Code 是最风靡的编辑器之一，作为深度使用者，笔者主要使用 VS Code 来**阅读代码**，**写 Markdown 笔记**（本站所有内容都是在 VS Code 上写出来的），同时也用来写一些**简单的解释型语言代码（例如 Python）或者脚本**等。本文的内容偏分享向而非教学向，如果想了解更多，请参阅 [Visual Studio Code Docs](https://code.visualstudio.com/docs/)。
<!-- more -->

# 入门
VS Code 在 **Help > Keyboard Shortcut Reference** 提供了一份平台相关的 PDF 来帮助你查**找键盘快捷键**。除了常用的复制、黏贴、查找、替换等常规文本操作，以下是比较常用的基本快捷键：

快捷键|解释
---|---
`Ctrl+P`|快速打开、跳转到文件...
`Ctrl+,` |用户设置
`Alt+ ↑ / ↓` |上/下移动行（编程中很有用）
`Ctrl+/` |打开/关闭行注释
``Ctrl+`(反引号)``|显示集成终端 

## 多选择/多光标 (multi-cursor)
VS Code 支持多光标同时编辑，`Alt+Click` 即可添加一个光标，可以通过 `Ctrl+Alt+Down` or `Ctrl+Alt+Up` 同时上下添加多个光标。Esc 可以退出多光标模式。

## 块选择 (Column (box) selection)
按住 `Shift+Alt` 然后用鼠标拖拽就可以选中块区域，类似于 Vim 中的块选择效果。 

# 更改默认设置
VS Code 的默认设置已经很人性化了，基本操作都比较符合直觉。但仍然有一些笔者不适应的地方，以下记录了如何修改这些默认设置：

## 关闭预览模式
VS Code 默认是**单击预览文件，双击打开文件**，笔者习惯单击打开文件，打开新文件时候经常会不小心进入预览模式而关闭当前文件，因此在设置中将 `"workbench.editor.enablePreview": true` 改为 false。

## 打开自动保存
默认情况下，需要 `Ctrl+S` 才能将修改保存到硬盘，可以在 **File > Auto Save** 设置自动保存，默认是延迟 1 秒自动保存，在设置中可以进行更细粒度设置，包括：
- off - 关闭自动保存（默认）
- afterDelay - 经过延时配置的时间（默认 1000 毫秒）后保存
- onFocusChange - 移出编辑区域后保存（**推荐**）
- onWindowChange - 移出 VS Code 窗口后保存

## 使用 Tab 补全
默认情况下，代码片和其他建议混在一起，需要使用 `Ctrl+Space` 不太方便补全，在设置 `"editor.tabCompletion": "on"` 中开启 Tab 补全。

# 代码片 (Snippets)
VS Code 自带了简单的代码片，如果需要某种特定**编程语言**的代码补全，可以在 Extension 中下载对应语言的官方插件，一般都包含代码片。

## 基本格式
用户也可以创建自己的代码片，代码片文件是一个支持 C 风格注释的 JSON 文件，例如笔者创建了 Markdown 的代码片，用于快速输入一些控制格式的部分，可以专注于内容的编写。

以下是一个 JS 语言的 for 循环代码片：
{% codeblock lang:json %}
// in file 'Code/User/snippets/javascript.json'
{
  "For Loop": {
    "prefix": ["for", "for-const"],
    "body": ["for (const ${2:element} of ${1:array}) {", "\t$0", "}"],
    "description": "A for loop."
  }
}
{% endcodeblock %}

每个代码片使用名字作为键，其值包含：
- prefix 前缀：使用该代码片的输入前缀。
- body 主体：主要部分。
- description 描述：可选。

代码片主体部分可以是简单的固定文字，也可以包含占位符，其格式为：`${跳转顺序:默认值}`，通过 Tab 进行跳转。其中第一个跳转的占位符为从 1 开始，`$0` 总为最后一个跳转占位符。

## 作用范围
用户可以通过 **File > Preferences - User Snippets** 来创建或编辑代码片，默认的**用户级代码片 (User Scope Snippet)** 存储位置为 `Code\User\snippets\`, 用户级代码片又可以分为：
- 依据语言类型分类的特定语言代码片
- 适用于所有语言的全局代码片

除此之外，还可以创建**项目代码片 (Project Scope Snippet)**，更便于随着项目移植，位于项目根目录下的 `.vscode` 文件夹中。项目代码片类似于用户级的全局代码片，其适用于所有语言，可以通过 scope 属性来限制语言范围。

