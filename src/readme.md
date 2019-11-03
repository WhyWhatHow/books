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
### 查找图书相关信息

# 普通用户
### 用户个人信息管理
#### 修改密码
#### 修改个人信息
#### 查询借阅记录(历史借阅情况)
#### 当前借阅情况
#### 用户借书
#### 用户还书
#### 提交欠款
#### 登录
#### 注册

#管理员界面
### 新增图书
### 下架图书