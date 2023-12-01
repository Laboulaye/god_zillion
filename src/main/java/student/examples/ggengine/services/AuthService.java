package student.examples.ggengine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.examples.ggengine.events.EventPublisher;
import student.examples.ggengine.game.UserStatus;

@Service
public class AuthService {
	
	public static final String USER_NAME = "user";
	public static final String USER_PASSWORD = "user";
	
	@Autowired
	private EventPublisher gameEventPublisher;
	
	
	public void signIn(String userName, String password) {
		if (userName.equals(USER_NAME) && password.equals(USER_PASSWORD)) {
			gameEventPublisher.publishUserEvent(null, UserStatus.SIGNIN);
		} 
	}
	
	public void signOut(String uuid) {
		gameEventPublisher.publishUserEvent(uuid, UserStatus.SIGNOUT);
	}

}
