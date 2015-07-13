
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resc
-- ----------------------------
DROP TABLE IF EXISTS `sys_resc`;
CREATE TABLE `sys_resc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '权限url',
  `resc_type` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
-- Records of sys_resc
-- ----------------------------
INSERT INTO `sys_resc` VALUES ('1', 'relationship/list', '1');
INSERT INTO `sys_resc` VALUES ('2', 'rule/list', '1');
INSERT INTO `sys_resc` VALUES ('3', 'channel/list', '1');
INSERT INTO `sys_resc` VALUES ('4', 'history/list', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `authority` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', '普通用户', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_role_resc
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resc`;
CREATE TABLE `sys_role_resc` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `resc_id` bigint(20) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限';

-- ----------------------------
-- Records of sys_role_resc
-- ----------------------------
INSERT INTO `sys_role_resc` VALUES ('1', '1');
INSERT INTO `sys_role_resc` VALUES ('1', '2');
INSERT INTO `sys_role_resc` VALUES ('1', '3');
INSERT INTO `sys_role_resc` VALUES ('2', '1');
INSERT INTO `sys_role_resc` VALUES ('2', '2');
INSERT INTO `sys_role_resc` VALUES ('2', '3');
INSERT INTO `sys_role_resc` VALUES ('1', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT b'1' COMMENT '账号是否未过期',
  `account_non_locked` bit(1) DEFAULT b'1' COMMENT '账号是否未被锁定',
  `credentials_non_expire` bit(1) DEFAULT b'1' COMMENT '凭证是否为过期',
  `enabled` bit(1) DEFAULT b'1' COMMENT '账号是否可用',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '', '', '', '', '2015-06-23 12:48:44');
INSERT INTO `sys_user` VALUES ('2', 'user', 'user', '', '', '', '', '2015-06-23 12:48:39');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');