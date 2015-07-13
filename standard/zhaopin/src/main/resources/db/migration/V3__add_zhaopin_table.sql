/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-06-25 13:48:13
*/


-- ----------------------------
-- Table structure for zhaopin_channel
-- ----------------------------
DROP TABLE IF EXISTS `zhaopin_channel`;
CREATE TABLE `zhaopin_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '推广规则ID',
  `url` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '建立URL',
  `scene_id` int(11) DEFAULT NULL COMMENT '二维码scene_id',
  `status` bit(1) DEFAULT b'0' COMMENT '投放方式状态：启用或停用',
  `del` bit(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='投放方式表';

-- ----------------------------
-- Records of zhaopin_channel
-- ----------------------------
INSERT INTO `zhaopin_channel` VALUES ('61', '哎哎呀呀', '24', 'http://www.baidu.com', '61', '', '\0');
INSERT INTO `zhaopin_channel` VALUES ('62', 'ad', '24', 'http://www.baidu.com', '62', '\0', '\0');

-- ----------------------------
-- Table structure for zhaopin_channel_log
-- ----------------------------
DROP TABLE IF EXISTS `zhaopin_channel_log`;
CREATE TABLE `zhaopin_channel_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(20) DEFAULT NULL COMMENT '访问ip',
  `atime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '访问时间',
  `type` int(11) DEFAULT NULL COMMENT '操作类型:1,扫描量，2：转发量，3：阅读量，4：状态',
  `channel_id` bigint(20) DEFAULT NULL COMMENT '渠道id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道管理log';

-- ----------------------------
-- Records of zhaopin_channel_log
-- ----------------------------

-- ----------------------------
-- Table structure for zhaopin_relationship
-- ----------------------------
DROP TABLE IF EXISTS `zhaopin_relationship`;
CREATE TABLE `zhaopin_relationship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_openid` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '推荐人openId',
  `from_user_name` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '推荐人名称',
  `from_user_name_phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '推荐人手机号',
  `to_user_openid` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '被推荐人openid',
  `to_user_name` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '被推荐人名称',
  `to_user_name_phone` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '被推荐手机号',
  `try_status` bit(1) DEFAULT NULL COMMENT '试用状态',
  `positive_status` bit(1) DEFAULT NULL COMMENT '转正状态',
  `trial_price` double DEFAULT NULL COMMENT '试用成功价格',
  `positive_price` double DEFAULT NULL COMMENT '转正成功价格',
  `browse_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '浏览时间',
  `resume` longblob COMMENT '简历',
  `file_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名称',
  `suffix` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '文件类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='人才库关系';

-- ----------------------------
-- Records of zhaopin_relationship
-- ----------------------------
INSERT INTO `zhaopin_relationship` VALUES ('21', 'olHvdt0zqAkjRcpPBOJqplXiBVmM', null, null, 'olHvdtxbz0odpHay_JWYif8bZVJA', null, null, '', '\0', null, null, '2015-06-25 13:41:26', null, null, null);
INSERT INTO `zhaopin_relationship` VALUES ('22', 'olHvdtxbz0odpHay_JWYif8bZVJA', null, null, 'olHvdt0F6_h_5nMVUrZfBfHltzUw', null, null, '\0', '\0', null, null, '2015-06-25 13:41:52', null, null, null);
INSERT INTO `zhaopin_relationship` VALUES ('23', 'olHvdtxbz0odpHay_JWYif8bZVJA', null, null, 'olHvdt6c6ZLzPzQeD9TWHvQk32GE', null, null, '\0', '', null, null, '2015-06-25 13:41:59', null, null, null);
INSERT INTO `zhaopin_relationship` VALUES ('24', 'olHvdtxbz0odpHay_JWYif8bZVJA', null, null, 'olHvdt1Z2v75u66n9ySl1_wkQMVA', null, null, '\0', '\0', null, null, '2015-06-25 13:45:27', null, null, null);

-- ----------------------------
-- Table structure for zhaopin_relationship_history
-- ----------------------------
DROP TABLE IF EXISTS `zhaopin_relationship_history`;
CREATE TABLE `zhaopin_relationship_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `relationship_id` bigint(20) DEFAULT NULL COMMENT '推荐关系id',
  `provide` bit(1) DEFAULT NULL COMMENT '是否发放',
  `provide_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发放时间',
  `operator` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '发放人',
  `provide_price` double(10,2) DEFAULT NULL COMMENT '发放金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='奖品发放记录';

-- ----------------------------
-- Records of zhaopin_relationship_history
-- ----------------------------
INSERT INTO `zhaopin_relationship_history` VALUES ('7', '21', '', '2015-06-25 13:47:14', 'user', null);
INSERT INTO `zhaopin_relationship_history` VALUES ('8', '23', '', '2015-06-25 13:47:26', 'user', null);

-- ----------------------------
-- Table structure for zhaopin_rule
-- ----------------------------
DROP TABLE IF EXISTS `zhaopin_rule`;
CREATE TABLE `zhaopin_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operator` varchar(20) DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '推广规则名称',
  `recommend_success` double DEFAULT NULL COMMENT '推荐成功费用',
  `trial_success` double NOT NULL COMMENT '试用成功费用',
  `positive_success` double DEFAULT NULL COMMENT '转正成功费用',
  `status` bit(1) DEFAULT b'0' COMMENT '启用状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `del` bit(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=gbk COMMENT='推广规则表';

-- ----------------------------
-- Records of zhaopin_rule
-- ----------------------------
INSERT INTO `zhaopin_rule` VALUES ('24', null, '哎呀呀招聘', '1', '2', '3', '', '2015-06-25 13:13:52', null);
