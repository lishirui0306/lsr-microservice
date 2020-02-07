/*
 Navicat Premium Data Transfer

 Source Server         : 服务器
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 106.54.114.230:3306
 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 03/02/2020 13:48:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `pid` bigint(20) NOT NULL COMMENT '主键',
  `pname` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(32) NOT NULL COMMENT '资源类型：menu,button,',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) DEFAULT NULL COMMENT '排序号',
  `available` char(1) DEFAULT '0' COMMENT '是否可用,0：可用，1不可用',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, 'select', 'button', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_permission` VALUES (2, 'insert', 'button', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_permission` VALUES (3, 'update', 'button', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_permission` VALUES (4, 'delete', 'button', NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_permission` VALUES (5, 'admin', 'button', NULL, NULL, NULL, NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `rid` varchar(36) NOT NULL,
  `rname` varchar(128) NOT NULL,
  `available` char(1) DEFAULT '0' COMMENT '是否可用,0：可用，1不可用',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('001', 'admin', '0');
INSERT INTO `sys_role` VALUES ('002', '普通用户', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `p_id` varchar(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '001', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '001', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '001', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '001', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '002', '1');
INSERT INTO `sys_role_permission` VALUES ('6', '001', '5');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` varchar(36) NOT NULL COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `usernick` varchar(32) NOT NULL COMMENT '昵称',
  `imgurl` varchar(32) DEFAULT NULL COMMENT '头像',
  `mail` varchar(32) DEFAULT NULL COMMENT '密码',
  `locked` char(1) DEFAULT '0' COMMENT '账号是否锁定，1：锁定，0未锁定',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('01', 'admin', '3f687c0e4b67d79c3d48dc7e3f7f46b2', 'Terry', NULL, 'Hacker_lsr@126.com', '0');
INSERT INTO `sys_user` VALUES ('02', 'guest', '3f687c0e4b67d79c3d48dc7e3f7f46b2', 'Test', NULL, NULL, '0');
INSERT INTO `sys_user` VALUES ('2bb7ca11-16d4-4ddf-b851-b2500bbefe46', '123123', '12\"', '12', NULL, NULL, '0');
INSERT INTO `sys_user` VALUES ('40ad29ca-5035-4d91-a389-827183f48ed4', 'lishirui', 'llll\"', '890', NULL, NULL, '0');
INSERT INTO `sys_user` VALUES ('a42ea487-a003-478c-a97f-7501ad4efbd7', '123', '111\"', 'fdsff', NULL, NULL, '0');
INSERT INTO `sys_user` VALUES ('af161c4b-baa1-468f-a674-e693ac47373b', '321321', '321312321\"', '123', NULL, NULL, '0');
INSERT INTO `sys_user` VALUES ('c96163fe-9b4a-44eb-80aa-0c58526e640f', '765', '123\"', '23', NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '01', '001');
INSERT INTO `sys_user_role` VALUES ('2', '02', '002');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
