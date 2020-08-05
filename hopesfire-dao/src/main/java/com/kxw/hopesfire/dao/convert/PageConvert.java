package com.kxw.hopesfire.dao.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kxw.hopesfire.dao.model.PagerModel;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020-07-01 17:58
 */
public class PageConvert {

    /**
     * 转换分页结果
     *
     * @param page
     * @param records
     * @return
     */
    public static <E, M> PagerModel convertToModel(IPage<E> page, List<M> records) {
        PagerModel result = new PagerModel();
        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setPages(page.getPages());
        result.setTotal(page.getTotal());
        result.setRecords(records);
        return result;
    }

    /**
     * 转换分页参数
     *
     * @param pager
     * @param <E>
     * @return
     */
    public static <E> IPage<E> convertPage(PagerModel pager) {
        Page<E> page = new Page<>(pager.getPage(), pager.getPageSize());
        String sort = pager.getSort();
        if (sort != null && !"".equals(sort)) {
            String order = pager.getOrder();
            OrderItem item = "desc".equalsIgnoreCase(order) ? OrderItem.desc(sort) : OrderItem.asc(sort);
            page.setOrders(Arrays.asList(item));
        }
        return page;
    }

}
