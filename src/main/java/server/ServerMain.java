package server;

import resLoader.ImageLoader;

public class ServerMain {
    public static void main(String[] args) {
        ImageLoader.getInstance().loadCardImages();
        new Server().start();
    }
}
