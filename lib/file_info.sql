CREATE TABLE `file_info` (
                             `id` int NOT NULL AUTO_INCREMENT COMMENT '自增逐渐',
                             `file_name` varchar(200) COLLATE utf8_bin DEFAULT '' COMMENT '文件名称',
                             `file_type` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '所属部门',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `create_by` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '上传用户',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT ...tf8 COLLATE=utf8_bin COMMENT='文件表'