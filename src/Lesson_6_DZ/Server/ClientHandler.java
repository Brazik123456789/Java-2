package Lesson_6_DZ.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private ServerMain server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(ServerMain server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readUTF();
                            if (str.equals("/end")){
                                out.writeUTF("ClientClosed");
                                break;
                            }
                            System.out.println("Client" + str);
                            server.broadcastMsg(str);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(String str){
        try {
            out.writeUTF("Кто-то сказал " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
