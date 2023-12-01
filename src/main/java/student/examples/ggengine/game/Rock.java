package student.examples.ggengine.game;

import lombok.Getter;

@Getter
public final class Rock extends Item{
	
	public Rock(int width, int height, int speedX, int speedY, int top, int left, int rotation, int rotationSpeed,
			ItemType itemType) {
		super(width, height, speedX, speedY, top, left, rotation,rotationSpeed, itemType);
	}

}
