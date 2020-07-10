package resLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private static ImageLoader imageLoader = new ImageLoader();
    public static ImageLoader getInstance(){
        return imageLoader;
    }
    private ImageLoader(){};

    public BufferedImage loadImage(String imageFileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./src/main/resources/" + imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}
