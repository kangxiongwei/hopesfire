package com.kxw.hopesfire.dao.model;

import lombok.Data;

/**
 * 分页数据模型
 *
 * @author kangxiongwei
 * @date 2020-07-03 11:39
 */
@Data
public class PagerModel {

    private long page;          //当前第几页
    private long pageSize;      //每页多少行
    private String sort;        //排序字段
    private String order;       //排序形式，asc | desc
    private long total;         //总记录数
    private long pages;         //总页数
    private Object records;     //数据

    public PagerModel() {
        this.page = 1;
        this.pageSize = 10;
        this.order = "asc";
    }

    public PagerModel(long page, long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

}
