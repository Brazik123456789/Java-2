package Server_Client;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static BufferedReader reader;

    private static String IP_Adress = "localhost";
    private static int port = 5132;


    public static void main(String[] args) {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(IP_Adress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String msgFromServer = in.readLine();
                            if (msgFromServer.equals("/end")) break;
                            System.out.println(msgFromServer);
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
                            String msgForServer = reader.readLine();
                            out.println(msgForServer);
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
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
