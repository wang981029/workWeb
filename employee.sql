/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : workdb

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2023-08-05 16:41:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `dept_name` varchar(20) DEFAULT NULL,
  `emp_degree_name` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `deleted` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1687741542565892098 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '张一', '男', '25', '业务部', '本科', '54cd1476ed740f4113ee95b4466784e2', '87418', '0');
INSERT INTO `employee` VALUES ('2', '张二', '女', '26', '人事部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('3', '张三', '男', '27', '后勤部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('4', '张四', '女', '28', '人事部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('5', '张五', '男', '29', '后勤部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('6', '张六', '女', '29', '后勤部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('7', '张七', '男', '33', '业务部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('8', '张八', '男', '32', '业务部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('9', '张九', '女', '33', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('10', '李一', '女', '45', '业务部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('11', '李二', '女', '19', '人事部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('12', '李三', '男', '28', '业务部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('13', '李四', '女', '46', '后勤部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('14', '李五', '男', '58', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('15', '李六', '女', '22', '人事部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('16', '李七', '男', '26', '后勤部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('17', '李八', '男', '25', '人事部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('18', '李九', '女', '29', '后勤部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('19', '王一', '男', '45', '后勤部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('20', '王二', '女', '21', '业务部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('21', '王三', '男', '21', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('22', '王四', '男', '23', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('23', '王五', '女', '33', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('24', '王六', '男', '45', '人事部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('25', '王七', '男', '35', '业务部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('26', '王八', '男', '41', '后勤部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('27', '王九', '女', '25', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('28', '赵一', '男', '26', '人事部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('29', '赵二', '男', '20', '后勤部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('30', '赵三', '女', '21', '人事部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('31', '赵四', '男', '19', '后勤部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('32', '赵五', '女', '35', '后勤部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('33', '赵六', '男', '24', '业务部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('34', '赵七', '男', '29', '业务部', '大专', null, null, '0');
INSERT INTO `employee` VALUES ('35', '赵八', '女', '33', '业务部', '研究生', null, null, '0');
INSERT INTO `employee` VALUES ('36', '赵九', '男', '45', '业务部', '本科', null, null, '0');
INSERT INTO `employee` VALUES ('1687741542565892097', 'admin', '男', '12', '人事部', '本科', 'a0d5038f649fa10153b2fc8fc28a6074', '0phrc', '0');
