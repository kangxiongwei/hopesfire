package com.kxw.hopesfire.basic.util;

import java.io.*;
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
            closeChannel(inputChannel);
            closeChannel(outputChannel);
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream) {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024];
        try {
            int i = bis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException("拷贝数据流时异常！");
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
    }

    public static void closeChannel(FileChannel channel) {
        if (channel == null) {
            return;
        }
        try {
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException("拷贝文件关闭数据流时异常！");
        }
    }

    public static void closeStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭输入流异常！");
            }
        }
    }

    public static void closeStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭输出流异常！");
            }
        }
    }

}
