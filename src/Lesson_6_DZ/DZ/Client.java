package Lesson_6_DZ.DZ;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static String ip = "localhost";
    private static int port = 5553;


    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket(ip,port);


            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner reader = new Scanner(System.in);

            Thread inStr = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true){
                        String str = in.nextLine();
                        if (str.equals("/end")) break;
                        System.out.println(str);
                    }
                }
            });
            inStr.start();

            Thread outStr = new Thread (new Runnable() {
                @Override
                public void run() {

                    while (true){
                        String str = reader.nextLine();
                        out.println(str);
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
        }
    }
}
