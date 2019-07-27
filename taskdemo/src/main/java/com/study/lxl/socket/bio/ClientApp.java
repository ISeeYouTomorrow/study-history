package com.study.lxl.socket.bio;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;

/**
 * @ClassName ClientApp
 * @Author @lvxile
 * @Date 2019/7/25 19:09
 * @Description TODO
 */
public class ClientApp {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8000);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        int i=0;
        while (i++ < 100) {
            System.out.println("send msg 'num"+i+"'");
            writer.write("num "+i+"\r\n");
            writer.flush();
        }
        writer.write("DONE");

        writer.close();
        socket.close();
    }
}
