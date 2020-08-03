CREATE TABLE t_group
(
    id INT NOT NULL COMMENT '自增ID' PRIMARY KEY KEY AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL COMMENT '群组名称',
    tenant_id INT NOT NULL COMMENT '所属租户ID',
    remark VARCHAR(256) COMMENT '群组描述',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    KEY idx_name (name)
) COMMENT '群组表';
