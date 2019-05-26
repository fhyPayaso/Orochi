


-- ----------------------------
-- user
-- ----------------------------
DROP TABLE IF EXISTS `orochi_user`;
CREATE TABLE `orochi_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(100) NOT NULL COMMENT '手机号',
  `password` varchar(100) NOT NULL COMMENT '加密后的密码',
  `name` varchar(100) DEFAULT '' COMMENT '用户名',
  `permission` int(1) DEFAULT 0 COMMENT '用户权限',
  `cover_image_url` varchar(100) DEFAULT '' COMMENT '头像图片地址',
  `register_on` int(20) DEFAULT 0 COMMENT '注册时间',
  `update_on` int(20) DEFAULT 0 COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';




-- ----------------------------
-- app
-- ----------------------------
DROP TABLE IF EXISTS `orochi_app`;
CREATE TABLE `orochi_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '应用名称',
  `desc` varchar(255) DEFAULT '' COMMENT '应用简述',
  `cover_image_url` varchar(100) DEFAULT '' COMMENT '封面图片地址',
  `created_on` int(20) COMMENT '新建时间',
  `created_by` int(10) COMMENT '创建人',
  `update_on` int(20) COMMENT '修改时间',
  `update_by` int(10) COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app应用';


-- ----------------------------
-- apk
-- ----------------------------
DROP TABLE IF EXISTS `orochi_apk`;
CREATE TABLE `orochi_apk` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` int(10) NOT NULL COMMENT '所属的应用id',
  `version_code` varchar(100) NOT NULL COMMENT '当前包版本号',
  `download_url` varchar(255) NOT NULL COMMENT '下载地址',
  `desc` varchar(255) DEFAULT '' COMMENT '应用简述',
  `created_on` int(20) COMMENT '新建时间',
  `created_by` int(10) COMMENT '创建人',
  `update_on` int(20) COMMENT '修改时间',
  `update_by` int(10) COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安装包';