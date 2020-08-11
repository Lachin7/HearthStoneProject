package request_response.response;

import client.ClientGui;
import server.models.Cards.Target;

import java.util.ArrayList;

public class InitialMoveTargeting extends Response{
    private ArrayList<Target> targets;
    private long id;

    public InitialMoveTargeting(ArrayList<Target> targets, long id) {
        this.targets = targets;
        this.id = id;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().initialMoveTargeting(targets,id);
    }
}
