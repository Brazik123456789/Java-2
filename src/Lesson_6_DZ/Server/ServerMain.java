package Lesson_6_DZ.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerMain {
    private Vector<ClientHandler> clients;

    public ServerMain() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
//            String str = AuthService.getNickByLoginAndPass("login1", "pass1");
//            System.out.println(str);
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
               // clients.add(new ClientHandler(this, socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
            AuthService.disconnect();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }


    //метод отправки соощения определенному клиенту
    public void broadcastMsgFromNick(String nickOtpravitelya, String nick, String msg) {
       for (ClientHandler o: clients) {
            if (o.nick.equals(nick)){
                o.sendMsg(nickOtpravitelya + ": " + msg);
                break;
            }
        }
    }

    //метод проверки клиента на присутствие в чате
    public boolean clientExists (String nick){
        for (ClientHandler o: clients) {
            if (o.nick.equals(nick)) return true;
        }
        return false;
    }




}
