package server;

import resLoader.ImageLoader;
import resLoader.database.DataBase;

import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] args) {
        new Server().start();
    }
}
