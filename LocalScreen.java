
import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;


class LocalScreen implements ICaptureScreen {

 
    Robot r;
    Toolkit tkt;
    BufferedImage bi;
    int width,height;
  

    public LocalScreen() throws AWTException {
        this.width = width;
        this.height = height;
        r = new Robot();
        tkt = Toolkit.getDefaultToolkit();
    }

    @Override
    public BufferedImage CaptureScreen() {
        bi = r.createScreenCapture(new Rectangle(tkt.getScreenSize()));
        return bi;
    }

    @Override
    public BufferedImage CaptureScreen(int width, int height) {
        BufferedImage img = CaptureScreen();
        BufferedImage rimg = new BufferedImage(width, height, img.getType());
        Graphics2D g = rimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, width, height, 0, 0, img.getWidth(), img.getHeight(), null);
        g.dispose();

        return rimg;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
