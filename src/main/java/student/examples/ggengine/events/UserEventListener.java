package student.examples.ggengine.events;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.factory.ParticipantFactory;
import student.examples.ggengine.game.Participant;
import student.examples.ggengine.game.ServerParticipantSet;
import student.examples.ggengine.game.UserStatus;
import student.examples.ggengine.services.GameService;

@Component
@Slf4j
public class UserEventListener implements ApplicationListener<UserEvent> {
	
	public static final String HARDCODED_USER_NAME = "John Doe";
	
	@Autowired
	private ParticipantFactory participantFactory;
	@Autowired
	private GameService gameService;
	

	@Override
	public void onApplicationEvent(UserEvent event) {
		
		ServerParticipantSet serverParticipantSet = gameService.getAllParticipants();
		Participant participant;
		
		if (event.getUserStatus() == UserStatus.SIGNIN) {
			UUID id = UUID.randomUUID();
			participant = participantFactory.createParticipant(id, HARDCODED_USER_NAME);
			serverParticipantSet.add(participant);
			
		} else {
			participant = gameService.getAllParticipants().getById(event.getUserId());
			if (participant != null) {
				serverParticipantSet.delete(participant);
			}
		}
		
		log.info("Received user event: " + event.getUserStatus() + " UUID: " + event.getUserId());
		log.info("All server participants: ");
		for (Participant part : serverParticipantSet.getAllParticipants()) {
			log.info(part.toString());
		}
	}
	
	

}
