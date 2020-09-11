DROP TABLE IF EXISTS t_attach;

CREATE TABLE IF NOT EXISTS t_attach (
    id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    attach_type TINYINT(16) NOT NULL COMMENT '附件类型 1 首页轮播图 2 用户头像等',
    origin_name VARCHAR(128) NOT NULL COMMENT '附件原始文件名称',
    file_name VARCHAR(256) NOT NULL COMMENT '附件实际存储名称',
    file_path VARCHAR(512) NOT NULL COMMENT '附件实际存储路径',
    file_url VARCHAR(256) NOT NULL COMMENT '附件的URL地址',
    username VARCHAR(128) NOT NULL COMMENT '用户名称',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    KEY idx_origin_name(origin_name),
    KEY idx_username(username)
) COMMENT '附件存储表';


ALTER TABLE t_attach ADD COLUMN thumbnail_url VARCHAR(256) COMMENT '缩略图地址' AFTER file_url;
