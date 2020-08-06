package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.basic.util.IoUtil;
import com.kxw.hopesfire.biz.enums.AttachTypeEnum;
import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IAttachService;
import com.kxw.hopesfire.web.model.AttachUploadModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片管理，所有图片操作都放在这里
 *
 * @author kangxiongwei
 * @date 2020/8/6 6:33 下午
 */
@RestController
@RequestMapping("/attach")
public class AttachController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    @Resource
    private IAttachService attachService;

    @PostMapping("/save")
    public HttpBaseModel save(AttachUploadModel model, HttpServletRequest request) {
        MultipartFile[] files = model.getFiles();
        if (files == null || files.length <= 0) {
            return HttpBaseModel.buildSuccess(null);
        }
        List<String> response = new ArrayList<>();
        List<AttachModel> attaches = new ArrayList<>();

        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());
        System.out.println(request.getServerName());
        System.out.println(request.getServletPath());
        System.out.println(request.getServerPort());
        System.out.println(request.getScheme());

        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            try {
                AttachModel attach = diskFile(file, model.getAttachType());
                if (attach != null) {
                    attaches.add(attach);
                }
                response.add(filename + "上传成功！");
            } catch (Exception e) {
                LOGGER.error("上传文件{}失败，异常为：", filename, e);
                response.add(filename + "上传失败！");
            }
        }
        attachService.save(attaches);
        return HttpBaseModel.buildSuccess(response);
    }

    private AttachModel diskFile(MultipartFile file, Integer attachType) {
        URL path = this.getClass().getClassLoader().getResource("");
        if (file == null || path == null) {
            return null;
        }
        String fileUrl = "static/images";
        String attachPath = AttachTypeEnum.getAttachType(attachType);
        if (StringUtils.isNotBlank(attachPath)) {
            fileUrl += "/" + attachPath;
        }
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            return null;
        }
        try {
            File tempFile = File.createTempFile("temp_", filename);
            file.transferTo(tempFile);
            String realFileName = System.currentTimeMillis() + "_" + filename;
            File destFile = new File(path.getPath() + fileUrl, realFileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            IoUtil.copyFile(tempFile, destFile);
            tempFile.deleteOnExit();
            //封装附件对象
            AttachModel attach = new AttachModel();
            attach.setAttachType(attachType);
            attach.setOriginName(filename);
            attach.setFileName(realFileName);
            attach.setFilePath(destFile.getPath());
            attach.setFileUrl(fileUrl + "/" + realFileName);
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject == null ? null : subject.getPrincipal();
            attach.setUsername(principal == null ? "admin" : ((UserModel) principal).getUsername());
            return attach;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
