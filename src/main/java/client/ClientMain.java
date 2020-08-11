package client;

import resLoader.ImageLoader;

public class ClientMain {
    public static void main(String[] args) {
        ImageLoader.getInstance().loadCardImages();
        new ClientGui().goToPanel("connection");
    }
}
