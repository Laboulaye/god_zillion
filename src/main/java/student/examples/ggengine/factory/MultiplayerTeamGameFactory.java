package student.examples.ggengine.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.events.EventPublisher;
import student.examples.ggengine.game.Game;
import student.examples.ggengine.game.GameState;
import student.examples.ggengine.game.Item;
import student.examples.ggengine.game.ItemType;
import student.examples.ggengine.game.MultiplayerTeamGame;
import student.examples.ggengine.game.Participant;
import student.examples.ggengine.game.Rock;
import student.examples.ggengine.game.Team;

@Component
@Slf4j
public class MultiplayerTeamGameFactory implements GameFactory {

	
	@Autowired
	private EventPublisher eventPublisher;
	
	
	
	@Override
	public Game createGame() {
		UUID uuid = UUID.randomUUID();
		
		Set<Item> items = new HashSet<>();
		items.add(new Rock(0, 0, 0, 0, 0, 0, 0, 0, ItemType.ROCK));
		
		
		Set<Participant> teamAPlayers = new HashSet<>();
		Team teamA = new Team(teamAPlayers);
		Set<Participant> teamBPlayers = new HashSet<>();
		Team teamB = new Team(teamBPlayers);
		Map<String, Team> teamMap = new HashMap<>();
		teamMap.put("teamA", teamA);
		teamMap.put("teamB", teamB);
		

		Set<Participant> participants = new HashSet<Participant>();
		
		Game game = new MultiplayerTeamGame(uuid, items, teamMap, participants, GameState.PENDING);
		
		eventPublisher.publishStartGameEvent();
		log.info("Multiplayer Team Game Created");
		
		return game;
	}
	
	

}
