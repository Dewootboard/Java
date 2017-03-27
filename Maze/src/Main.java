import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static javax.print.attribute.standard.MediaSizeName.C;

/**
 * Created by jocke on 3/27/2017.
 */
public class Main{
    public static void Main(String[] args){

        try {
            LoadIm image = new LoadIm();
            image.getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
