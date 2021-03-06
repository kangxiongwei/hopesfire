CREATE TABLE t_tenant
(
    id INT NOT NULL COMMENT '自增ID' PRIMARY KEY KEY AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL COMMENT '租户名称',
    remark VARCHAR(256) COMMENT '租户描述',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    KEY idx_name (name)
) COMMENT '租户表';

