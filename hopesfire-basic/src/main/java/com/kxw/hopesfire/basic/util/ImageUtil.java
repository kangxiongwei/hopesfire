package com.kxw.hopesfire.basic.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * 图片处理工具
 *
 * @author kangxiongwei
 * @date 2020/9/11 10:22 下午
 */
public class ImageUtil {

    /**
     * 按照宽度和高度进行图片压缩
     *
     * @param image
     * @param width
     * @param height
     */
    public static File compress(File image, int width, int height) {
        if (image == null || !image.exists()) {
            return null;
        }
        try {
            String parent = image.getParent();
            String fileName = String.format("%s_%s_%s_%s", "thumbnail", width, height, image.getName());
            File destFile = FileUtil.createFile(parent + File.separator + "thumbnails", fileName);
            Thumbnails.of(image).size(width, height).toFile(destFile);
            return destFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照比例压缩
     *
     * @param image
     * @param scale
     * @return
     */
    public static File compress(File image, float scale) {
        if (image == null || !image.exists()) {
            return null;
        }
        try {
            String parent = image.getParent();
            String fileName = String.format("%s_%s_%s", "thumbnail", scale, image.getName());
            File destFile = FileUtil.createFile(parent + File.separator + "thumbnails", fileName);
            Thumbnails.of(image).scale(scale).toFile(destFile);
            return destFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception{
        File file = new File("/opt/apps/hopesfire/attaches/home/1597055249855_login-bg.jpg");
        URI uri = file.toURI();
        System.out.println(file.getPath());

    }

}
