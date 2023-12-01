package student.examples.ggengine.events;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import student.examples.ggengine.game.UserStatus;

@Getter
public class UserEvent  extends ApplicationEvent{
	
	private UserStatus userStatus;
	private UUID userId;
	
	public UserEvent(Object source, String userId, UserStatus userStatus) {
		super(source);
		this.userId = userId != null ? UUID.fromString(userId) : null;
		this.userStatus = userStatus;
	}
	

	
	
	

}
