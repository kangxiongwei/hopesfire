CREATE TABLE t_role_group
(
    id BIGINT(20) NOT NULL COMMENT '自增ID' PRIMARY KEY KEY AUTO_INCREMENT,
    role_id BIGINT(20) NOT NULL COMMENT '角色ID',
    group_id BIGINT(20) NOT NULL COMMENT '分组ID',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    KEY idx_role_id (role_id),
    KEY idx_group_id (group_id)
) COMMENT '角色和分组关系表';
