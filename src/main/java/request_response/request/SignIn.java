package request_response.request;

import server.models.Player;
import server.ClientHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignIn extends Request {

    private String name, pass;

    public SignIn(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        String message = "";
        Player player = clientHandler.getServer().getDataBase().fetch(Player.class, name);
        if (player == null) message = "Error! There isn't an account in this name , Try again..";
        else if (player.getPassword().equals(pass)) {
            player.setSignedUp(true);
            clientHandler.setMainPlayer(player);
//            LogManager.getLogManager().reset();
//            FileHandler fileHandler = null;
//            try {
//                fileHandler = new FileHandler("./src/main/java/logs/" + player.getName() + "-" + player.getID() + ".log", true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            fileHandler.setFormatter(new SimpleFormatter());
//            clientHandler.getPlayerLOGGER().addHandler(fileHandler);
//            clientHandler.getPlayerLOGGER().info("USER  : " + player.getName() + "\nSigned_In AT :" + new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + player.getPassword() + "\n");
            clientHandler.log("USER  : " + player.getName() + "\nSigned_In AT :" + new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + player.getPassword() + "\n");
            message = "you are signed up successfully! BEGIN YOUR JOURNEY IN HEARTH STONE!!";

        } else message = "Error! Wrong password , Try again..";
        clientHandler.sendResponse("SignIn", new request_response.response.SignIn(message));
    }
}
