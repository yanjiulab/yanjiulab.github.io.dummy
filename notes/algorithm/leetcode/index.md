---
title: LeetCode
date: 2019-12-26 09:57:58
comments: false
mathjax: true
---

LeetCode 的题目可以按照标签来分类，LeetCode 官方给每道题目定义了 easy/medium/hard 的难度，笔者给 tag 按照难度进行了排序，分为一星（⭐）到五星（⭐⭐⭐⭐⭐）。

刷题**不建议完全按照 Tag 来刷起**。但对于我这样的初学者按照顺序刷题有些苦恼，以下是我的策略：**循序渐进，从简单标签到困难标签，每种标签从简单题目到困难题目。**
1. 从一星难度标签开始强攻。目的是**建立信心**，巩固基础数据结构和这门语言的对这些数据结构的支持。
2. 开始两星难度标签。

# 题目总结
以下是笔者刷过的题目总结：
Tag|Rank|Link
---|---|---
Linked List|⭐|[LeetCode Linked List](/2019/10/15/leetcode-linked-list/)
Array|⭐|[LeetCode Array](/2019/11/09/leetcode-array/)
Hash Table|⭐|
Math|⭐|
String|⭐|

图、树、堆、栈、链表、哈希表、记忆搜索、动态规划、指针法、并查集
DFS + memo 322
Backtracking 22
DP (DP <--> DFS + memo) 55
BFS 286
Partition 86
Greedy 421
Sliding Window 15
Divide and Conquer
Graph 207 743
Tree
Binary Search

# 刷题模板
以下是一些刷题模板
- [Binary Search Template](/2019/11/12/algo-binary-search/)

# References
- [LeetCode按照怎样的顺序来刷题比较好？](https://www.zhihu.com/question/36738189)


# 问题
- 递归找中位数，边界条件如何选？？全闭 or 半开半闭。应该属于binary search 问题。

# 未单独成文的 Tag 
## Array

## Hash Table

## String
ID|题目|思路
---|---|---
647|回文子串|二维 DP<br>中心法
5|最长回文子串|二维 DP<br>中心法

## Math
ID|题目|思路
---|---|---

69. x 的平方根
- 二分查找 + 注意溢出
- 牛顿法

## Tree
617. 合并二叉树
437. 路径总和 III  等待复习 （前缀和，双重递归等）


## 动态规划
ID|题目|思路
---|---|---
53|最大子序和|`dp[k] = Math.max(dp[k-1], 0) + nums[k]`
121|买卖股票的最佳时机
70|爬楼梯

## 回溯法
79. 单词搜索
22. 括号生成

## 广度搜索
994. 腐烂橘子

## 其他
ID|题目|思路
---|---|---
56|合并区间|排序合并

# 问题模板

## Top-k 问题
问题描述
- 求给定数据中前 K 大的数
- 求给定数据中前 K 小的数

解决思路
1. 数据排序后（降序）取前 *k* 个元素。
2. 维护一个大小为 *k* 的堆。
3. BFPRT 算法，基于快速排序。

LeetCode 题目
- 692. 前K个高频单词


# 题解
## 树
### 二叉树镜像
{% codeblock MirrorTree lang:java https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/ %}
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;

        TreeNode tmp = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = tmp;

        return root;
    }
}
{% endcodeblock %}

### 对称的二叉树
{% codeblock isSymmetric lang:java https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/ %}
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetric(left.left, right.right) &&
               isSymmetric(left.right, right.left);
    }
}
{% endcodeblock %}


### 层序打印二叉树
{% codeblock LevelOrder lang:java https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/%}
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {result[i] = res.get(i);}
        return result;
    }
}
{% endcodeblock %}

### 层序打印二叉树 II
{% codeblock LevelOrder lang:java https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/ %}
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            result.add(new LinkedList<>());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                result.get(level).add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            level++;
        }
        return result;
    }
}
{% endcodeblock %}

### BST 的后序遍历序列
{% codeblock VerifyPostorder lang:java https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/ %}
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public boolean recur(int[] postorder, int start, int end) {
        if (start >= end) return true;

        int i = start;
        while (postorder[i] < postorder[end]) i++;
        int j = i;
        while (postorder[i] > postorder[end]) i++;

        return (i == end) &&
                recur(postorder, start, j - 1) &&
                recur(postorder, j, end - 1);
    }
}
{% endcodeblock %}

{% note info %}
如果处理二叉树的遍历序列，首先应该找到根结点，然后基于根结点把遍历序列拆分为左子树对应的子序列和右子树对应的子序列，最后递归的处理这两个子序列。
{% endnote %}


## 数组
### 顺时针打印矩阵
{% codeblock SpiralMatrix lang:java https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/ %}



{% endcodeblock %}