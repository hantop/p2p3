/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-06 11:27:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for weixin_access_token
-- ----------------------------
DROP TABLE IF EXISTS `weixin_access_token`;
CREATE TABLE `weixin_access_token` (
  `id` varchar(50) NOT NULL,
  `access_token` varchar(600) DEFAULT NULL COMMENT 'access_token凭证',
  `openid` varchar(300) DEFAULT NULL COMMENT '用户标识',
  `expires_in` bigint(20) DEFAULT NULL COMMENT 'access_token接口调用凭证超时时间，单位（秒）',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `refresh_token` varchar(600) DEFAULT NULL COMMENT '用户刷新access_token',
  `scope` varchar(600) DEFAULT NULL COMMENT 'scope',
  `unionid` varchar(600) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）',
  `type` varchar(600) DEFAULT NULL COMMENT '类型:网页和非网页的token',
  `log_id` bigint(20) DEFAULT NULL COMMENT '日志记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信token';

-- ----------------------------
-- Records of weixin_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_fans
-- ----------------------------
DROP TABLE IF EXISTS `weixin_fans`;
CREATE TABLE `weixin_fans` (
  `id` varchar(50) NOT NULL,
  `subscribe` int(11) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
  `openid` varchar(64) DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `nickname` varchar(64) DEFAULT NULL COMMENT '用户的昵称',
  `sex` int(11) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(64) DEFAULT NULL COMMENT '用户所在城市',
  `country` varchar(64) DEFAULT NULL COMMENT '用户所在国家',
  `province` varchar(64) DEFAULT NULL COMMENT '用户所在省份',
  `language` varchar(64) DEFAULT NULL COMMENT '用户的语言',
  `headimgurl` varchar(256) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `subscribe_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `unionid` varchar(64) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `remark` varchar(200) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `groupid` int(11) DEFAULT NULL COMMENT '用户所在的分组ID',
  `privilege` varchar(64) DEFAULT NULL COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  `log_id` bigint(20) DEFAULT NULL COMMENT '日志记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信粉丝';

-- ----------------------------
-- Records of weixin_fans
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_log
-- ----------------------------
DROP TABLE IF EXISTS `weixin_log`;
CREATE TABLE `weixin_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) DEFAULT NULL COMMENT '微信app_id',
  `target` varchar(100) DEFAULT NULL COMMENT '请求目标',
  `invoke` varchar(500) DEFAULT NULL COMMENT '执行的目标方法',
  `argus` text COMMENT '执行参数',
  `code` varchar(20) DEFAULT NULL COMMENT '返回状态吗',
  `err_msg` varchar(100) DEFAULT NULL COMMENT '错误描述',
  `thing` varchar(100) DEFAULT NULL COMMENT '执行动作',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '执行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信log';



-- ----------------------------
-- Records of weixin_log
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_qrcode
-- ----------------------------
DROP TABLE IF EXISTS `weixin_qrcode`;
CREATE TABLE `weixin_qrcode` (
  `id` varchar(50) NOT NULL,
  `scene_name` varchar(50) DEFAULT NULL COMMENT '二维码场景,指定该二维码是做什么应用的，招聘，广告，登陆，等等，场景管理',
  `action_name` varchar(20) DEFAULT NULL COMMENT '二维码类型（永久，临时），QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值',
  `scene_value` varchar(64) DEFAULT NULL COMMENT '二维码场景值scene',
  `scene_type` varchar(64) DEFAULT NULL COMMENT '二维码场景类型,int,String',
  `ticket_id` varchar(50) DEFAULT NULL COMMENT 'ticket外键',
  `create_time` bigint(20) DEFAULT NULL COMMENT '二维码的创建时间',
  `bytes` longblob COMMENT '二进制内容',
  `suffix` varchar(30) DEFAULT NULL COMMENT '文件后缀',
  `name` varchar(64) DEFAULT NULL COMMENT '文件名称',
  `enable` bit(1) DEFAULT NULL COMMENT '如果该二维码已经被引用后将变得不可以，如果是临时二维码需要判断类型',
  `log_id` bigint(20) DEFAULT NULL COMMENT '日志记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二维码';


-- ----------------------------
-- Records of weixin_qrcode
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_ticket
-- ----------------------------
DROP TABLE IF EXISTS `weixin_ticket`;
CREATE TABLE `weixin_ticket` (
  `id` varchar(50) NOT NULL,
  `log_id` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `ticket` varchar(300) DEFAULT NULL,
  `expire_seconds` bigint(20) DEFAULT NULL COMMENT '二维码的有效时间，以秒为单位。',
  `expires_in` bigint(20) DEFAULT NULL COMMENT 'jsapi_adk ticket有效时间',
  `url` varchar(300) DEFAULT NULL COMMENT '二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片',
  `type` varchar(20) DEFAULT NULL COMMENT 'ticket类型',
  `code` varchar(20) DEFAULT NULL COMMENT 'use_custom_code字段为true的卡券必须填写，非自定义code不必填写。',
  `card_id` varchar(32) DEFAULT NULL COMMENT '卡券ID',
  `openid` varchar(32) DEFAULT NULL COMMENT '指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，非指定openid不必填写。',
  `unique_code` bit(1) DEFAULT NULL COMMENT '指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false。',
  `outer_id` int(11) DEFAULT NULL COMMENT '领取场景值，用于领取渠道的数据统计，默认值为0，字段类型为整型，长度限制为60位数字。用户领取卡券后触发的事件推送中会带上此自定义场景值。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信ticket';






-- ----------------------------
-- Records of weixin_ticket
-- ----------------------------
drop table if exists weixin_msg;

/*==============================================================*/
/* Table: weixin_msg                                            */
/*==============================================================*/
CREATE TABLE `weixin_msg` (
  `id` varchar(64) NOT NULL,
  `app_id` varchar(64) DEFAULT NULL COMMENT 'appID（应用ID）',
  `to_user_name` varchar(64) DEFAULT NULL COMMENT '接收方帐号（收到的OpenID）',
  `from_user_name` varchar(64) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `create_time` bigint(20) DEFAULT NULL COMMENT '消息创建时间 （整型）',
  `msg_id` varchar(64) DEFAULT NULL COMMENT '消息id，64位整型',
  `msg_type` varchar(64) DEFAULT NULL COMMENT '消息类型，event',
  `event` varchar(64) DEFAULT NULL COMMENT '事件类型',
  `content` text COMMENT '消息内容',
  `pic_url` varchar(256) DEFAULT NULL COMMENT '消息内容格式',
  `media_id` varchar(64) DEFAULT NULL COMMENT '图片消息媒体id，可以调用多媒体文件下载接口拉取数据。',
  `format` varchar(64) DEFAULT NULL COMMENT '语音格式，如amr，speex等',
  `thumb_media_id` varchar(64) DEFAULT NULL COMMENT '视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据',
  `location_X` varchar(64) DEFAULT NULL COMMENT '地理位置维度',
  `location_Y` varchar(64) DEFAULT NULL COMMENT '地理位置经度',
  `scale` varchar(10) DEFAULT NULL COMMENT '地图缩放大小',
  `label` varchar(64) DEFAULT NULL COMMENT '地理位置信息',
  `title` varchar(64) DEFAULT NULL COMMENT '消息标题',
  `description` varchar(64) DEFAULT NULL COMMENT '消息描述',
  `url` varchar(256) DEFAULT NULL COMMENT '消息链接',
  `event_key` varchar(64) DEFAULT NULL COMMENT '事件KEY值',
  `ticket` varchar(200) DEFAULT NULL COMMENT '二维码的ticket',
  `latitude` varchar(64) DEFAULT NULL COMMENT '地理位置纬度(上报地理)',
  `longitude` varchar(64) DEFAULT NULL COMMENT '地理位置经度(上报地理)',
  `precision` varchar(64) DEFAULT NULL COMMENT '地理位置精度',
  `recognition` text COMMENT '语音识别结果',
  `article_count` int(11) DEFAULT NULL COMMENT '图文消息个数',
  `uniq_id` varchar(64) DEFAULT NULL COMMENT '商户自己内部ID',
  `poi_id` varchar(64) DEFAULT NULL COMMENT '微信的门店ID',
  `result` varchar(64) DEFAULT NULL COMMENT '审核结果',
  `msg` text COMMENT '成功的通知信息，或审核失败的驳回理由',
  `card_id` varchar(64) DEFAULT NULL COMMENT '卡券ID',
  `give_by_friend` bit(1) DEFAULT NULL COMMENT '是否为转赠，1代表是，0代表否。',
  `user_card_code` varchar(64) DEFAULT NULL COMMENT 'code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。',
  `old_user_card_code` varchar(64) DEFAULT NULL COMMENT '转赠前的code序列号',
  `outer_id` varchar(64) DEFAULT NULL COMMENT '领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加JS API接口中自定义该字段的整型值。',
  `consume_source` varchar(64) DEFAULT NULL COMMENT '核销来源。支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信交互的消息记录';

drop table if exists weixin_media;
CREATE TABLE `weixin_media` (
  `id` varchar(64) NOT NULL,
  `message_id` varchar(64) DEFAULT NULL COMMENT '所属消息id',
  `type` varchar(64) DEFAULT NULL COMMENT '媒体类型enum',
  `media_id` varchar(64) DEFAULT NULL COMMENT '通过素材管理接口上传多媒体文件，得到的id',
  `title` varchar(64) DEFAULT NULL COMMENT '消息的标题',
  `description` varchar(64) DEFAULT NULL COMMENT '消息的描述',
  `music_url` varchar(256) DEFAULT NULL COMMENT '音乐链接',
  `hqmusic_url` varchar(256) DEFAULT NULL COMMENT '高质量音乐链接，WIFI环境优先使用该链接播放音乐',
  `thumb_media_id` varchar(64) DEFAULT NULL COMMENT '缩略图的媒体id，通过上传多媒体文件，得到的id',
  `pic_url` varchar(256) DEFAULT NULL COMMENT '图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致',
  `url` text COMMENT '点击图文消息跳转链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体消息\r\n';




drop table if exists weixin_business;
-- 门店信息
CREATE TABLE `weixin_business` (
  `id` varchar(50) NOT NULL,
  `business_name` varchar(30) DEFAULT NULL COMMENT '门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美）',
  `branch_name` varchar(100) DEFAULT NULL COMMENT '分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）',
  `province` varchar(50) DEFAULT NULL COMMENT '门店所在的省份（直辖市填城市名,如：北京市）',
  `city` varchar(50) DEFAULT NULL COMMENT '门店所在的城市',
  `district` varchar(100) DEFAULT NULL COMMENT '门店所在地区',
  `address` varchar(300) DEFAULT NULL COMMENT '门店所在的详细街道地址（不要填写省市信息）',
  `telephone` varchar(300) DEFAULT NULL COMMENT '门店的电话（纯数字，区号、分机号均由“-”隔开）',
  `categories` varchar(300) DEFAULT NULL COMMENT '门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）',
  `offset_type` varchar(300) DEFAULT NULL COMMENT '坐标类型，1 为火星坐标（目前只能选1）',
  `longitude` varchar(10) DEFAULT NULL COMMENT '门店所在地理位置的经度',
  `latitude` varchar(10) DEFAULT NULL COMMENT '门店所在地理位置的纬度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）',
  `photo_list` text COMMENT '图片列表，url 形式，可以有多张图片，尺寸为\r\n            640*340px。必须为上一接口生成的url。图片内容不允许与门店不相关，不允许为二维码、员工合照（或模特肖像）、营业执照、无门店正门的街景、地图截图、公交地铁站牌、菜单截图等',
  `special` varchar(100) DEFAULT NULL COMMENT '特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务',
  `open_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '营业时间，24 小时制表示，用“-”连接，如 8:00-20:00',
  `avg_price` double DEFAULT NULL COMMENT '人均价格，大于0 的整数',
  `sid` varchar(50) DEFAULT NULL COMMENT '商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性',
  `poi_id` varchar(50) DEFAULT NULL COMMENT '微信的门店ID，微信内门店唯一标示ID',
  `introduction` varchar(300) DEFAULT NULL COMMENT '商户简介，主要介绍商户信息等',
  `recommend` varchar(300) DEFAULT NULL COMMENT '推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容',
  `available_state` int(11) DEFAULT NULL COMMENT '门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空',
  `update_status` int(11) DEFAULT NULL COMMENT '扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段没有在更新中或更新已生效，可以再次更新',
  `careate_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店信息';



