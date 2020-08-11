package server;

import resLoader.ImageLoader;
import resLoader.database.DataBase;

import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] args) {
//        try {
//            DataBase.isValid( 1);
//        }
//        catch (SQLException e){
//
//        }
//        ;
        new Server().start();
    }
}
