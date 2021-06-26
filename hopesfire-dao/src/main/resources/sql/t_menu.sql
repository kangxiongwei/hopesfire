DROP TABLE  IF EXISTS t_menu;
DROP TABLE IF EXISTS t_route;

CREATE TABLE IF NOT EXISTS t_menu (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL COMMENT '菜单名称',
  icon VARCHAR(64) DEFAULT NULL COMMENT '图标',
  parent_id BIGINT NOT NULL DEFAULT 0 COMMENT '父级菜单ID',
  level INT NOT NULL DEFAULT 0 COMMENT '当前是几级菜单',
  route_path VARCHAR(128) DEFAULT NULL COMMENT '路由，冗余字段',
  route_id BIGINT(20) DEFAULT 0 COMMENT '路由',
  create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  INDEX idx_name(name),
  INDEX idx_parent_id(parent_id),
  INDEX idx_route_id(route_id)
) COMMENT '菜单表';


CREATE TABLE IF NOT EXISTS t_route (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL COMMENT '路由名称',
  path VARCHAR(128) NOT NULL COMMENT '路径',
  meta VARCHAR(256) COMMENT '元数据，JSON格式',
  resource_path VARCHAR(256) NOT NULL COMMENT '资源路径',
  parent_id BIGINT NOT NULL DEFAULT 0 COMMENT '父目录ID',
  menu_id BIGINT NOT NULL DEFAULT 0 COMMENT '菜单ID',
  create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  INDEX idx_menu_id(menu_id),
  INDEX idx_parent_id(parent_id),
  INDEX idx_name(name)
) COMMENT '路由表';