package com.kxw.hopesfire.basic.util;

import java.io.File;

/**
 * @author kangxiongwei
 * @date 2020/9/3 4:19 下午
 */
public class FileUtil {

    /**
     * 创建文件
     *
     * @param path
     * @param name
     * @return
     */
    public static File createFile(String path, String name) {
        File destFile = new File(path, name);
        if (!destFile.getParentFile().exists()) {
            boolean dirs = destFile.getParentFile().mkdirs();
            if (!dirs) {
                throw new RuntimeException("创建父文件夹失败！");
            }
        }
        return destFile;
    }

}
