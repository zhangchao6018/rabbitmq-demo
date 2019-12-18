/*
 Navicat Premium Data Transfer

 Source Server         : local_mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : rabbit_task_finish

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 18/12/2019 23:14:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for learning_course
-- ----------------------------
DROP TABLE IF EXISTS `learning_course`;
CREATE TABLE `learning_course` (
  `id` varchar(32) NOT NULL,
  `course_id` varchar(32) NOT NULL COMMENT '课程id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `charge` varchar(32) DEFAULT NULL COMMENT '收费规则',
  `price` float(8,2) DEFAULT NULL COMMENT '课程价格',
  `valid` varchar(32) DEFAULT NULL COMMENT '有效性',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL COMMENT '选课状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xc_learning_list_unique` (`course_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of learning_course
-- ----------------------------
BEGIN;
INSERT INTO `learning_course` VALUES ('8a08336b6c5b5760016c5b5808a50000', '4028e58161bcf7f40161bcf8b77c,', '49', NULL, NULL, NULL, NULL, NULL, '501001');
INSERT INTO `learning_course` VALUES ('8a08336b6c5b5760016c5b5bb3470001', '1008e58161bcf7f40161bcf8b77c,', '1', NULL, NULL, NULL, NULL, NULL, '501001');
INSERT INTO `learning_course` VALUES ('8a08336b6c610aad016c610e28360000', '9,', '2', NULL, NULL, NULL, NULL, NULL, '501001');
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
INSERT INTO `task_his` VALUES ('1', '2019-07-31 20:09:17', '2019-08-01 01:29:35', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"9,\",\"userId\":\"2\"}', 1, '10201', NULL);
INSERT INTO `task_his` VALUES ('1028858162959ce5016295b604ba', '2019-07-31 20:09:17', '2019-08-01 01:29:35', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"1008e58161bcf7f40161bcf8b77c,\",\"userId\":\"1\"}', NULL, '10201', NULL);
INSERT INTO `task_his` VALUES ('4028858162959ce5016295b604ba0000', '2019-07-31 20:09:17', '2019-08-01 01:29:35', '2019-07-31 20:09:17', 'todo_test', 'ex_my_task', 'key_todo_money', '{\"courseId\":\"4028e58161bcf7f40161bcf8b77c,\",\"userId\":\"49\"}', NULL, '10201', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
