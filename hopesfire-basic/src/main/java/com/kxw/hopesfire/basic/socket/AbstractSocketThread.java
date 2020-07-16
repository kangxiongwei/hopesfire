package com.kxw.hopesfire.basic.socket;

import java.io.*;
import java.net.Socket;

/**
 * 抽象的Socket线程处理方法
 *
 * @author kangxiongwei
 * @date 2020-07-14 17:07
 */
public abstract class AbstractSocketThread implements Runnable {

    private Socket socket;

    /**
     * 执行自己的具体逻辑
     *
     * @param socket
     */
    public abstract void execute(Socket socket);

    /**
     * 从网络中读取数据
     *
     * @return
     */
    public synchronized String read() throws Exception {
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }

    /**
     * 往网络中写入数据
     *
     * @param data
     */
    public synchronized void write(String data) throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(data);
        writer.flush();
        outputStream.flush();
    }

    @Override
    public void run() {
        this.execute(socket);
    }

    void setSocket(Socket socket) {
        this.socket = socket;
    }

}
