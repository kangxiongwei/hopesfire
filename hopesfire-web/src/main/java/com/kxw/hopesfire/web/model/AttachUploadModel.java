package com.kxw.hopesfire.web.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kangxiongwei
 * @date 2020/8/6 7:16 下午
 */
@Data
public class AttachUploadModel {

    private Integer attachType;
    private MultipartFile[] files;

}
