package com.kxw.hopesfire.biz.convert;

import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.dao.entity.BaseEntity;
import com.kxw.hopesfire.dao.entity.RoleEntity;
import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.dao.model.BaseModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 拷贝属性
 *
 * @author kangxiongwei
 * @date 2020-07-24 23:27
 */
public class BaseConvert {

    private static final Map<Class<?>, Class<?>> ENTITY_MODEL = new HashMap<>();
    private static final Map<Class<?>, Class<?>> MODEL_ENTITY = new HashMap<>();

    static {
        ENTITY_MODEL.put(UserEntity.class, UserModel.class);
        MODEL_ENTITY.put(UserModel.class, UserEntity.class);
        ENTITY_MODEL.put(RoleEntity.class, RoleModel.class);
        MODEL_ENTITY.put(RoleModel.class, RoleEntity.class);
    }

    public static <E> BaseModel createModel(E entity) throws Exception {
        Class<?> clazz = ENTITY_MODEL.get(entity.getClass());
        return (BaseModel) clazz.newInstance();
    }

    public static <M> BaseEntity createEntity(M model) throws Exception {
        Class<?> clazz = MODEL_ENTITY.get(model.getClass());
        return (BaseEntity) clazz.newInstance();
    }

    /**
     * 将entity对象转换为model对象
     *
     * @param entity
     * @param <E>
     * @return
     */
    public static <E> BaseModel convertModel(E entity) throws Exception {
        if (entity == null) return null;
        BaseModel model = createModel(entity);
        copyProperties(entity, model);
        return model;
    }

    /**
     * 将model对象转换为entity对象
     *
     * @param model
     * @param <M>
     * @return
     */
    public static <M> BaseEntity convertEntity(M model) throws Exception {
        if (model == null) return null;
        BaseEntity entity = createEntity(model);
        copyProperties(model, entity);
        return entity;
    }

    private static void copyProperties(Object source, Object target) {
        Field[] fields = source.getClass().getFields();
        Class<?> targetClass = target.getClass();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Field targetField = targetClass.getField(fieldName);
                targetField.setAccessible(true);
                targetField.set(target, field.get(source));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
