CREATE TABLE t_tag (
  id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  tag_name VARCHAR(256) NOT NULL COMMENT '标签名称',

  username VARCHAR(128) NOT NULL COMMENT '用户名称',
  create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间'
  ) COMMENT '标签表';