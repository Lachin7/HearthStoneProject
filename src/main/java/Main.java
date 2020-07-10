import gui.GameFrame;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.goToPanel("signInPanel");
        gameFrame.setVisible(true);

    }
}
