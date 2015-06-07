/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50158
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50158
File Encoding         : 65001

Date: 2011-08-31 11:00:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressid` int(255) NOT NULL AUTO_INCREMENT,
  `Street` varchar(255) DEFAULT NULL,
  `Suburb` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `Zip` int(11) DEFAULT NULL,
  `State/Province` varchar(255) DEFAULT NULL,
  `Country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', 'My Street Name', 'My Street Address', 'Johannesburg', '12345', 'Gauteng', 'South Africa');
INSERT INTO `address` VALUES ('2', 'Boss Street Name', 'Boss Street Address', 'Cape Town', '12345', 'Western Cape', 'South Africa');
INSERT INTO `address` VALUES ('3', 'Guest Street Name', 'Guest Street Address', 'Bloemfontein', '12345', 'Free State', 'South Africa');

-- ----------------------------
-- Table structure for `groups`
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `groupid` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(255) DEFAULT NULL,
  `groupdesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`groupid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', 'Administrators', 'They Run Things');
INSERT INTO `groups` VALUES ('2', 'Managers', 'Paper Pushers');
INSERT INTO `groups` VALUES ('3', 'Guests', 'Another word for Noobs');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address_addressid` int(255) NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `fk_user_address1` (`address_addressid`),
  CONSTRAINT `fk_user_address1` FOREIGN KEY (`address_addressid`) REFERENCES `address` (`addressid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'greenkode', '850b86296579dae443e0f05498fc84624defd7ab8bd4e513419827051e02992b', 'Green', 'Kode', '2001-02-28', 'greenkode@greenkode.com', '1');
INSERT INTO `user` VALUES ('2', 'boss', 'a5e7c002443743c5836758c7d1cd8ddefd9fcf2061daa0efaac683fb99966057', 'Big', 'Boss', '2002-02-28', 'boss@greenkode.com', '2');
INSERT INTO `user` VALUES ('3', 'guest', '84983c60f7daadc1cb8698621f802c0d9f9a3c3c295c810748fb048115c186ec', 'Little', 'Man', '2002-02-28', 'guest@greenkode.com', '3');

-- ----------------------------
-- Table structure for `user_has_groups`
-- ----------------------------
DROP TABLE IF EXISTS `user_has_groups`;
CREATE TABLE `user_has_groups` (
  `User_userid` int(11) NOT NULL,
  `Groups_groupid` int(11) NOT NULL,
  PRIMARY KEY (`User_userid`,`Groups_groupid`),
  KEY `fk_User_has_Groups_Groups1` (`Groups_groupid`),
  KEY `fk_User_has_Groups_User1` (`User_userid`),
  CONSTRAINT `fk_User_has_Groups_User1` FOREIGN KEY (`User_userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Groups_Groups1` FOREIGN KEY (`Groups_groupid`) REFERENCES `groups` (`groupid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_has_groups
-- ----------------------------
INSERT INTO `user_has_groups` VALUES ('1', '1');
INSERT INTO `user_has_groups` VALUES ('1', '2');
INSERT INTO `user_has_groups` VALUES ('2', '2');
INSERT INTO `user_has_groups` VALUES ('3', '3');

-- ----------------------------
-- View structure for `v_user_role`
-- ----------------------------
DROP VIEW IF EXISTS `v_user_role`;
CREATE VIEW `v_user_role` AS select `user`.`username` AS `username`,`user`.`password` AS `password`,`groups`.`groupname` AS `groupname` from ((`user_has_groups` join `user` on((`user_has_groups`.`User_userid` = `user`.`userid`))) join `groups` on((`groups`.`groupid` = `user_has_groups`.`Groups_groupid`)));
