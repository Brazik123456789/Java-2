package Lesson_6_DZ.DZ;

import javafx.scene.transform.Scale;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args)  {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(5553);
            System.out.println("Сервер запущен!");


            socket = server.accept();
            System.out.println("Клиент присоединиился");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Thread inStr = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true){
                        String str = null;
                        try {
                            str = in.readLine();
                            if (str.equals("/end")) break;
                            System.out.println(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            inStr.start();

                Thread outStr = new Thread (new Runnable() {
                    @Override
                    public void run() {

                            while (true){
                                String str = null;
                                try {
                                    str = reader.readLine();
                                    out.println(str);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    }
                });
                outStr.setDaemon(true);
                outStr.start();

            try {
                inStr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}