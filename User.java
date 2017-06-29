/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Bhaskar
 */
public class User {

    String name;
    Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;

    public User(Socket s) throws IOException {
        socket = s;
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(s.getInputStream());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String msgType, Object msg) throws IOException {
        out.writeObject(msgType);
        out.flush();
        out.writeObject(msg);
        out.flush();

    }

}
