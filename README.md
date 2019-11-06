# Landlords pokercard game

## Release 2 Notes

如果你发现了Bug值得Report，就到[Our Bug Report](http://cs3343.cs.cityu.edu.hk/bugzilla/enter_bug.cgi?product=Bro_Doudizhu)登记一下吧！最后的Documentation要用到！

Release 2 的目标主要是：

1.  重构目前的代码。
2.  添加Robot，可能分为三种等级的“智商”。比如，
    -   只出手中最大的牌
    -   只出对手中牌的Total Score减少最少的牌
    -   记牌高手？？
3.  画Class Diagram
4.  准备各种Documentation

### TODO: Release 2

-   [ ] 【重要！】画Class Diagram
-   [ ] 异常输入通过Exception处理
-   [ ] 重构Hand.java
-   [ ] 重构main()
-   [ ] 添加Robot
-   [ ] 游戏中输入exmaple打印示例input，输入suggest打印suggestion（当前输入的help功能）
-   [ ] 【探讨】关于出牌suggestion（即托管），目前即使有也不会推荐炸弹，是否要推荐？
-   [ ] ……etc 等着大家来加进去

## Bug Notes

-   [ ] PASS输出会输出空卡 => 要判断lastCardList新家的是不是空

## TODO [Midterm stage form]

-   [x] 完整地玩一遍直到程序结束
-   [ ] 检查Release 1还缺什么元素，如果OK，就发布Release 1（Merge 到 master）
-   [ ] ~~Class Diagram Version 1 (需要在目前的设计上考虑各种Principles和Patterns，为Release 2做指导)~~
-   [x] 抢地主Refactor以缩减代码
-   [x] （Idea）Messenger Refactor为通过Message Token得到message信息
-   [ ] Rank.alias如果不考虑‘10‘的’x‘'X'和A的‘1’，可以考虑去掉alias并将Helper.isValidInputCardNames()的判断通过toUpperCase来实现
-   [x] 将所有Ask for input的部分改写为用Helper.getCheckInput()并通过toUpperCase()来预处理Input
-   [x] PASS输出会输出空卡，要判断lastCardList新家的是不是空

## Collaboration Guide

ave two main branches in the git repository:
- master: for stable version of code
- dev: for development version of code

You can follow the following:
1. Fork this repository to your own account.
2. Clone this repository to your local machine.
3. Import project from your local repo (you should import Landlords/Landlords as your project folder)
4. Create a new feature branch (for example, `poker`) for your own development. You can do it in Eclipse.
6. You can edit code and do several commits on your own branch (for example, `poker`).
7. When it's time to push your modification to the Github, you can checkout to your local `dev` branch and merge/rebase from your own branch (for example, `poker`). Then, fetch the latest remote branches `master` & `dev` from upstream, that is, the original repo, to avoid your modification conflicting with others' code already push to the `remote/dev` or `remote/master` (you can find it in Eclipse. You can follow the session 4 of  [Github团队项目合作流程](https://www.cnblogs.com/schaepher/p/4933873.html#fetch)). Then, you can push the local `dev` branch to your fork repo and make a pull request from your `dev` branch to the `dev` branch of the original repository. This procedure is trying to make a clearer git commit history.

Note: 
- if you would like to get the latest development version of code, please checkout to `dev` branch. Branch `master` may not be the newest.
- before you push, remember to fetch from the upstream first.

Recommanded softwares:
- Github Desktop
- SourceTree # You can visualize the git actions.

**Highly Recommanded Readings:**
- [廖雪峰：多人协作](https://www.liaoxuefeng.com/wiki/896043488029600/900375748016320)
- [廖雪峰：解决冲突](https://www.liaoxuefeng.com/wiki/896043488029600/900004111093344)
- [wiki: dou dizhu](https://en.wikipedia.org/wiki/Dou_dizhu)
- [编程语言中常用的变量命名缩写](https://blog.csdn.net/qq_37851620/article/details/94731227)
- [upstream](https://www.cnblogs.com/schaepher/p/4933873.html#fetch)
