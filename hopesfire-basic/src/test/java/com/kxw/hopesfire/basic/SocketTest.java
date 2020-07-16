package com.kxw.hopesfire.basic;

import com.kxw.hopesfire.basic.socket.AbstractSocketThread;
import com.kxw.hopesfire.basic.socket.SocketUtil;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author kangxiongwei
 * @date 2020-07-14 17:19
 */
public class SocketTest {

    @Test
    public void test() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        SocketUtil.bio(serverSocket, SocketTestThread.class);
    }

    @Test
    public void test02() throws Exception {
        Socket socket = new Socket("localhost", 9999);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello world".getBytes());
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("得到返回：" + line);
        }
    }

    public static class SocketTestThread extends AbstractSocketThread {

        @Override
        public void execute(Socket socket) {
            System.out.println("获取到客户端，开始处理....");
            try {
                String read;
                while ((read = this.read()) != null) {
                    System.out.println("读取到数据：" + read);
                    this.write("server " + read);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
