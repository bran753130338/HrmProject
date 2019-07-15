/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : wuyi

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 15/07/2019 20:37:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anno_inf
-- ----------------------------
DROP TABLE IF EXISTS `anno_inf`;
CREATE TABLE `anno_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of anno_inf
-- ----------------------------
INSERT INTO `anno_inf` VALUES (1, '放假通知', '明天放假666666666', '2019-07-01 23:33:42');
INSERT INTO `anno_inf` VALUES (4, '没事', '123', '2019-07-05 22:30:17');
INSERT INTO `anno_inf` VALUES (5, '1', '1', '2019-07-05 22:45:28');
INSERT INTO `anno_inf` VALUES (6, '1', '12', '2019-07-05 22:45:31');
INSERT INTO `anno_inf` VALUES (8, '1', '12', '2019-07-05 22:45:34');
INSERT INTO `anno_inf` VALUES (7, '1', '12', '2019-07-05 22:45:33');
INSERT INTO `anno_inf` VALUES (9, '1', '12', '2019-07-05 22:45:35');
INSERT INTO `anno_inf` VALUES (10, '1', '12', '2019-07-05 22:45:37');
INSERT INTO `anno_inf` VALUES (14, 'asd', '123', '2019-07-11 14:08:50');

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES (1, '财务部', '无1233');
INSERT INTO `dept_inf` VALUES (2, '学术部', '无');
INSERT INTO `dept_inf` VALUES (3, '公关部', '无123');
INSERT INTO `dept_inf` VALUES (4, '学习部', '123');

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CARD_ID` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ADDRESS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `POST_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TEL` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PHONE` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QQ_NUM` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SEX` int(11) NOT NULL DEFAULT 1,
  `PARTY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BIRTHDAY` date NULL DEFAULT NULL,
  `RACE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EDUCATION` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SPECIALITY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `HOBBY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_DATE` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_EMP_DEPT`(`DEPT_ID`) USING BTREE,
  INDEX `FK_EMP_JOB`(`JOB_ID`) USING BTREE,
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES (2, 2, 1, '小明', '123456789012345678', '广东省深圳', '234242', '23424333', '13610561160', '324234', '54555@qq.cn', 1, '群众', '1980-07-09', '汉族', '本科', '计算机', '无123', '无1', '2019-02-19 15:32:44');
INSERT INTO `employee_inf` VALUES (3, 1, 1, '旺财', '123456789012345670', '广东省深圳', '23424', '23424333', '13610561161', '324234', 'dsafasfd@dsa.com', 1, '群众', '1980-07-07', '汉', '本科', '计算机', '无', '无', '2019-02-19 15:37:20');
INSERT INTO `employee_inf` VALUES (4, 1, 1, '张帅', '8745896', '广州', '524178', '8804521', '13800138000', '1458747854', 'example@qq.com', 1, '群众', '2019-06-11', '汉族', '本科', '信息安全', '看电影', '无', '2019-06-03 00:00:00');
INSERT INTO `employee_inf` VALUES (5, 1, 1, '雷神', '74747474', '雷州', '524874', '10086', '18214565441', '887452491', '123@qq.com', 1, '党员', '2019-05-09', '维吾尔族', '博士', '制药工程', '无', '无', '2019-06-27 00:00:00');
INSERT INTO `employee_inf` VALUES (6, 3, 1, '蚁人', '74749587', '广东省东莞', '524541', '10086', '17885451478', '745141741', 'example@gmail.com', 1, '群众', '2002-08-27', '汉族', '本科', '电子信息工程', '无', '无', '2019-06-27 23:22:07');
INSERT INTO `employee_inf` VALUES (7, 3, 3, '萧薰儿', '74749587', '广东省东莞', '524541', '10086', '1828547485', '854712365', 'example@gmail.com', 2, '共青团员', '1994-08-27', '汉', '本科', '电子信息工程', '无', '无', '2019-06-27 23:31:38');
INSERT INTO `employee_inf` VALUES (9, 3, 2, '卡卡南', '784578445', '广州大学城', '524547', '18214787497', '18214787497', '882417474', '45321@qq.com', 1, '党员', '1998-05-07', '汉', '高中', '电子信息工程', '无', '无', '2019-06-29 00:36:43');
INSERT INTO `employee_inf` VALUES (10, 1, 1, '卡卡', '784', '广州', '', '10254', '17802014578', '', '123@qq.com', 1, '群众', '2019-06-29', '汉族', '本科', '电子信息工程', '', '', '2019-06-29 23:19:12');
INSERT INTO `employee_inf` VALUES (13, 2, 1, '海贼王', '851146212', '大航海', '', '123456', '123456', '123', 'haizeiwan@qq.com', 1, '群众', '2019-06-01', '汉族', '本科', '超新星', '吃肉', '', '2019-06-29 23:58:49');
INSERT INTO `employee_inf` VALUES (14, 1, 1, '卡卡罗特', '123434', '`22', '', '123', '123', '', '435@gmail.com', 1, '群众', '2019-07-09', '苗族', '初中', '电子信息工程', '', '', '2019-07-05 20:24:39');

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES (1, '工程师', '无1');
INSERT INTO `job_inf` VALUES (2, '会计师', '无');
INSERT INTO `job_inf` VALUES (3, '普通职员', '无');
INSERT INTO `job_inf` VALUES (4, '智能制造学部', '12323');
INSERT INTO `job_inf` VALUES (5, '预定号', '123');
INSERT INTO `job_inf` VALUES (6, '预定', '');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT 1,
  `createdate` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (1, 'mike', '99462', 1, '2019-05-08 11:19:49', '乔峰1');
INSERT INTO `user_inf` VALUES (2, 'zhang', '999', 0, '2019-04-27 17:01:05', '段誉');
INSERT INTO `user_inf` VALUES (3, 'jackk', '999', 1, '2019-05-09 11:19:59', '王语嫣');
INSERT INTO `user_inf` VALUES (4, 'jason', '000000', 1, '2019-05-09 11:19:55', '扫地僧');
INSERT INTO `user_inf` VALUES (5, 'lishi', '000000', 1, '2018-12-05 21:20:22', '李四');
INSERT INTO `user_inf` VALUES (6, 'aaa', '111', 0, '2019-04-27 17:13:15', '东方不败');
INSERT INTO `user_inf` VALUES (7, 'jack', '111', 0, '2019-04-28 11:35:14', '令狐冲');
INSERT INTO `user_inf` VALUES (8, 'test', '123', 1, '2019-05-06 14:21:53', '哈哈');
INSERT INTO `user_inf` VALUES (10, 'test1', '111', 1, '2019-05-25 08:47:21', '慕容复');
INSERT INTO `user_inf` VALUES (12, 'test3', '123', 1, '2019-05-25 10:29:33', '李四');
INSERT INTO `user_inf` VALUES (13, 'admin', '123456', 0, '2019-06-15 13:09:53', 'along');
INSERT INTO `user_inf` VALUES (15, 'serser', '123456789', 2, '2019-06-27 00:31:40', '张设计院');
INSERT INTO `user_inf` VALUES (16, 'loginname', '123456789', 2, '2019-06-27 23:32:20', '萧炎');
INSERT INTO `user_inf` VALUES (17, 'lokik', '123456', 0, '2019-07-05 20:38:23', '卡卡罗特');
INSERT INTO `user_inf` VALUES (18, 'iii', '666666', 0, '2019-07-06 10:28:09', '1231');
INSERT INTO `user_inf` VALUES (19, 'aaaaaa', '123456', 0, '2019-07-06 10:55:13', 'wde');
INSERT INTO `user_inf` VALUES (20, 'aaa', '123456', 0, '2019-07-11 14:02:22', 'asd');
INSERT INTO `user_inf` VALUES (21, 'aab', '123456', 0, '2019-07-11 14:05:15', '淑怡');

SET FOREIGN_KEY_CHECKS = 1;
