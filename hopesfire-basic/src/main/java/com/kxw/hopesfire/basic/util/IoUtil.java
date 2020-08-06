package com.kxw.hopesfire.basic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author kangxiongwei
 * @date 2020/8/6 8:19 下午
 */
public class IoUtil {

    /**
     * 拷贝文件
     *
     * @param source
     * @param target
     */
    public static void copyFile(File source, File target) {
        if (source == null || target == null) {
            return;
        }
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(target).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {
            throw new RuntimeException("拷贝文件数据流处理时异常！");
        } finally {
            try {
                if (inputChannel != null) {
                    inputChannel.close();
                }
                if (outputChannel != null) {
                    outputChannel.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("拷贝文件关闭数据流时异常！");
            }
        }
    }

}
