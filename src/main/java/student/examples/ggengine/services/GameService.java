package student.examples.ggengine.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import student.examples.ggengine.factory.GameFactory;
import student.examples.ggengine.factory.ParticipantFactory;
import student.examples.ggengine.game.Game;
import student.examples.ggengine.game.Item;
import student.examples.ggengine.game.MultiplayerTeamGame;
import student.examples.ggengine.game.Participant;
import student.examples.ggengine.game.Rock;
import student.examples.ggengine.game.ServerParticipantSet;
import student.examples.ggengine.game.Team;

@Getter
@Service
@Slf4j
public class GameService {

	private Set<Game> games;
	private ServerParticipantSet allParticipants;
	
	@Autowired
	private GameFactory gameFactory;
	
	@Autowired
	private ParticipantFactory participantFactory;
	
	
	public GameService() {
		init();
	}
	
	public void init() {
		games = new HashSet<>();
		allParticipants = new ServerParticipantSet();
	}
	
	
	public boolean joinGame(String uuid) {
		boolean result = false;
		UUID id = UUID.fromString(uuid);
		
		if (games.isEmpty()) {
			games.add(gameFactory.createGame());
		} 
		
		MultiplayerTeamGame game = (MultiplayerTeamGame) games.stream().findFirst().get();
		
		Participant player = allParticipants.getById(id);
		if (player != null) {
			Map<String, Team> teamMap = game.getTeams();
			Set<Participant> playersTeamA = teamMap.get("teamA").getParticipants();
			playersTeamA.add(player);
			
			log.info("Participants of team A: ");
			for (Participant participant: playersTeamA) {
				log.info(participant.toString());
			}
			result = true;
		} 
		
		return result;
	}
	
	public boolean leaveGame(String uuid) {
		UUID id = UUID.fromString(uuid);
		boolean result = false;
		
		Participant player = allParticipants.getById(id);
		if (player != null) {
			MultiplayerTeamGame game = (MultiplayerTeamGame) games.stream().findFirst().get();
			Map<String, Team> teamMap = game.getTeams();
			Set<Participant> playersTeamA = teamMap.get("teamA").getParticipants();
			playersTeamA.remove(player);
			game.getParticipants().remove(player);
			allParticipants.delete(player);
			
			log.info("Player: " + uuid + " left the game");
			
			log.info("Participants of team A: ");
			for (Participant participant: playersTeamA) {
				log.info(participant.toString());
			}
			result = true;
		} 
		
		return result;
		
	}
	
	@Scheduled(fixedDelay = 1000)
	public void updateFrame() {
		if (!games.stream().findFirst().isPresent()) {
			return;
		}
		Game game = games.stream().findFirst().get();
		
		Item item = game.getItems().stream().findFirst().get();
		game.getItems().remove(item);
		game.getItems().add(new Rock(
				item.getWidth(),
				item.getHeight(),
				item.getSpeedX(),
				item.getSpeedY(),
				item.getTop(),
				item.getLeft() + 1,
				item.getRotation(),
				item.getRotationSpeed(),
				item.getItemType()));
		
//		log.info("In updateFrame of the Game Service");
	}
	
	

	
}
