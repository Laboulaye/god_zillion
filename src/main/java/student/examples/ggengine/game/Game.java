package student.examples.ggengine.game;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Game {
	
	UUID id;
	
	Set<Item> items;
	
	GameState gameState = GameState.PENDING;

	
}
