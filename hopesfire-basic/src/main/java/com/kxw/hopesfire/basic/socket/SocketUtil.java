package com.kxw.hopesfire.basic.socket;

import com.kxw.hopesfire.basic.util.ThreadPoolUtil;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * IO操作的工具类
 *
 * @author kangxiongwei
 * @date 2020-07-14 16:42
 */
public class SocketUtil {


    /**
     * 监听客户端，并将客户端传过来的数据返回
     *
     * @param serverSocket
     * @return
     */
    public static <T extends AbstractSocketThread> void bio(ServerSocket serverSocket, Class<T> task) throws Exception {
        if (serverSocket == null) {
            return;
        }
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                AbstractSocketThread thread = task.newInstance();
                thread.setSocket(socket);
                ThreadPoolUtil.commit(thread);
            }
        } finally {
            serverSocket.close();
        }
    }


}
