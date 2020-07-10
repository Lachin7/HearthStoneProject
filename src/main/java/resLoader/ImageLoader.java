package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResLoader {

    private static ResLoader resLoader = new ResLoader();
    // TODO make constructor private
    public static ResLoader getInstance(){
        return resLoader;
    }

    public BufferedImage imageLoader(String imageFileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./src/main/resources/" + imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}
