package com.kxw.hopesfire.web.model;

import com.kxw.hopesfire.biz.model.AttachModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 附件上传的信息
 *
 * @author kangxiongwei
 * @date 2020/9/3 5:27 下午
 */
@Data
public class AttachUploadInfoModel {

    private List<AttachModel> attaches = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

}
