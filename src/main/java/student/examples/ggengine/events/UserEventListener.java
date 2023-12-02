package student.examples.ggengine.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.domain.entity.User;
import student.examples.ggengine.factory.ParticipantFactory;
import student.examples.ggengine.game.Participant;
import student.examples.ggengine.game.ServerParticipantSet;
import student.examples.ggengine.game.UserStatus;
import student.examples.ggengine.services.GameService;

@Component
@Slf4j
public class UserEventListener implements ApplicationListener<UserEvent> {
	
	
	@Autowired
	private ParticipantFactory participantFactory;
	@Autowired
	private GameService gameService;
	

	@Override
	public void onApplicationEvent(UserEvent event) {
		
		ServerParticipantSet serverParticipantSet = gameService.getAllParticipants();
		Participant participant;
		
		User user = event.getUser();
		log.info("Received user event: " + event.getUserStatus() + ", UUID: " + user.getId());
		
		if (event.getUserStatus() == UserStatus.SIGNIN) {
			participant = participantFactory.createParticipant(user.getId(), user.getUserName());
			serverParticipantSet.add(participant);
			
		} else {
			participant = gameService.getAllParticipants().getById(user.getId());
			if (participant != null) {
				serverParticipantSet.delete(participant);
			}
		}
		
		log.info("All server participants: ");
		for (Participant part : serverParticipantSet.getAllParticipants()) {
			log.info(part.toString());
		}
	}
	
	

}
