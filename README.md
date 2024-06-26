# 言谈智友

本项目是中南林业科技大学2021级信息与计算科学2023《软件工程》课程设计“言谈智友”小组的代码仓库。主要用于记录小组成员在课程设计过程中的代码、文档和其他相关资料。

本项目部分资源来源于自网络，如主界面的背景来自于Pixiv的画师[ovu総桜しわす](https://www.pixiv.net/artworks/109348979)，关于图片也来自于Pixiv画师[なーが其のもの](https://www.pixiv.net/artworks/84694532)

## 目录

- [项目简介](#项目简介)
- [目录](#目录)
- [项目结构](#项目结构)
- [小组成员](#小组成员)
- [开发环境](#开发环境)
- [如何运行](#如何运行)
- [使用须知](#使用须知)
- [使用说明](#使用说明)
- [联系我们](#联系我们)

## 项目结构

```plaintext
.
├── .idea/            # IDEA生成
├── lib/              # 需要的lib
├── src/Main/         # 存放源代码
│   ├── java/         # 源码
│   └── resource/     # 资源
├── target/classes/   # 调试生成
├── doc               # 文档
├── README.md         # 项目说明文件
├── LICENSE           # 许可证
├── pom.xml           # Maven依赖项等
└── .gitignore        # Git 忽略文件配置

```

## 小组成员

* **可行性与项目规划:**
  王行杭、席祥、唐正阳
* **设计:**
  覃志帆、刘骥潇
* **需求分析:**
  朱恒坚、赵无忌
* **编码:**
  张越、许鹏、张力
* **测试:**
  姚雨果、喻博文

## 开发环境
* Java11
* Maven4.0.0

## 软件截图

- 登陆界面

![登陆界面](https://github.com/HeTongRe4per/imgurl/blob/main/Wise_Conversations/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202024-06-26%20153902.png?raw=true)

- 主界面

![主界面](https://github.com/HeTongRe4per/imgurl/blob/main/Wise_Conversations/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202024-06-26%20153452.png?raw=true)

- 设置界面

![设置界面](https://github.com/HeTongRe4per/imgurl/blob/main/Wise_Conversations/cb95448797112cbc1286e38b36dba634.png?raw=true)

## 如何运行

**（本项目不再提供 `.jar`，故此部分已过期，编译运行可以参考）**

以下提示是给编译运行的，如果你使用 `.exe`可以看[使用说明](#使用说明)。当然，你如果想直接使用的话可以直接下载Releases下的exe文件（使用exe4j打包），或者下载Releases下的jar文件然后运行 `java -jar 言谈智友.jar`来运行。

1. 克隆项目到本地

   ```bash
   git clone https://github.com/HeTongRe4per/Software_Engineering.git
   ```
2. 安装JDK11
3. 安装Maven
4. 安装IntelliJ IDEA
5. 打开项目，使用Maven导入项目
6. 运行main方法

## 使用须知

1. 本软件使用GPLv3协议开源，详情请看[这里](https://github.com/HeTongRe4per/Software_Engineering/blob/master/LICENSE)。
2. 请勿将本软件用于非法用途，否则后果自负。
3. 请勿将本软件用于商业用途，否则后果自负。

## 使用说明

以下说明是给 `.exe`的软件运行说明，但是源码编译和 `.jar`可以参照运行，`.jar`的使用[在此](#如何运行)：

**开始之前：**

1. 你需要Java环境来运行本软件，支持的Java版本有Java11到Java17，如果你没有，可以自行安装或者使用zip的环境打包版本
2. 你需要一个可以连接互联网的环境，启动后就可以看到登录界面了

**进入软件：**

1. 你需要拥有账号才可以进入主界面，没有账号可以点击最右下角的按钮来创建账号
2. 登录后进入主界面，最上面有菜单栏，里面有许多选项，中间是输出框，你的问题和对应的回答都会在这里显示
3. 下面是输入框和发送按钮，你可以在输入框输入你的问题然后点击发送按钮，等待一会儿然后就可以看到对应的回答了。发送也可以通过Ctrl+Enter来快捷发送

**菜单栏提示**

1. 账号栏：

    * 账号管理：你可以通过它管理用户名和、邮箱和密码，也可以删除账号
    * 退出登录：退出登录，回到登录界面
2. 工具栏：

    * 重开对话：你可以通过它来重新开始对话
    * 设置：你可以在这里设置一些软件的设置，比如设置服务器可ApiKey等，还可以在这里更改主题和字体等（注：更改主题需要重启软件）

**帮助**

1. 帮助只有关于这一项，你可以通过关于来查看软件的说明。里面可以前往此项目的[Github仓库](https://github.com/HeTongRe4per/Software_Engineering)，也可以[联系我们](mailto:zhang_zlf@outlook.com)。


## 联系我们

如果你在使用软件时遇到了问题，或者你有什么好的建议，欢迎提交[#issue](https://github.com/HeTongRe4per/Software_Engineering/issues)或者通过邮件联系我们：[zhang_zlf@outlook.com](mailto:zhang_zlf@outlook.com)
