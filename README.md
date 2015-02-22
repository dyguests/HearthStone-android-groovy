#关于

Hearth Stone,HearthStone-android-groovy,炉石传说,炉石界面/框架/逻辑/动画等学习用项目.

编程语言: groovy + java

测试环境: 小米3(5寸屏,1080*1920)

#进度表

1.  20150117

    新建炉石安卓学习项目

2.  20150121

    完成部分界面框架,卡牌框架.

3.  20150130

    view,model,及一部分view-model的数据绑定

#残留作业

1.  随从牌视图不能实现 嘲讽/圣盾/风怒/被沉默,即死/亡语/触发等外观

2.  之后把 heroView等从layout改成view,各元素在onDraw中实现

#笔记

如果有哪里写错了的话,希望能告诉我

套牌

游戏界面组成

| english     |中文  |
| --------    | --- |
|library      |牌库  |
|deck         |套牌  |
|cemetery     |坟场  |
|Opponent     |对手  |


卡牌类型

| english     |中文  |
| --------    | --- |
|minion       |随从  |
|spell        |法术  |
|weapon       |装备  |

etc..


随从属性

| english     |中文  |
| --------    | --- |
|battlecry    |战吼  |
|charge       |冲锋  |
|chooseOne    |快择  |
|deathrattle  |亡语  |
|divineShleld |圣盾  |
|enrage       |激怒  |
|taunt        |嘲讽  |
|windfury     |风怒  |
|FXIME???     |过载  |

参考:[demo.vaadin.com](demo.vaadin.com)

| english     |中文  |
| --------    | --- |
|start    |开始游戏  |
|change 1,2,3      |实始状态的换牌操作(换第1,2,3张牌  |
|xxxx    |xxx  |


命令行模式


#游戏流程

	1.开始游戏

	2.换牌

	3.回合制

		3.1.回合开始
	
		3.2.摸牌
	
		3.3.使用卡牌/技能等
	
			3.3.1.略

		3.2.回合结束

	4.结束游戏


#游戏规则

1.假如对方法师挂了一个复制对方召唤随从的奥秘,我召唤随从时:先触发随从的战吼,再触发奥秘.



#Question

1.  怎么实现出牌的动画效果啊?

    1.1 打出随从牌的动画效果

    1.2 打出法术牌的动画效果