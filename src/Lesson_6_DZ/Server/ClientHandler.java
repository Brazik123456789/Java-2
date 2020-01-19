package Lesson_6_DZ.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerMain server;
    public  String nick;

    public ClientHandler(ServerMain server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] tokes = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokes[1], tokes[2]);

                                if (newNick != null) {
                                    if (!server.clientExists(newNick)){                     // условие проверки наличия клиента в чате
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else
                                        sendMsg("Такой пользователь есть в чате, вы не можете войти");

                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            System.out.println("Client: " + str);

                           if (str.startsWith("/w")){                                          //проверка входящего сообщения, если оно начинается на /w мы создаем массив, где второй элемент это ник, а третий - сообщение
                                String[] nickAndMsg = str.split(" ", 3);                   // далее вызывает у сервера метод отправки сообщения определенному клиенту, поставил лимит на 3 элемента массива, чтобы сообщение не обрезалось
                                server.broadcastMsgFromNick(nick, nickAndMsg[1], nickAndMsg[2]);
                            }else
                                server.broadcastMsg(nick + ": " + str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
