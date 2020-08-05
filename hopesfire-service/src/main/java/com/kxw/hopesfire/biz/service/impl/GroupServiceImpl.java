package com.kxw.hopesfire.biz.service.impl;

import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.model.GroupModel;
import com.kxw.hopesfire.biz.service.IGroupService;
import com.kxw.hopesfire.dao.entity.GroupEntity;
import com.kxw.hopesfire.dao.mapper.GroupMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分组接口
 *
 * @author kangxiongwei
 * @date 2020/8/5 10:34 上午
 */
@Service("groupService")
public class GroupServiceImpl implements IGroupService {

    @Resource
    private GroupMapper groupMapper;

    /**
     * 增加分组
     *
     * @param group
     */
    @Override
    public void save(GroupModel group) {
        GroupEntity entity = BaseConvert.convertEntity(group, new GroupEntity());
        if (entity.getId() == null) {
            this.groupMapper.insert(entity);
        } else {
            this.groupMapper.updateById(entity);
        }
    }

    /**
     * 删除分组
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.groupMapper.deleteById(id);
    }

    /**
     * 查询分组
     *
     * @param id
     * @return
     */
    @Override
    public GroupModel get(Long id) {
        GroupEntity entity =  this.groupMapper.selectById(id);
        return BaseConvert.convertModel(new GroupModel(), entity);
    }

    /**
     * 分页查询
     *
     * @param group
     * @return
     */
    @Override
    public PagerModel find(GroupModel group) {
        return null;
    }

    /**
     * 不分页查询
     *
     * @param group
     * @return
     */
    @Override
    public List<GroupModel> list(GroupModel group) {
        return null;
    }
}
