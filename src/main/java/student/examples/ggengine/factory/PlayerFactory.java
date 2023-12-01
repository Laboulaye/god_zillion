package student.examples.ggengine.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.game.Participant;
import student.examples.ggengine.game.Player;

@Component
@Slf4j
public class PlayerFactory implements ParticipantFactory {

	@Override
	public Participant createParticipant(UUID id, String name) {
		Player player = new Player(id, name);
		log.info("Created new player: " + player);
		return player;
	}
	
	

}
