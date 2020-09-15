CREATE TABLE IF NOT EXISTS t_article (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL COMMENT '文章标题',
    icon_id BIGINT(20) COMMENT '展示图片',
    banner_id BIGINT(20) COMMENT '所属栏目',
    keyword VARCHAR(512) COMMENT '关键字，用|分割',
    summary VARCHAR(512) COMMENT '文章摘要',
    content LONGTEXT DEFAULT NULL COMMENT '文章内容',
    weight TINYINT(4) DEFAULT 0 COMMENT '调节顺序的权重',
    status TINYINT(4) DEFAULT 0 COMMENT '文章状态： 0未审核 1审核中 2已通过 3已驳回 4已发表',
    audit_report VARCHAR(512) DEFAULT NULL COMMENT '审核意见',
    author VARCHAR(128) NOT NULL COMMENT '作者',
    auditor VARCHAR(128) NOT NULL COMMENT '审核人员',
    publish_time DATETIME COMMENT '发表时间',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    KEY idx_title(title),
    KEY idx_create_time(create_time)
) COMMENT '文章信息表';
