package resLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;

public class ImageLoader {

    private static final ImageLoader imageLoader = new ImageLoader();
    public static ImageLoader getInstance(){
        return imageLoader;
    }
    private ImageLoader(){
        cardsImages = new HashMap<>();
    }

    private final HashMap<String,BufferedImage> cardsImages;


    public void loadCardImages(){
        File AllCards = new File("./src/main/resources/Cards");
        File[] CardFiles = AllCards.listFiles();
        for(File file : CardFiles){
            String fileName = file.getName();
            BufferedImage image = loadImage("Cards/"+fileName);
            cardsImages.put(fileName.substring(0,fileName.length()-4),image);
        }
    }

    public BufferedImage loadImage(String imageFileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./src/main/resources/" + imageFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public HashMap<String, BufferedImage> getCardsImages() {
        return cardsImages;
    }
}
