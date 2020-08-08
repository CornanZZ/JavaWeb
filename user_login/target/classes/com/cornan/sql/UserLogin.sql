/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 80020
Source Host           : 47.93.8.228:3306
Source Database       : ncov

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-07-17 09:43:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for UserLogin
-- ----------------------------
DROP TABLE IF EXISTS `UserLogin`;
CREATE TABLE `UserLogin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `realname` varchar(40) DEFAULT NULL,
  `password` varchar(40) NOT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `registertime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
