package student.examples.ggengine.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import student.examples.ggengine.game.GameState;
import student.examples.ggengine.game.UserStatus;

@Component
public class EventPublisher {
	
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishStartGameEvent() {
        GameEvent startGameEvent = new GameEvent(this, GameState.PENDING);
        applicationEventPublisher.publishEvent(startGameEvent);
    }
    
    public void publishUserEvent(String uuid, UserStatus userStatus) {
    	UserEvent userEvent = new UserEvent(this, uuid, userStatus);
        applicationEventPublisher.publishEvent(userEvent);
    }
}
