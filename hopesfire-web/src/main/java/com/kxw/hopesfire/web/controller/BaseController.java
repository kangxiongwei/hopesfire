package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.basic.util.FileUtil;
import com.kxw.hopesfire.basic.util.ImageUtil;
import com.kxw.hopesfire.basic.util.IoUtil;
import com.kxw.hopesfire.biz.enums.AttachTypeEnum;
import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.web.model.AttachUploadInfoModel;
import com.kxw.hopesfire.web.model.AttachUploadModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    protected String getUsername() {
        String username = this.getLoginUsername();
        return username == null ? "admin" : username;
    }

    /**
     * 上传附件的方法
     *
     * @param model
     */
    protected AttachUploadInfoModel uploadAttaches(AttachUploadModel model, String attachPath) {
        AttachUploadInfoModel result = new AttachUploadInfoModel();
        MultipartFile[] files = model.getFiles();
        if (files == null || files.length <= 0) {
            return result;
        }
        List<AttachModel> attaches = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            try {
                AttachModel attach = diskFile(file, model.getAttachType(), attachPath);
                if (attach != null) {
                    attaches.add(attach);
                } else {
                    errors.add(filename + "上传失败！");
                }
            } catch (Exception e) {
                errors.add(filename + "上传失败！");
            }
        }
        result.setAttaches(attaches);
        result.setMessages(errors);
        return result;
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
            AttachTypeEnum attachTypeEnum = AttachTypeEnum.getAttachType(attachType);
            String attachPath = attachTypeEnum == null ? "" : attachTypeEnum.getAttachType();
            String fileUrl = StringUtils.isNotBlank(attachPath) ? attachPath : "";
            File destFile = FileUtil.createFile(filePath + fileUrl, realFileName);
            IoUtil.copyFile(tempFile, destFile);
            File thumbFile = null;
            if (attachTypeEnum != null) {
                thumbFile = ImageUtil.compress(destFile, attachTypeEnum.getWidth(), attachTypeEnum.getHeight());
            }
            tempFile.deleteOnExit();
            //封装attach对象
            AttachModel attach = new AttachModel();
            attach.setAttachType(attachType);
            attach.setOriginName(filename);
            attach.setFileName(realFileName);
            attach.setFilePath(destFile.getPath());
            attach.setFileUrl(destFile.getPath().replace(filePath, "/attach/"));
            if (thumbFile != null) {
                attach.setThumbnailUrl(thumbFile.getPath().replace(filePath, "/attach/"));
            }
            attach.setUsername(getUsername());
            return attach;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
