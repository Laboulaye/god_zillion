package student.examples.ggengine.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import student.examples.ggengine.game.Game;
import student.examples.ggengine.services.GameService;

@Component
public class GameEventListener implements ApplicationListener<GameEvent> {
	
//	@Autowired
//	private GameService gameService;
	
	
    @Override
    public void onApplicationEvent(GameEvent event) {
        System.out.println("Received game event - " + event.getGameState());
//        gameService.addGame();
    }
    
    
}
