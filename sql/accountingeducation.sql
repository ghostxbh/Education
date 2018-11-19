/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : accountingeducation

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-11-19 17:40:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for e_category
-- ----------------------------
DROP TABLE IF EXISTS `e_category`;
CREATE TABLE `e_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='视频分类表';

-- ----------------------------
-- Records of e_category
-- ----------------------------
INSERT INTO `e_category` VALUES ('34', '会计考试');
INSERT INTO `e_category` VALUES ('35', '会计实操');
INSERT INTO `e_category` VALUES ('36', '税务实操');

-- ----------------------------
-- Table structure for e_course
-- ----------------------------
DROP TABLE IF EXISTS `e_course`;
CREATE TABLE `e_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `course_name` varchar(64) DEFAULT NULL COMMENT '课程名称',
  `course_info` varchar(1024) DEFAULT NULL COMMENT '课程祥情/简介',
  `photo_url` varchar(256) DEFAULT NULL COMMENT '图片url',
  `thum_photo_url` varchar(256) DEFAULT NULL COMMENT '封面缩略图url',
  `lecturer` varchar(16) DEFAULT NULL COMMENT '讲师',
  `lecturer_portrait_url` varchar(128) DEFAULT NULL COMMENT '讲师头像url',
  `period` varchar(16) DEFAULT NULL COMMENT '课程周期 ',
  `price` double DEFAULT NULL COMMENT '课程单价',
  `free` char(1) DEFAULT NULL COMMENT '是否免费（1：是 0：否）',
  `status` char(1) DEFAULT NULL COMMENT '上架/下架 （1：上架   0：已下架）',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销售量',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(8) DEFAULT NULL COMMENT '创建人',
  `category_id` int(11) DEFAULT NULL COMMENT '分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='课程表';

-- ----------------------------
-- Records of e_course
-- ----------------------------
INSERT INTO `e_course` VALUES ('11', '注会CPA《会计》投资性房地产', '1、对教材每个章节进行全面系统讲解。 2、权威解析考试大纲、聚焦核心内容、总结高频考点。 3、案例解读理论、习题深化知识、结合生活实际学习。 4、资深名师授课，化繁为简，通俗易懂。 5、难点反复听、错题重复练、核心点多角度学。', 'http://collegeimage.uzykj.com/9d5d6e18d25946c3846262f57d22e5e2_20181119.jpg', 'http://collegeimage.uzykj.com/d940de9173344f8d89540f605aaf0df5_20181119.png', '程老师', 'http://collegeimage.uzykj.com/54d6a36fa1c74e838d15e2b1cc09d8db_20181119.jpg', '6', '28', '0', '1', '0', '2018-11-19 12:17:56', '管理员', '34');

-- ----------------------------
-- Table structure for e_invoice
-- ----------------------------
DROP TABLE IF EXISTS `e_invoice`;
CREATE TABLE `e_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '发票抬头',
  `code` char(20) NOT NULL COMMENT '纳税识别号',
  `address` varchar(255) DEFAULT NULL COMMENT '发票地址',
  `number` varchar(13) DEFAULT NULL COMMENT '发票电话',
  `bank` varchar(80) DEFAULT NULL COMMENT '开户银行',
  `card` char(20) DEFAULT NULL COMMENT '开户账号',
  `mail` varchar(80) DEFAULT NULL COMMENT '收票人邮箱',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发票表';

-- ----------------------------
-- Records of e_invoice
-- ----------------------------

-- ----------------------------
-- Table structure for e_order
-- ----------------------------
DROP TABLE IF EXISTS `e_order`;
CREATE TABLE `e_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '订单号',
  `receiver` varchar(10) DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `pay_way` char(1) DEFAULT NULL COMMENT '支付方式：1、支付宝；2、微信',
  `status` char(1) DEFAULT NULL COMMENT '订单状态：0、未付；1、已付；',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of e_order
-- ----------------------------

-- ----------------------------
-- Table structure for e_role
-- ----------------------------
DROP TABLE IF EXISTS `e_role`;
CREATE TABLE `e_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role` char(1) NOT NULL COMMENT '角色：1、会员；2、管理员',
  `user_code` varchar(16) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of e_role
-- ----------------------------

-- ----------------------------
-- Table structure for e_section
-- ----------------------------
DROP TABLE IF EXISTS `e_section`;
CREATE TABLE `e_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `section_name` varchar(64) DEFAULT NULL COMMENT '章节名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_section
-- ----------------------------
INSERT INTO `e_section` VALUES ('71', '11', '投资性房地产的特征与范围');
INSERT INTO `e_section` VALUES ('72', '11', '投资性房地产的确认和初始计量');
INSERT INTO `e_section` VALUES ('73', '11', '投资性房地产的后续计量');
INSERT INTO `e_section` VALUES ('74', '11', '投资性房地产的转换（1）');
INSERT INTO `e_section` VALUES ('75', '11', '投资性房地产的转换（2）');
INSERT INTO `e_section` VALUES ('76', '11', '投资性房地产的处置');

-- ----------------------------
-- Table structure for e_video
-- ----------------------------
DROP TABLE IF EXISTS `e_video`;
CREATE TABLE `e_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) DEFAULT NULL COMMENT '章节id',
  `video_name` varchar(64) DEFAULT NULL COMMENT '视频名称',
  `video_url` varchar(128) DEFAULT NULL COMMENT '视频url',
  `cover_url` varchar(128) DEFAULT NULL COMMENT '视频截图url',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_video
-- ----------------------------
INSERT INTO `e_video` VALUES ('288', '71', '第一讲 投资性房地产的特征与范围', '/course/0000013/01.mp4', null, '2018-11-19 12:21:47');
INSERT INTO `e_video` VALUES ('289', '72', '第一讲 投资性房地产的确认和初始计量', '/course/0000013/02.mp4', null, '2018-11-19 12:22:07');
INSERT INTO `e_video` VALUES ('290', '73', '第一讲 投资性房地产的后续计量', '/course/0000013/03.mp4', null, '2018-11-19 12:22:23');
INSERT INTO `e_video` VALUES ('291', '74', '第一讲 投资性房地产的转换（1）', '/course/0000013/04.mp4', null, '2018-11-19 12:22:38');
INSERT INTO `e_video` VALUES ('292', '75', '第一讲 投资性房地产的转换（2）', '/course/0000013/05.mp4', null, '2018-11-19 12:22:52');
INSERT INTO `e_video` VALUES ('293', '76', '第一讲 投资性房地产的处置', '/course/0000013/06.mp4', null, '2018-11-19 12:23:08');
