package student.examples.ggengine.game;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Participant {
	
	private UUID id;
	private String name;

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + "]";
	}
	
	
	
	

}
