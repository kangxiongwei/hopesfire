package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件管理
 *
 * @author kangxiongwei
 * @date 2020/8/6 6:34 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttachModel extends BaseModel {

    private Integer attachType;     //附件类型
    private String originName;      //附件原始名称
    private String fileName;        //附件实际名称
    private String filePath;        //附件实际路径
    private String fileUrl;         //附件的URL地址
    private String username;        //用户名称

}
