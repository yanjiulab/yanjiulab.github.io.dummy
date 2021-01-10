---
title: vim
date: 2020-01-22 16:45:12
---
从零开始，搭建一份属于你自己的 Vim，然后享受 coding 的乐趣。

# 自定义配置 (Customization)

## 轻量级配置
仅仅利用 vim 自身支持的配置项完成，不使用任何插件，不需要任何依赖，适合所有的环境使用，旨在于使用最快的速度拥有一个基本的编程环境。

{% codeblock .vimrc %}
" vim basic
set nocompatible
set showmode
set showcmd
set mouse=a
set encoding=utf-8
set t_Co=256

" display
set number          " 显示行号
" set relativenumber  " 显示相对行号
set cursorline      " 高亮当前光标所在行
set textwidth=80    " 显示行宽
set wrap            " 自动折行
set wrapmargin=2    " 折行边缘宽度
set linebreak       " 单词内不折行
set scrolloff=5     
set ruler
set matchtime=1
set laststatus=2

" enable filetype detection:
filetype on
filetype plugin on
filetype indent on  " file type based indentation

" indent
set autoindent      " 按下回车键后，下一行的缩进会自动跟上一行的缩进保持一致。
set smartindent     " 智能缩进
set tabstop=4       " 按下 Tab 键时，Vim 显示的空格数。
set softtabstop=4   " 按退格键时退回缩进的长度
set shiftwidth=4    " 按下缩进字符数
autocmd FileType make set noexpandtab shiftwidth=8 softtabstop=0

" searching
set showmatch
set hlsearch
set incsearch
set ignorecase
set smartcase

" edit
autocmd FileType markdown,tex set spell spelllang=en,cjk
set wildmenu
set wildmode=longest:list,full
set autoread
set nobackup
set nowb
set noswapfile

" coding
syntax on

" custom map
map j gj
map k gk
inoremap jj <Esc>
{% endcodeblock %}

# 参考
- [VimAwesome](https://vimawesome.com/)
- [how-do-i-run-a-c-program-from-vim](https://stackoverflow.com/questions/2627886/how-do-i-run-a-c-program-from-vim)