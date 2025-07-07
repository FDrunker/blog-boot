CREATE TABLE IF NOT EXISTS `sys_operate_log` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title` varchar(50) DEFAULT '' COMMENT '模块标题',
    `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0查询 1新增 2修改 3删除 4其他）',
    `method` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '方法名称',
    `resp_time` bigint(20) DEFAULT NULL COMMENT '响应时间',
    `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
    `browser` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器类型',
    `operate_type` int(1) DEFAULT '0' COMMENT '操作类别（0网站用户 1后台用户 2小程序 3其他）',
    `operate_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求URL',
    `operate_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '主机地址',
    `operate_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作地点',
    `operate_param` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
    `json_result` text COMMENT '返回参数',
    `status` tinyint(4) DEFAULT NULL COMMENT '操作状态（0正常 1异常）',
    `error_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '错误消息',
    `create_id` bigint(20) DEFAULT NULL COMMENT '操作人id',
    `create_time` datetime DEFAULT NULL COMMENT '操作时间',
    `update_id` bigint(20) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统管理-操作日志记录';

CREATE TABLE IF NOT EXISTS `base_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小程序的openId',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
    `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户账号',
    `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户姓名',
    `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
    `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户,01小程序用户）',
    `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
    `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '手机号码',
    `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
    `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户加密盐值',
    `password` varchar(100) DEFAULT '' COMMENT '密码',
    `status` char(1) DEFAULT NULL COMMENT '帐号状态（0停用 1正常）',
    `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
    `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
    `create_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_id` bigint(20) DEFAULT NULL COMMENT '更新人id',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `is_delete` char(1) DEFAULT '0' COMMENT '删除标志（0正常 1删除）',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户中心-用户信息表';

CREATE TABLE IF NOT EXISTS `base_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` varchar(30) NOT NULL COMMENT '角色名称',
    `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
    `sort_num` bigint(20) NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `status` char(1) NOT NULL DEFAULT '0' COMMENT '角色状态（0停用 1正常）',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `create_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `is_delete` char(1) DEFAULT '0' COMMENT '删除标志（0正常 1删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户中心-角色信息表';

CREATE TABLE IF NOT EXISTS `base_permission` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
    `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
    `code` varchar(100) NOT NULL COMMENT '权限标识',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户中心-权限信息表';