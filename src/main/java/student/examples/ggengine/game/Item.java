package student.examples.ggengine.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item {
	
	protected int width;
	protected int height;
	protected int speedX;
	protected int speedY;
	protected int top;
	protected int left;
	protected int rotation;
	protected int rotationSpeed;
	//protect the type
	protected ItemType itemType;
	

	
}
