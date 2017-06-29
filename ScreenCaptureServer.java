
import java.awt.AWTException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhaskar
 */
public class ScreenCaptureServer {
   ServerSocket ss;
   int port;
   Socket s;
    public static HashMap<String , User> onlineUser=new HashMap<>();
   public ScreenCaptureServer(int port){
       
       try {
           this.port=port;
           ss=new ServerSocket(port);
       } catch (IOException ex) {
           Logger.getLogger(ScreenCaptureServer.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   public void run() throws AWTException
   {
       while(true){
           try {
               System.out.println("waiting for Client....");
               s=ss.accept();
               System.out.println("client connected"+onlineUser.size());
               ServerThread st = new ServerThread(s);
               st.start();
               
           } catch (IOException ex) {
               Logger.getLogger(ScreenCaptureServer.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
   }
   public static void main(String args[]) throws AWTException{
       ScreenCaptureServer scr=new ScreenCaptureServer(9000);
       scr.run();
   }
   
    
}

