
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bhaskar
 */
public class BufferedSerializable implements Serializable {

    transient BufferedImage bi;

    public BufferedSerializable(BufferedImage bi) {
        this.bi = bi;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(bi, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        bi = ImageIO.read(in);
    }

    public BufferedImage getBufferedImage() {
        return bi;
    }
    
    public void freeBufferedSerializable()
    {
        bi.getGraphics().dispose();
        bi = null;
    }
}
