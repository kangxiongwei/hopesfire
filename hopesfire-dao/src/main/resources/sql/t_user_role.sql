CREATE TABLE t_user_role
(
    id BIGINT(20) NOT NULL COMMENT '自增ID' PRIMARY KEY KEY AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL COMMENT '用户ID',
    role_id BIGINT(20) NOT NULL COMMENT '角色ID',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    KEY idx_user_id (user_id),
    KEY idx_role_id (role_id)
) COMMENT '用户角色关系表';
