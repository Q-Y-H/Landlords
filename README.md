# Landlords pokercard game

## 记得记Bug Report！

## Methods that need to be tested

-   [ ] Helper.checkValidCards()
-   [ ] Hand.cards2hand()
-   [ ] Hand.compareTo()
-   [ ] Player.checkCardOnHand()
-   [ ] Player.playCards()

## To-Do

-   [ ] 符号输入是否会破坏目前的输入判断？如多个空格 换行符 制表符
-   [ ] 每次输入前清空输入流？
-   [ ] 输入Alias直接转换大小写？
-   [ ] cursor => Room.getNextPlayer(Player p)
-   [ ] 检查抢地主是否有逻辑错误
-   [ ] 检查Main.java是否有逻辑错误
-   [ ] **Principles** & **Pattern**
-   [ ] Rank: Alias for ‘A’ and ‘a’, etc.
-   [ ] Player: id是否有用？
-   [ ] Messager: 不同Players之间的过场动画
-   [x] Messager: **Card 10** display problem
-   [ ] Player: checkCardsOnHand有错 need to be fixed
-   [ ] 完成Main.js里`TODO`代码
-   [ ] 完成各处auto-generated的method

请求大家帮忙更新README！

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
