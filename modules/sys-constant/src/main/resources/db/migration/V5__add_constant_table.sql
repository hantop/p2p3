/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-03 15:58:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_constant
-- ----------------------------
DROP TABLE IF EXISTS `sys_constant`;
CREATE TABLE `sys_constant` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(100) DEFAULT NULL COMMENT '变量键值',
  `value` text COMMENT '变量内容',
  `type` varchar(100) DEFAULT NULL COMMENT '变量类型',
  `name` text COMMENT '变量名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='常量管理,提供系统的配置';

-- ----------------------------
-- Records of sys_constant
-- ----------------------------
INSERT INTO `sys_constant` VALUES ('1', 'SMS_SOFTWARE_SERIAL_NO', '6SDK-EMY-6688-KKWQP', 'SMS', '易美软通序列号');
INSERT INTO `sys_constant` VALUES ('2', 'SMS_PASSWORD', '439365', 'SMS', '易美软通密码');
INSERT INTO `sys_constant` VALUES ('3', 'SMS_SPECIAL_NO', '370413', 'SMS', '易美软通特服号');
INSERT INTO `sys_constant` VALUES ('4', 'SMS_KEY', 'fenlibao', 'SMS', '易美软通注册KEY');
INSERT INTO `sys_constant` VALUES ('5', 'SMS_AUTO_REGISTER', 'true', 'SMS', '易美软通自动注册');
INSERT INTO `sys_constant` VALUES ('6', 'SMS_SIGN', '【分利宝】', 'SMS', '易美软通签名');
INSERT INTO `sys_constant` VALUES ('7', 'WX_TOKEN_KEY', 'fenlibao', 'WEIXIN', '微信Token');
INSERT INTO `sys_constant` VALUES ('8', 'WX_APP_ID', 'wx21b7659ce16b13d9', 'WEIXIN', 'AppID(应用ID)');
INSERT INTO `sys_constant` VALUES ('9', 'WX_APP_SECRET', '096da6c0e64e509a2bedf080c3bd45ef', 'WEIXIN', 'AppSecret(应用密钥)');
INSERT INTO `sys_constant` VALUES ('10', 'WX_NAME', '微信二维码', 'WEIXIN', '微信二维码名称');
INSERT INTO `sys_constant` VALUES ('11', 'WX_SUFFIX', '.JPG', 'WEIXIN', '微信二维码后缀');
INSERT INTO `sys_constant` VALUES ('12', 'SCENE_ZHAOPIN', 'SCENE_ZHAOPIN', 'WEIXIN', '二维码招聘场景');