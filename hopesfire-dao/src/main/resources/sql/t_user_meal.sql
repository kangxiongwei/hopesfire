DROP TABLE IF EXISTS t_user_meal;
CREATE TABLE IF NOT EXISTS t_user_meal (
    id BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    meal_type TINYINT(8) NOT NULL COMMENT '菜品类型 1早餐 2午餐 3晚餐',
    main_meal VARCHAR(256) COMMENT '主食',
    meal_name VARCHAR(512) COMMENT '菜品名称',
    username VARCHAR(128) NOT NULL COMMENT '用户名称',
    create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间'
) COMMENT '用户饮食记录表'
