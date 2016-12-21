import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a non-player character
 * 
 * @author		Manuel Joswig
 * @copyright	2012 Manuel Joswig
 */
public class NPC extends Character {
	private int movementDirection;
	
	public NPC(String name, int x, int y) throws SlickException {
		super(name, x, y);
	}
	
	public void updateMovement(Map map, int delta) {
		int rand = getRandomInteger(0, 3500);
		int rand2 = getRandomInteger(0, 3500);

		if (rand == rand2) {
			movementDirection = getRandomInteger(0, 10);
			
			if (movementDirection >= 0 && movementDirection < 2) movementDirection = 0;
			else if (movementDirection >= 2 && movementDirection < 4) movementDirection = 1;
			else if (movementDirection >= 4 && movementDirection < 6) movementDirection = 2;
			else if (movementDirection >= 6 && movementDirection < 8) movementDirection = 3;
			else if (movementDirection >= 8 && movementDirection < 10) movementDirection = 4;
		}
		
		move(map, movementDirection, delta);
	}
	
	@Override
	protected Animation[] getMovementAnimations() throws SlickException {
		super.getMovementAnimations();
		
		// for non-player characters
		Image[] movementUp = {new Image("res/gfx/sprites2/up2.png"), new Image("res/gfx/sprites2/up1.png"), new Image("res/gfx/sprites2/up3.png")};
		Image[] movementDown = {new Image("res/gfx/sprites2/down2.png"), new Image("res/gfx/sprites2/down1.png"), new Image("res/gfx/sprites2/down3.png")};
		Image[] movementLeft = {new Image("res/gfx/sprites2/left2.png"), new Image("res/gfx/sprites2/left1.png")};
		Image[] movementRight = {new Image("res/gfx/sprites2/right2.png"), new Image("res/gfx/sprites2/right1.png")};
		
		// create animations
		Animation[] animations = {new Animation(movementUp, MOVEMENT_DURATION, false), 
								  new Animation(movementDown, MOVEMENT_DURATION, false),
								  new Animation(movementLeft, MOVEMENT_DURATION_2, false),
								  new Animation(movementRight, MOVEMENT_DURATION_2, false)};
		
		return animations;
	}
	
	private int getRandomInteger(int x, int y) {
		y++;
		return (int) (Math.random() * (y - x) + x);
	}
}
