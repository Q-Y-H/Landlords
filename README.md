

# Landlord

Dou Di Zhu game in the terminal verison.

## Catalogue

- [Submission Folder Structure](#submission-folder-structure)
- [Timeline](#timeline)
  * [11.14 (Thur)](#1114--thur-)
  * [11.21 (Thur)](#1121--thur-)
  * [11.27 (Wed)](#1127--wed-)
  * [11.29 (Fri)](#1129--fri-)
- [Nov.10 Pre-discussion notes](#nov10-pre-discussion-notes)
- [Release 2 Notes](#release-2-notes)
  * [TODO: Release 2](#todo--release-2)
- [Bug Notes](#bug-notes)
- [TODO [Midterm stage form]](#todo--midterm-stage-form-)
- [Collaboration Guide](#collaboration-guide)

## Submission Folder Structure

```
- Group09_Project
	- Docs
		- Presentation.pptx
		- Bug Report.docx
		- Analysis and Design Report.docx
		- Project Plan.docx
		- Release Summary.docx
		- Self Assessment Report.docx
		- Class Diagram
		- Sequence Diagram
		- *User Manual
		- *Installation Guide
		- *Javadoc
	- Release
		- LandlordGame.jar
	- Source
		- landlord
		- test
	- Readme.txt
	- ActivityLog.xlsx
```

## Timeline

### 11.14 (Thur)

-   [x] Robot v1.0
-   [x] Refactor the whole project following the **Class Diagram**

### 11.21 (Thur)

-   [ ] Release 2
    -   [ ] Class Diagram
    -   [ ] Sequence Diagram
    -   [ ] Robot v2.0
    -   [ ] Refactor log & 记录下来！！
    -   [ ] Bug Report v1.0
    -   [ ] Test case大爆肝 ==> 提高Coverage到80%以上
    -   [ ] `Player.getPlayChoice()`直接让Robot获取previousCards，不用改参数
    -   [ ] `HumanPlayer.getPlayChoice().suggest` => Helper.getLeastHand()
    -   [ ] `HumanPlayer.getPlayChoice().suggest` => Messenger.getHelpInfo()

### 11.27 (Wed)

-   [ ] Slides准备完好

### 11.28 (Thu)

**讨论**：

-   Messenger应该是用Singleton还是静态类？Singleton怎么保证线程安全？

### 11.29 (Fri)

-   [ ] Presentation 14:00-14:30 20min + 5min Q&A

## Nov.10 Pre-discussion notes

-   采用EventListener的机制轮换Player（这可以用什么pattern？）— 事件处理机制
-   Testing架构？ Bottom Up？
-   [Class Diagram](https://www.lucidchart.com/invitations/accept/dc3f6473-78cc-4aad-8414-632aee8b41dc)

## Release 2 Notes

如果你发现了Bug值得Report，就到[Our Bug Report](http://cs3343.cs.cityu.edu.hk/bugzilla/enter_bug.cgi?product=Bro_Doudizhu)登记一下吧！最后的Documentation要用到！

Release 2 的目标主要是：

1.  重构目前的代码。
2.  添加Robot，可能分为三种等级的“智商”。比如，
    -   [dumb]只出手中最大的牌
    -   [xyf&hgm]只出对手中牌的Total Score减少最少的牌
    -   [先知]记牌高手
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
-   [ ] notifyAll,rememberCardList, strategy for different role
-   [ ] Functions重构的改写
    -   [ ] Messenger.printAskForInput() => askForInput() 改写所有Occurence。参考LandlordGame。
    -   [ ] Player Constructor
    -   [ ] Command Pattern
    -   [ ] Command Pattern好像不怎么行的通 没办法获取回传数据，未来应该还要改
    -   [ ] storeAndPrint
    -   [ ] 正则表达式检查input
    -   [ ] 计时功能
    -   [ ] History要用Card还是Hand（没想清楚）
    -   [ ] 改写Singleton的Messenger
        -   [ ] Method都不用static
-   [ ] ……etc 等着大家来加进去

## Bug Notes

-   [x] PASS输出会输出空卡 => 要判断lastCardList新家的是不是空
-   [ ] Enter需要输入两次（hgm制造）

## TODO [Midterm stage form]

-   [x] 完整地玩一遍直到程序结束
-   [x] 检查Release 1还缺什么元素，如果OK，就发布Release 1（Merge 到 master）
-   [ ] ~~Class Diagram Version 1 (需要在目前的设计上考虑各种Principles和Patterns，为Release 2做指导)~~
-   [x] 抢地主Refactor以缩减代码
-   [x] （Idea）Messenger Refactor为通过Message Token得到message信息
-   [ ] Rank.alias如果不考虑‘10‘的’x‘'X'和A的‘1’，可以考虑去掉alias并将Helper.isValidInputCardNames()的判断通过toUpperCase来实现
-   [x] 将所有Ask for input的部分改写为用Helper.getCheckInput()并通过toUpperCase()来预处理Input
-   [x] PASS输出会输出空卡，要判断lastCardList新家的是不是空

## Collaboration Guide

We have two main branches in the git repository:

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
