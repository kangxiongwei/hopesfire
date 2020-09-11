DROP TABLE IF EXISTS t_user;
CREATE TABLE IF NOT EXISTS t_user
(
    id BIGINT(20) NOT NULL COMMENT '自增ID' PRIMARY KEY KEY AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL COMMENT '用户名称',
    password VARCHAR(128) NOT NULL COMMENT '用户密码',
    nickname VARCHAR(128) COMMENT '用户昵称',
    head_id BIGINT(20) COMMENT '用户头像 和t_attach对应',
    head_img VARCHAR(128) COMMENT '用户头像',
    sex TINYINT(4) COMMENT '性别',
    telephone VARCHAR(32) COMMENT '手机号',
    address VARCHAR(128) COMMENT '住址',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    UNIQUE KEY uk_name (username),
    KEY idx_head_id (head_id)
) COMMENT '用户表';

ALTER TABLE t_user ADD COLUMN head_id BIGINT(20) COMMENT '头像ID' AFTER nickname;
ALTER TABLE t_user ADD INDEX idx_head_id(head_id);
