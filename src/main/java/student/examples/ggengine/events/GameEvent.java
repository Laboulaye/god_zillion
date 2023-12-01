package student.examples.ggengine.events;


import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import student.examples.ggengine.game.GameState;

@Getter
public class GameEvent extends ApplicationEvent{
    private GameState gameState;

    public GameEvent(Object source, GameState gameState) {
    	super(source);
        this.gameState = gameState;
    }
    
}
