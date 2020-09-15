CREATE TABLE IF NOT EXISTS t_banner (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    banner VARCHAR(128) NOT NULL COMMENT '栏目名称',
    banner_type TINYINT(4) DEFAULT 0 COMMENT '1导航栏目 2文章列表 3文章内容 4图片栏目',
    parent_id BIGINT(20) DEFAULT 0 COMMENT '父级栏目ID',
    weight TINYINT(4) DEFAULT 0 COMMENT '调节顺序权重',
    status TINYINT(4) DEFAULT 0 COMMENT '栏目状态 0未启用 1已启用',
    username VARCHAR(128) COMMENT '创建人',
    create_time DATETIME DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间'
) COMMENT '文章栏目表';
