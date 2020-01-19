package Server_Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket server;
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader reader;

    public static void main(String[] args) {
        try {

            server = new ServerSocket(5132);
            System.out.println("Сервер запущен");
            reader = new BufferedReader(new InputStreamReader(System.in));


            socket = server.accept();
            System.out.println("Клиент подключился");



            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String msgFromClient = in.readLine();
                            if (msgFromClient.equals("/end)")) break;
                            System.out.println(msgFromClient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String msgForClient = reader.readLine();
                            out.println(msgForClient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t2.start();

            try {
                t1.join();
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
