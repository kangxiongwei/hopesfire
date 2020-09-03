package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.basic.util.IoUtil;
import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.service.IAttachService;
import com.kxw.hopesfire.web.config.ApplicationConfiguration;
import com.kxw.hopesfire.web.model.AttachDownloadModel;
import com.kxw.hopesfire.web.model.AttachUploadModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 附件管理，所有附件操作都放在这里
 *
 * @author kangxiongwei
 * @date 2020/8/6 6:33 下午
 */
@RestController
@RequestMapping("/ctl/attach")
public class AttachController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    @Resource
    private ApplicationConfiguration applicationConfiguration;
    @Resource
    private IAttachService attachService;

    @PostMapping("/upload")
    public HttpBaseModel upload(AttachUploadModel model) {
        MultipartFile[] files = model.getFiles();
        if (files == null || files.length <= 0) {
            return HttpBaseModel.buildSuccess(null);
        }
        List<String> response = new ArrayList<>();
        List<AttachModel> attaches = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            try {
                AttachModel attach = diskFile(file, model.getAttachType(), applicationConfiguration.getAttachPath());
                if (attach != null) {
                    attaches.add(attach);
                    response.add(attach.getFileUrl());
                } else {
                    response.add(filename + "上传失败！");
                }
            } catch (Exception e) {
                LOGGER.error("上传文件{}失败，异常为：", filename, e);
                response.add(filename + "上传失败！");
            }
        }
        attachService.save(attaches);
        return HttpBaseModel.buildSuccess(response);
    }

    @GetMapping("/download")
    public void download(AttachDownloadModel model, HttpServletResponse response) {
        AttachModel attach = this.attachService.getAttachByName(model.getAttachType(), model.getFileName());
        if (attach == null) {
            return;
        }
        File file = new File(attach.getFilePath());
        if (file.exists()) {
            try {
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;fileName=" + model.getFileName());
                IoUtil.copyStream(new FileInputStream(file), response.getOutputStream());
            } catch (IOException e) {
                LOGGER.error("文件流处理失败！异常为：", e);
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件不存在！");
            } catch (IOException e) {
                LOGGER.error("服务端错误！", e);
            }
        }
    }


}
