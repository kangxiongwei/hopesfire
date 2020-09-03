package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.service.IAttachService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.AttachEntity;
import com.kxw.hopesfire.dao.mapper.AttachMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/6 6:35 下午
 */
@Service("attachService")
public class AttachServiceImpl implements IAttachService {

    @Resource
    private AttachMapper attachMapper;

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    public void save(AttachModel model) {
        AttachEntity entity = BaseConvert.convertEntity(model, new AttachEntity());
        if (entity.getId() == null) {
            this.attachMapper.insert(entity);
        } else {
            this.attachMapper.updateById(entity);
        }
    }

    /**
     * 批量创建附件
     *
     * @param attaches
     */
    @Override
    public void save(List<AttachModel> attaches) {
        if (CollectionUtils.isEmpty(attaches)) {
            return;
        }
        List<AttachEntity> entities = BaseConvert.convertEntities(attaches, new AttachEntity());
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        this.attachMapper.batchInsert(entities);
    }

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.attachMapper.deleteById(id);
    }

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    @Override
    public AttachModel get(Long id) {
        AttachEntity entity = this.attachMapper.selectById(id);
        return BaseConvert.convertModel(new AttachModel(), entity);
    }

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<AttachModel> list(AttachModel model) {
        Wrapper<AttachEntity> wrapper = BaseConvert.convertWrapper(model, new AttachEntity());
        List<AttachEntity> entities = this.attachMapper.selectList(wrapper);
        return BaseConvert.convertModels(new AttachModel(), entities);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(AttachModel model) {
        Wrapper<AttachEntity> wrapper = BaseConvert.convertWrapper(model, new AttachEntity());
        IPage<AttachEntity> page = this.attachMapper.selectPage(PageConvert.convertPage(model), wrapper);
        return BaseConvert.convertPageModel(new AttachModel(), page);
    }

    /**
     * 根据文件名和类型查询文件
     *
     * @param attachType
     * @param fileName
     * @return
     */
    @Override
    public AttachModel getAttachByName(Integer attachType, String fileName) {
        AttachEntity entity = new AttachEntity();
        entity.setOriginName(fileName);
        entity.setAttachType(attachType);
        AttachEntity one = this.attachMapper.selectOne(new QueryWrapper<>(entity));
        return BaseConvert.convertModel(new AttachModel(), one);
    }

}
