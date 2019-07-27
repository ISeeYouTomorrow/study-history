package com.study.lxl.socket.bio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * @ClassName ServerApp
 * @Author @lvxile
 * @Date 2019/7/25 19:00
 * @Description TODO
 */
public class ServerApp {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String request , response = "";
        while ((request = reader.readLine()) != null) {
            if ("DONE".equals(request)) {
                break;
            }
            response = process(request);
        }
        reader.close();
        socket.close();
        System.out.println("response = "+response);
    }

    private static String process(String request) {
        System.out.println(Calendar.getInstance().getTime().toString()+" "+request);
        return "success";
    }

}
