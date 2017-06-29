
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bhaskar
 */
class ServerThread extends Thread {

    Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;
    //static int width, height;
    BufferedImage bi;
    User u;
    String name;

    public ServerThread(Socket s) throws IOException, AWTException {

            //out = new ObjectOutputStream(s.getOutputStream());
        //in = new ObjectInputStream(s.getInputStream());
        u = new User(s);
        out = u.out;
        in = u.in;

    }

    @Override
    public void run() {

        while (true) {
            try {
                String msg = (String) u.in.readObject();
                 
                if (msg.equals("LOGIN")) {
                    name = (String) u.in.readObject();
                    u.setName(name);
                    ScreenCaptureServer.onlineUser.put(name, u);
                    if(ScreenCaptureServer.onlineUser.size()==1)
                        u.sendMessage("PRESENTER", u.name);
                    for (String username : ScreenCaptureServer.onlineUser.keySet()) {
                        if (username.equals(name)) {
                            for (String prevusers : ScreenCaptureServer.onlineUser.keySet()) {
                                u.sendMessage("LOGIN", prevusers);
                            }
                        } else {

                            User user = ScreenCaptureServer.onlineUser.get(username);
                            user.sendMessage("LOGIN", name);
                        }
                    }
                } else if (msg.equals("LOGOUT")) {
                    ScreenCaptureServer.onlineUser.remove(name);
                } else if (msg.equals("CAPTURE")) {
                    BufferedSerializable screen = (BufferedSerializable)u.in.readObject();
                    for (String username : ScreenCaptureServer.onlineUser.keySet()) {
                        if (!username.equals(name)) {
                            User user = ScreenCaptureServer.onlineUser.get(username);
                            user.sendMessage("CAPTURE", screen);
                        }
                    }
                    screen.freeBufferedSerializable();
                    /*
                     try {
                     // Capture Screenshot
                     bi = ls.CaptureScreen();

                     BufferedSerializable bs = new BufferedSerializable(bi);
                     out.writeObject(bs);
                     out.flush();

                     } catch (IOException ex) {
                     //Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                     }*/
                }
                else if(msg.equals("PRESENTER")){
                    String username=(String)u.in.readObject();
                    User user = ScreenCaptureServer.onlineUser.get(username);
                    user.sendMessage("PRESENTER", user.name);
                    
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                if (u == null || u.name == null) {
                    break;
                }
                ScreenCaptureServer.onlineUser.remove(u.name);
                for (String username : ScreenCaptureServer.onlineUser.keySet()) {
                    User user = ScreenCaptureServer.onlineUser.get(username);
                    try {
                        user.sendMessage("LOGOUT", u.name);
                    } catch (IOException ex1) {
                       // Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                }
                System.out.println("User " + u.name + " went offline.");
                break;

            }
        }
    }

}
