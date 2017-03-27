import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by jocke on 3/27/2017.
 */
public class LoadIm {
    private ArrayList<Node> nodes = new ArrayList<>();
    private BufferedImage image = null;
    private int white = Color.WHITE.getRGB();
    private int black = Color.WHITE.getRGB();

    public void LoadIm() throws Exception {

    }

    public void getImage() throws Exception {
        image = ImageIO.read(new File("C:/Users/jocke/Desktop/maze.png"));


        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (image.getRGB(i, j) == white) {
                    if (isCrossing(i, j)) {
                        nodes.add(new Node(i,j));
                    }
                }
            }
        }

    }

    private boolean isCrossing(int x, int y){
        if((image.getRGB(x - 1, y) == white && image.getRGB(x + 1, y) == white)){
            return !(image.getRGB(x, y-1) == black && image.getRGB(x, y+1) == black);
        }
        if((image.getRGB(x - 1, y) == black && image.getRGB(x + 1, y) == black)){
            return !(image.getRGB(x, y-1) == white && image.getRGB(x, y+1) == white);
        }
        return false;
    }

    private void traverseNodes(Node current){
        if(image.getRGB(current.getX()+1, current.getY()) == white){

        }
    }
}
