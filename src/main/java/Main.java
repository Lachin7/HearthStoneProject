import gui.GameFrame;
import resLoader.ImageLoader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        GameFrame gameFrame = GameFrame.getInstance();
        ImageLoader.getInstance().loadCardImages();
        gameFrame.goToPanel("signInPanel");
        gameFrame.setVisible(true);

    }
}
