/*
 Navicat Premium Data Transfer

 Source Server         : local_mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : rabbit_task

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 18/12/2019 23:14:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` varchar(32) NOT NULL COMMENT '任务id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `task_type` varchar(32) DEFAULT NULL COMMENT '任务类型',
  `mq_exchange` varchar(64) DEFAULT NULL COMMENT '交换机名称',
  `mq_routingkey` varchar(64) DEFAULT NULL COMMENT 'routingkey',
  `request_body` varchar(512) DEFAULT NULL COMMENT '任务请求的内容',
  `version` int(10) DEFAULT NULL COMMENT '乐观锁版本号',
  `status` varchar(32) DEFAULT NULL COMMENT '任务状态',
  `errormsg` varchar(512) DEFAULT NULL COMMENT '任务错误信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES ('4028858162959ce5016295b604ba1111', '2019-07-31 20:09:17', '2019-08-04 01:29:35', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"4028e58161bcf7f40161bcf8b77c,\",\"userId\":\"8888\"}', NULL, '10201', NULL);
COMMIT;

-- ----------------------------
-- Table structure for task_his
-- ----------------------------
DROP TABLE IF EXISTS `task_his`;
CREATE TABLE `task_his` (
  `id` varchar(32) NOT NULL COMMENT '任务id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `task_type` varchar(32) DEFAULT NULL COMMENT '任务类型',
  `mq_exchange` varchar(64) DEFAULT NULL COMMENT '交换机名称',
  `mq_routingkey` varchar(64) DEFAULT NULL COMMENT 'routingkey',
  `request_body` varchar(512) DEFAULT NULL COMMENT '任务请求的内容',
  `version` int(10) DEFAULT '0' COMMENT '乐观锁版本号',
  `status` varchar(32) DEFAULT NULL COMMENT '任务状态',
  `errormsg` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_his
-- ----------------------------
BEGIN;
INSERT INTO `task_his` VALUES ('1', '2019-07-31 20:09:17', '2019-08-05 04:12:55', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"9,\",\"userId\":\"2\"}', 2, '10201', NULL);
INSERT INTO `task_his` VALUES ('1028858162959ce5016295b604ba', '2019-07-31 20:09:17', '2019-08-04 01:40:02', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"1008e58161bcf7f40161bcf8b77c,\",\"userId\":\"1\"}', NULL, '10201', NULL);
INSERT INTO `task_his` VALUES ('4028858162959ce5016295b604ba0000', '2019-07-31 20:09:17', '2019-08-04 01:36:27', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"4028e58161bcf7f40161bcf8b77c,\",\"userId\":\"49\"}', NULL, '10201', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
