
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhaskar
 */
public interface ICaptureScreen {
    BufferedImage CaptureScreen();
    BufferedImage CaptureScreen(int width,int height);
    //BufferedImage captureRemoteScreen(int width,int height);    
}
