package student.examples.ggengine.game;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Team implements ParticipantCollection{
	
	private Set<Participant> participants;

	@Override
	public void add(Participant participant) {
		participants.add(participant);
		
	}

	@Override
	public void delete(Participant participant) {
		participants.remove(participant);
		
	}
	
	

}
