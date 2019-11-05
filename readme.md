图书的三中状态： 未订购 0 ，可借阅 1 , 保留本(不可借阅) 或者已删除 2 

用户借书状态: 
未借阅图书 , 正在借阅图书, 图书超期

|fieldtype|type|comment|
|:---|:---:|---:|
| bid | int(11) |NOT NULL | 图书编号|
|bname | varchar(50) NOT NULL | 图书名称
|author|varchar(50) NOT NULL|作者
|publish|varchar(50) NULL|出版社
isbn|varchar(100) NULL|isbn号
price|decimal(10,0) NULL|定价
info|varchar(5000) NULL|内容简介
cid|int(11) NULL|分类编号
view|int(11) NULL|浏览次数
borrow|int(11) NULL|借阅次数
total|int(11) NOT NULL|图书总数量
is_deleted|tinyint(1) NULL|是否删除
current|int(11) NOT NULL|当前数量

# visitor:
 
-[ ]   查找图书相关信息

# 普通用户

- [ ] 用户提交欠款
> 约定:超期一天,罚款一元/一角
 
 - [x]   用户个人信息管理 # 
 - [x]   修改密码 # 
 - [x]   修改个人信息 #
 - []   查询借阅记录(历史借阅情况)
 - [ ]   当前借阅情况
 - [ ]   用户借书
 - [ ]   用户还书
 - [ ]   提交欠款
 - [x]   登录 done
 - [x]   注册 done

#管理员界面
 - []   新增图书
 - []   下架图书
 - [x]   管理分类信息(使用redis，都忘干净了，zz)
 - [x]   新增分类  done 
 - [x]   删除分类  done 
 - [x]   修改分类  done


 
 
          