package student.examples.ggengine.events;


import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.game.UserStatus;

@Getter
public class UserEvent  extends ApplicationEvent{
	
	private UserStatus userStatus;
	private User user;
	
	public UserEvent(Object source, User user, UserStatus userStatus) {
		super(source);
		this.user = user;
		this.userStatus = userStatus;
	}
	

	
	
	

}
