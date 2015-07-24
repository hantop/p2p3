/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-24 18:32:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` varchar(128) DEFAULT NULL COMMENT '菜单KEY值',
  `value` varchar(128) DEFAULT NULL COMMENT 'value',
  `type` varchar(100) DEFAULT NULL COMMENT '菜单的响应动作类型',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `url` varchar(256) DEFAULT NULL COMMENT '网页链接，用户点击菜单可打开链接',
  `media_id` varchar(50) DEFAULT NULL COMMENT '调用新增永久素材接口返回的合法media_id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='配置信息';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'SMS', 'SMS', null, '易美软通短信配置', null, null, null);
INSERT INTO `sys_config` VALUES ('2', 'SMS_SPECIAL_NO', '370413', 'SMS', '易美软通特服号', null, null, '1');
INSERT INTO `sys_config` VALUES ('3', 'SMS_KEY', 'fenlibao', 'SMS', '易美软通注册KEY', null, null, '1');
INSERT INTO `sys_config` VALUES ('4', 'SMS_AUTO_REGISTER', 'true', 'SMS', '易美软通自动注册', null, null, '1');
INSERT INTO `sys_config` VALUES ('5', 'SMS_SIGN', '【分利宝】', 'SMS', '易美软通签名', null, null, '1');
INSERT INTO `sys_config` VALUES ('6', 'SMS_SOFTWARE_SERIAL_NO', '6SDK-EMY-6688-KKWQP', 'SMS', '易美软通序列号', null, null, '1');
INSERT INTO `sys_config` VALUES ('7', 'SMS_PASSWORD', '439365', 'SMS', '易美软通密码', null, null, '1');
INSERT INTO `sys_config` VALUES ('8', 'WEIXIN', 'WEIXIN', null, '微信配置', null, null, null);
INSERT INTO `sys_config` VALUES ('9', 'WX_NAME', '微信二维码', 'WEIXIN', '微信二维码名称', null, null, '8');
INSERT INTO `sys_config` VALUES ('10', 'WX_SUFFIX', '.JPG', 'WEIXIN', '微信二维码后缀', null, null, '8');
INSERT INTO `sys_config` VALUES ('11', 'SCENE_ZHAOPIN', 'SCENE_ZHAOPIN', 'WEIXIN', '二维码招聘场景', null, null, '8');
INSERT INTO `sys_config` VALUES ('12', 'WX_TOKEN_KEY', 'fenlibao', 'WEIXIN', '微信Token', null, null, '8');
INSERT INTO `sys_config` VALUES ('13', 'WX_APP_ID', 'wx21b7659ce16b13d9', 'WEIXIN', 'AppID(应用ID)', null, null, '8');
INSERT INTO `sys_config` VALUES ('14', 'WX_APP_SECRET', '096da6c0e64e509a2bedf080c3bd45ef', 'WEIXIN', 'AppSecret(应用密钥)', null, null, '8');
