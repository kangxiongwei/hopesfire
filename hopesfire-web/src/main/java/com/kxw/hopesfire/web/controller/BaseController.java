package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.basic.util.FileUtil;
import com.kxw.hopesfire.basic.util.IoUtil;
import com.kxw.hopesfire.biz.enums.AttachTypeEnum;
import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 基本的控制类
 *
 * @author kangxiongwei
 * @date 2020/8/14 6:51 下午
 */
public class BaseController {

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    protected String getLoginUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || subject.getPrincipal() == null) {
            return null;
        }
        UserModel user = (UserModel) subject.getPrincipal();
        return user.getUsername();
    }

    /**
     * 持久化磁盘文件
     *
     * @param file
     * @param attachType
     * @return
     */
    protected AttachModel diskFile(MultipartFile file, Integer attachType, String filePath) {
        if (file == null || StringUtils.isBlank(file.getOriginalFilename())) {
            return null;
        }
        String filename = file.getOriginalFilename();
        try {
            File tempFile = File.createTempFile("temp_", filename);
            file.transferTo(tempFile);
            String realFileName = System.currentTimeMillis() + "_" + filename;
            String attachPath = AttachTypeEnum.getAttachType(attachType);
            String fileUrl = StringUtils.isNotBlank(attachPath) ? attachPath : "";
            File destFile = FileUtil.createFile(filePath + fileUrl, realFileName);
            IoUtil.copyFile(tempFile, destFile);
            tempFile.deleteOnExit();
            //封装attach对象
            AttachModel attach = new AttachModel();
            attach.setAttachType(attachType);
            attach.setOriginName(filename);
            attach.setFileName(realFileName);
            attach.setFilePath(destFile.getPath());
            attach.setFileUrl(File.separator + "attach" + File.separator + fileUrl + File.separator + realFileName);
            attach.setUsername(getLoginUsername() == null ? "admin" : getLoginUsername());
            return attach;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
