
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
public class RemoteScreen implements ICaptureScreen {

    User u;

    public RemoteScreen(User u) throws IOException {
        this.u = u;
    }

    @Override
    public BufferedImage CaptureScreen() {
        try {
            BufferedImage bi = null;
            u.out.writeObject("CAPTURE");
            u.out.flush();
            try {
                BufferedSerializable bsi = (BufferedSerializable) u.in.readObject();
                bi = bsi.getBufferedImage();
                return bi;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RemoteScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(RemoteScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //@Override
    @Override
    public BufferedImage CaptureScreen(int width, int height) {
        BufferedImage img = CaptureScreen();
        BufferedImage rimg = new BufferedImage(width, height, img.getType());
        Graphics2D g = rimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, width, height, 0, 0, img.getWidth(), img.getHeight(), null);
        g.dispose();
        return rimg;
    }

    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}
