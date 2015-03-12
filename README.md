利用github的版本控制来团队开发程序<p>
1.	访问github 官网 https://github.com/  注册账号<p>
2.	打开eclipse 安装github插件org.eclipse.egit.repository-3.7.0.201502260915-r.zip<p>
a)	help->install new software<p>
b)	Add -> archive<p>
c)	选择org.eclipse.egit.repository-3.7.0.201502260915-r.zip 文件<p>
d)	Ok  然后一路安装 当提醒开源申明时 点acceptall 插件安装完毕<p>
3.	导入工程<p>
a)	再工程视图里右击 选择import –>选择git->clone uri<p>
b)	输入项目地址 https://github.com/AUGxhub/Park.git 你的github账户 密码<p>
c)	选择要同步的版本 我们目前只有一个主要版本 就是master
然后下一步<p>
d)	选择一个保存文件的位置 （不是eclipse 的工作空间）<p>
e)	下一步后 久开始下载程序源文件  等一会 一路next<p>
f)	添加项目 直接finish<p>
4.	CVS使用说明<p>
a)	每次打开工程后先使用 fetch from upstream ，有提示更新则跟新，以确保自己的工程和服务器上的是一样的，<p>
b)	 然后就是正常的编写啦。。。。。<p>
c)	当对某个文件进行改写 添加之后 对应的文件前面会多个 ’>‘<p>
d)	 写完之后 退出eclipse之前要使用commit 把自己改写的部分同步到服务器<p>
e)	在commit message 填写这次同步改写 或者添加的功能 修改bug 等信息 然后commit<p>
f)	 同步完成后工程中所有的文件 和文件夹前面都没有’>’表示同步完成  你改写或者添加的部分就上传到服务器上啦。。（大功告成 ）<p>
g)	也可以在https://github.com/AUGxhub/Park.git网页上 查看更新 工程进度 <p>





