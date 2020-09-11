package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/6 9:01 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_attach")
public class AttachEntity extends BaseEntity {

    private Integer attachType;
    private String originName;
    private String fileName;
    private String filePath;
    private String fileUrl;
    private String thumbnailUrl;
    private String username;

}
