package student.examples.ggengine.game;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;

@Getter
public class MultiplayerTeamGame extends Game implements HasTeams, HasPlayers{
	
	private Set<Participant> participants;
	private Map<String, Team> teams;
	
	public MultiplayerTeamGame(UUID id, Set<Item> items, Map<String, Team> teams, 
			Set<Participant> participants, GameState gameState) {
		
		super(id, items, gameState);
		this.participants = participants;
		this.teams = teams;
	}

	@Override
	public Map<String, Team> getTeams() {
		return this.teams;
	}

	@Override
	public Set<Participant> getPlayers() {
		return this.participants;
	}
	
	
	
	

}
