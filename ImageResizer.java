
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhaskar
 */
public class ImageResizer {
    public static BufferedImage getResizedImage(int width, int height, BufferedImage src)
    {
        BufferedImage rimg = new BufferedImage(width, height, src.getType());
        Graphics2D g = rimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, width, height, 0, 0, src.getWidth(), src.getHeight(), null);
        g.dispose();
        
        return rimg;
    }
}
