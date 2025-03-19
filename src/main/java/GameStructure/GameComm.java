package GameStructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class GameComm implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String gameID;
    private final TeamManagement teamManagement;
    private final GameDynamics gameDynamics;

    public GameComm(@JsonProperty("gameID") String gameID,
                    @JsonProperty("teamManagement") TeamManagement teamManagement,
                    @JsonProperty("gameDynamics") GameDynamics gameDynamics) {
        this.gameID = gameID;
        this.teamManagement = teamManagement;
        this.gameDynamics = gameDynamics;
    }

    public String getGameID() {
        return gameID;
    }

    public TeamManagement getTeamManagement() {
        return teamManagement;
    }

    public GameDynamics getGameDynamics() {
        return gameDynamics;
    }
}
