package Lesson_6_DZ.DZ;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static BufferedReader reader;
    private static PrintWriter out;
    private static BufferedReader in;

    private static String ip = "localhost";
    private static int port = 5553;


    public static void main(String[] args) {

        try {
            socket = new Socket(ip,port);
            reader = new BufferedReader(new InputStreamReader(System.in));

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

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
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();




        } catch (IOException e) {
            e.printStackTrace();
        }







    }




}
