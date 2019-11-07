/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.27 : Database - bookmanagement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookmanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bookmanagement`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bid` varchar(32) NOT NULL COMMENT '图书编号',
  `bname` varchar(50) DEFAULT NULL COMMENT '图书名称',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `publish` varchar(50) DEFAULT '机器工业出版社' COMMENT '出版社',
  `isbn` varchar(100) DEFAULT '1111-1111-1111' COMMENT 'isbn号',
  `price` decimal(10,0) DEFAULT '100' COMMENT '定价',
  `info` varchar(5000) DEFAULT '本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。' COMMENT '内容简介',
  `cid` int(11) DEFAULT '1' COMMENT '分类编号',
  `borrow` int(11) DEFAULT '0' COMMENT '借阅次数',
  `total` int(11) DEFAULT '3' COMMENT '图书总数量',
  `state` int(4) DEFAULT '2' COMMENT '图书状态',
  `current` int(11) DEFAULT '3' COMMENT '当前数量',
  `create_time` datetime DEFAULT NULL COMMENT '入库时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`bid`),
  KEY `cid` (`cid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `book` */

LOCK TABLES `book` WRITE;

insert  into `book`(`bid`,`bname`,`author`,`publish`,`isbn`,`price`,`info`,`cid`,`borrow`,`total`,`state`,`current`,`create_time`,`update_time`) values ('1','Computer networking : a top-down approach','(美) 詹姆斯·F. 库罗斯, (美) 基思·W. 罗斯著 陈鸣译','机器工业出版社','1111-1111-1111','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',1,0,3,0,3,NULL,NULL),('10','娃哈哈','娃哈哈','数学出版社','1111-1111-1111','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',1,0,3,0,3,NULL,NULL),('12','whatever','whywhathow','机械工业出版社','1111-1111-1111','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',1,0,10,1,10,'2019-09-18 00:00:00','2019-09-18 00:00:00'),('2','计算机网络','谢希仁','电子工业出版社','1111-1111-1111','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',1,0,3,0,3,NULL,NULL),('3','计算机网络释疑与习题解答','谢希仁','电子工业出版社','978-7-121-31638-8/CNY39.80','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',1,0,3,0,3,NULL,NULL),('3a3ad8335b01415fa111e1da0e8b51c7','How to be a human','whywhathow','string','string','0','string',1,0,0,0,0,'2019-11-04 14:56:32','2019-11-04 15:14:30'),('4','算法导论','科尔曼 (Cormen, Thomas H.) 编','机器工业出版社','978-7-111-40701-0/CNY128.00','100','《算法导论(原书第3版)》编辑推荐：全球超过50万人阅读的算法圣经！算法标准教材，国内外1000余所高校采用。国内知名高校6位教授历时3年倾心翻译！',1,0,3,0,3,NULL,NULL),('5','挑战程序设计竞赛.2.算法和数据结构','(日) 渡部有隆著 支鹏浩译','人民邮电出版社','978-7-115-43161-5/CNY79.00','100','本书分为准备篇、基础篇和应用篇三大部分，借助在线评测系统Aizu Online Judge以及大量例题，详细讲解了算法与复杂度、初等和高等排序、搜索、递归和分治法、动态规划法、二叉搜索树、堆、图、计算几何学、数论等与程序设计竞赛相关的算法和数据结构，既可以作为挑战程序设计竞赛的参考书，也可以用来引导初学者系统学习算法和数据结构的基础知识。',2,0,3,0,3,NULL,NULL),('6','算法','(美)塞奇威克著','人民邮电出版社','978-7-115-27146-4/CNY99.00','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',2,0,3,0,3,NULL,NULL),('7','数学规划及其应用','范玉妹 编著','机器工业出版社','1111-1111-1111','100','书主要论述了线性规划、整数规划、非线性规划、多目标规划和动态规划等内容，并介绍了一些成功的实用实例和计算机应用过程，为便于自学，各章后面都附有习题。',1,0,3,0,3,NULL,NULL),('8','数学极客:探索数字、逻辑、计算之美','(美) 马克·C. 查－卡罗尔著 罗文俊 ... [等] 译','机器工业出版社','1111-1111-1111','100','数学是美丽的，它既有趣又令人兴奋，同时也很实用。本书探讨了两千多年的数学发展历程中一些伟大的突破和有趣的话题：从埃及分数到图灵机，从数字 的真正意义到证明树、群对称和机械化计算。如果你想知道高中几何课中难以完成的证明背后到底隐藏着什么，或者什么限制了计算机的能力，本书将会带你找到答案。 作者从数字的基础开始带你开启美丽的数学之旅，首先通过探讨一些有趣的和奇怪的数字，如整数、自然数、有理数、超越数、零、黄金比例、虚数、罗马数字、埃及分数和连分数，带你领略数字的趣味性、数字之美和数字之用，然后深入研究现代逻辑，包括线性逻辑、Prolog语言等，以及现代集合论和现代机械化计算的进展与悖论，带你感受数学的逻辑性和计算性。',1,0,3,0,3,NULL,NULL),('9','数学家大全','黄数','机器工业出版社','1111-1111-1111','100','本书是经典的计算机网络教材之一，采用了作者独创的自顶向下方法来讲授计算机网络的原理及其协议，自16年前第1版出版以来已经被数百所大学和学院选作教材，被译为14种语言。第7版保持了以前版本的特色，继续关注因特网和计算机网络的现代处理方式，注重原理和实践，为计算机网络教学提供了一种新颖和与时俱进的方法。同时，第7版进行了相当多的修订和更新，首次改变了各章的组织结构，将网络层分成两章（第4章关注网络层的“数据平面”，第5章关注网络层的“控制平面”），并将网络管理主题放入了新的第5章中。此外，为了反映自第6版以来计算机网络领域的新变化，对其他章节也进行了更新，删除了FTP和分布式散列表的材料，用流行的因特网显式拥塞通告（ECN）材料代替了ATM网络的材料，更新了有关802.11（所谓WiFi）网络和蜂窝网络（包括4G和LTE）的材料，全面修订并增加了新的课后习题，等等。',1,0,3,0,3,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `cname` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `parentId` int(11) DEFAULT '0' COMMENT '父类分类id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除该分类',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `category` */

LOCK TABLES `category` WRITE;

insert  into `category`(`cid`,`cname`,`parentId`,`create_time`,`update_time`,`is_deleted`) values (1,'计算机网路',0,NULL,NULL,0),(2,'算法',0,NULL,NULL,0),(3,'数学',0,NULL,NULL,0),(4,'英语',0,NULL,NULL,0),(5,'java',0,NULL,NULL,0),(6,'操作系统',0,NULL,NULL,0),(7,'长篇小说',0,NULL,NULL,0),(8,'计算机',0,NULL,NULL,0),(9,'编程语言',2,NULL,NULL,0),(10,'汽车',6,NULL,NULL,0),(11,'model',6,NULL,NULL,0),(12,'手表',7,NULL,NULL,0),(13,'string',0,'2019-11-04 06:39:49','2019-11-04 06:39:49',1);

UNLOCK TABLES;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `uri` varchar(100) DEFAULT NULL COMMENT '权限所在路径',
  `parentId` int(11) DEFAULT '0' COMMENT '父类权限ip',
  `type` int(11) DEFAULT NULL COMMENT 'uri类型本地or 远程',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '菜单是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menu` */

LOCK TABLES `menu` WRITE;

insert  into `menu`(`id`,`name`,`uri`,`parentId`,`type`,`is_deleted`) values (1,'用户基本信息','/main/userinfo',0,NULL,0),(2,'借阅管理','/main/assetsList',0,NULL,0),(3,'信息查询','/main/assetsInfo',0,NULL,0),(4,'更新用户信息','/main/userinfo',0,NULL,0),(5,'添加图书','/main/assetsDetail',0,NULL,0),(6,'管理图书','/main/assetsDetail',0,NULL,0),(7,'移除图书','/main/assetsDetail',0,NULL,0),(8,'个人基本信息','/main/userinfo',0,NULL,0),(9,'个人借阅信息','/main/assetsInfo',0,NULL,0),(10,'添加用户','/main/register',0,NULL,0),(11,'报表','/main/report',0,NULL,0);

UNLOCK TABLES;

/*Table structure for table `relation` */

DROP TABLE IF EXISTS `relation`;

CREATE TABLE `relation` (
  `uid` varchar(32) NOT NULL COMMENT '用户编号',
  `bid` varchar(32) NOT NULL COMMENT '图书编号',
  `borrow_time` datetime DEFAULT NULL COMMENT '借阅时间',
  `real_return` datetime DEFAULT NULL COMMENT '实际归还时间',
  `need_return` datetime DEFAULT NULL COMMENT '需求归还时间',
  PRIMARY KEY (`uid`,`bid`),
  KEY `bid` (`bid`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `relation` */

LOCK TABLES `relation` WRITE;

insert  into `relation`(`uid`,`bid`,`borrow_time`,`real_return`,`need_return`) values ('068239c5edb344d19f0c1ed2322afceb','1','2016-05-28 00:00:05','2019-11-05 22:40:42','2016-05-28 00:00:05'),('068239c5edb344d19f0c1ed2322afceb','2','2016-05-28 00:00:05','2016-05-28 00:00:00','2016-05-28 00:00:05'),('068239c5edb344d19f0c1ed2322afceb','3','2016-05-28 00:00:05',NULL,'2019-11-05 22:46:01');

UNLOCK TABLES;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rname` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '角色是否被删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

LOCK TABLES `role` WRITE;

insert  into `role`(`rid`,`rname`,`is_deleted`,`create_time`) values (1,'admin',0,NULL),(2,'normal',0,NULL),(3,'superadmin',0,NULL);

UNLOCK TABLES;

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rid` int(11) DEFAULT NULL COMMENT '角色id',
  `mid` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `mid` (`mid`),
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`),
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_menu` */

LOCK TABLES `role_menu` WRITE;

insert  into `role_menu`(`id`,`rid`,`mid`) values (18,1,8),(19,1,9),(20,1,2),(26,1,10),(27,2,8),(28,2,9),(31,2,5),(39,1,11);

UNLOCK TABLES;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL COMMENT '用户编号',
  `username` varchar(32) DEFAULT NULL COMMENT '用户登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(32) DEFAULT NULL COMMENT '用户邮箱(可以考虑加一下验证)',
  `gender` varchar(10) DEFAULT NULL COMMENT '用户性别',
  `level` int(11) DEFAULT NULL COMMENT '用户级别',
  `real_name` varchar(50) DEFAULT NULL COMMENT '用户真实姓名',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `telphone` varchar(12) DEFAULT NULL COMMENT '电话',
  `state` smallint(2) DEFAULT '0' COMMENT '用户状态,0 未激活用户,1,正常用户,2,超期用户',
  `code` varchar(64) DEFAULT NULL COMMENT '激活码',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `rid` int(11) DEFAULT NULL COMMENT '用户角色',
  `avatar_url` varchar(200) DEFAULT NULL COMMENT '用户头像链接',
  `owe` int(10) DEFAULT '0' COMMENT '用户欠款',
  PRIMARY KEY (`uid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`uid`,`username`,`password`,`email`,`gender`,`level`,`real_name`,`birthday`,`telphone`,`state`,`code`,`update_time`,`create_time`,`rid`,`avatar_url`,`owe`) values ('068239c5edb344d19f0c1ed2322afceb','string','b45cffe084dd3d20d928bee85e7bfF21',NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,100),('95951edf4f9b49b9b791778fd76723b1','st1ring','b45cffe084dd3d20d928bee85e7bfF21','string','string',0,'string','2019-11-04 05:44:05','string',0,'string','2019-11-04 05:44:05','2019-11-04 06:23:56',2,'string',0);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
