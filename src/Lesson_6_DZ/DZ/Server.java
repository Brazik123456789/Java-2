package Lesson_6_DZ.DZ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
        private static Socket socket;
        private static ServerSocket server;
        private static BufferedReader in;
        private static PrintWriter out;
        private static BufferedReader reader;


    public static void main(String[] args)  {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            server = new ServerSocket(5553);
            System.out.println("Сервер запущен!");


                socket = server.accept();
                System.out.println("Клиент присоединиился");

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true){
                                String str = in.readLine();
                                System.out.println(str);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true){
                                String str = reader.readLine();
                                out.println(str);
                                System.out.println(str);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}