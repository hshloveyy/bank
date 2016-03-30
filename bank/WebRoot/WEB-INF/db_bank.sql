/*
MySQL Data Transfer
Source Host: localhost
Source Database: db_bank
Target Host: localhost
Target Database: db_bank
Date: 2013/2/19 21:10:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for dtproperties
-- ----------------------------
CREATE TABLE `dtproperties` (
  `id` int(11) NOT NULL,
  `objectid` int(11) default NULL,
  `property` varchar(55) NOT NULL default '',
  `value` varchar(255) default NULL,
  `uvalue` varchar(255) default NULL,
  `lvalue` longtext,
  `version` int(11) default NULL,
  PRIMARY KEY  (`id`,`property`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
CREATE TABLE `t_admin` (
  `userId` int(11) NOT NULL,
  `userName` varchar(55) default NULL,
  `userPw` varchar(55) default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_cun
-- ----------------------------
CREATE TABLE `t_cun` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  `jine` int(11) default NULL,
  `shijian` varchar(66) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_lilv
-- ----------------------------
CREATE TABLE `t_lilv` (
  `id` int(11) NOT NULL,
  `lilv` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_qu
-- ----------------------------
CREATE TABLE `t_qu` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  `jine` int(11) default NULL,
  `shijian` varchar(66) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL,
  `realname` varchar(50) default NULL,
  `sex` varchar(55) default NULL,
  `age` varchar(50) default NULL,
  `address` varchar(50) default NULL,
  `tel` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  `kahao` varchar(50) default NULL,
  `ps` varchar(66) default NULL,
  `shenfenzheng` varchar(50) default NULL,
  `yue` int(11) default NULL,
  `tai` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_zhuanzhang
-- ----------------------------
CREATE TABLE `t_zhuanzhang` (
  `id` int(11) NOT NULL,
  `fromUserId` int(11) default NULL,
  `toUserId` int(11) default NULL,
  `jine` int(11) default NULL,
  `shijian` varchar(66) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'a', 'a');
INSERT INTO `t_cun` VALUES ('1', '2', '100', '2012-5-5 14:46:19');
INSERT INTO `t_cun` VALUES ('2', '2', '100', '2012-5-5 14:51:17');
INSERT INTO `t_cun` VALUES ('3', '2', '400', '2012-5-4 15:16:31');
INSERT INTO `t_cun` VALUES ('4', '4', '100', '2013-2-19 20:18:37');
INSERT INTO `t_lilv` VALUES ('1', '0.8885');
INSERT INTO `t_qu` VALUES ('1', '2', '100', '2012-5-5 14:56:13');
INSERT INTO `t_qu` VALUES ('2', '2', '100', '2012-5-4 15:16:42');
INSERT INTO `t_qu` VALUES ('3', '4', '100', '2013-2-19 20:18:30');
INSERT INTO `t_qu` VALUES ('4', '4', '200', '2013-2-19 20:27:52');
INSERT INTO `t_user` VALUES ('2', '刘三', '男', '21', '北京路', '13888888888', 'liusan@yahoo.cn', '2012001', '000000', '371323', '300', '冻结');
INSERT INTO `t_user` VALUES ('3', '李斯', '男', '54', '上海路', '13999999999', 'lisi@yahoo.cn', '2012002', '000000', '371324', '400', '正常');
INSERT INTO `t_user` VALUES ('4', '1111', '男', '1', '11', '1', '11@qq.com', '21212121212', '21', '2121', '1921', '正常');
INSERT INTO `t_zhuanzhang` VALUES ('1', '2', '3', '100', '2012-5-5 15:08:28');
INSERT INTO `t_zhuanzhang` VALUES ('2', '2', '3', '100', '2012-5-4 15:17:02');
