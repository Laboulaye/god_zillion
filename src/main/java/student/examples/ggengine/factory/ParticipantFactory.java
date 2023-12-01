package student.examples.ggengine.factory;

import java.util.UUID;

import student.examples.ggengine.game.Participant;

public interface ParticipantFactory {
	
	Participant createParticipant(UUID id, String name);

}
