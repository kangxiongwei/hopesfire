package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.AttachModel;

import java.util.List;

/**
 * 图片处理接口
 *
 * @author kangxiongwei
 * @date 2020/8/6 6:34 下午
 */
public interface IAttachService extends IBaseService<AttachModel> {

    /**
     * 批量创建附件
     *
     * @param attaches
     */
    void save(List<AttachModel> attaches);

    /**
     * 根据文件名和类型查询文件
     *
     * @param attachType
     * @param fileName
     * @return
     */
    AttachModel getAttachByName(Integer attachType, String fileName);

}
