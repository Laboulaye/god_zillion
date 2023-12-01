package student.examples.ggengine.game;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ServerParticipantSet implements ParticipantCollection {
	
	private Set<Participant> allParticipants = new HashSet<>();

	
	@Override
	public void add(Participant participant) {
		allParticipants.add(participant);
		
	}

	@Override
	public void delete(Participant participant) {
		allParticipants.remove(participant);
		
	}
	
	public Participant getById(UUID id) {
		return allParticipants.stream()
        .filter(participant -> participant instanceof Player)
        .map(participant -> (Player) participant)
        .filter(player -> player.getId().equals(id))
        .findFirst()
        .orElse(null);
		
	}
	
	

}
