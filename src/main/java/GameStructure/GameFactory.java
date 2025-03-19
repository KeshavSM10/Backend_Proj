package GameStructure;

import org.springframework.stereotype.Component;

import services.GameDynamics;
import services.TeamManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Component
public class GameFactory {

    @Autowired
    private ApplicationContext context;

    public GameComm createGame(String gameID) {
        return new GameComm(gameID, context.getBean(TeamManagement.class), context.getBean(GameDynamics.class));
    }
}
