import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a player
 * 
 * @author		Manuel Joswig
 * @copyright	2012 Manuel Joswig
 */
public class Character {
	private static final double MOVEMENT_SPEED = 0.075;
	
	protected static final int[] MOVEMENT_DURATION = {175, 175, 175};
	protected static final int[] MOVEMENT_DURATION_2 = {175, 175};
	
	private String playerName;
	private Animation sprite;
	private Animation[] movementAnimations;
	
	private double posX, posY;
	
	protected int[] movementDuration = {175, 175, 175};
	protected int[] movementDuration2 = {175, 175};
	
	public Character(String name, double x, double y) throws SlickException {
		playerName = name;
		
		// load animations for movement
		movementAnimations = getMovementAnimations();
		
		// character will look down (start direction)
		sprite = movementAnimations[1];
		sprite.setCurrentFrame(1);
		
		// player coordinates (start position)
		posX = x;
		posY = y;
	}
	
	public void move(Map map, int direction, int delta) {
		// map.setBlocked(posX, posY, false);
		
		switch (direction) {
			// motionless
			case 0:
				sprite.setCurrentFrame(1);
				break;
				
			// move up
			case 1:
				sprite = movementAnimations[0];
				
				if (!map.isBlocked(posX, posY - delta * MOVEMENT_SPEED)) {
					sprite.update(delta);
					posY -= delta * MOVEMENT_SPEED;
				}
				else {
					sprite.setCurrentFrame(1);
				}
				
				break;
			
			// move down
			case 2:
				sprite = movementAnimations[1];
				
				if (!map.isBlocked(posX, posY + Map.TILE_SIZE + delta * MOVEMENT_SPEED)) {
					sprite.update(delta);
					posY += delta * MOVEMENT_SPEED;
				}
				else {
					sprite.setCurrentFrame(1);
				}
				
				break;
			
			// move left
			case 3:
				sprite = movementAnimations[2];
				
				if (!map.isBlocked(posX - delta * MOVEMENT_SPEED, posY)) {
					sprite.update(delta);
					posX -= delta * MOVEMENT_SPEED;
				}
				else {
					sprite.setCurrentFrame(1);
				}
				
				break;
			
			// move right
			case 4:
				sprite = movementAnimations[3];
				
				if (!map.isBlocked(posX + Map.TILE_SIZE + delta * MOVEMENT_SPEED, posY)) {
					sprite.update(delta);
					posX += delta * MOVEMENT_SPEED;
				}
				else {
					sprite.setCurrentFrame(1);
				}
				
				break;
		}
		
		// map.setBlocked(posX, posY, true);
	}
	
	public String getName() {
		return playerName;
	}
	
	public double getX() {
		return posX;
	}
	
	public double getY() {
		return posY;
	}
	
	public void draw() {
		sprite.draw((int) posX, (int) posY);
	}
	
	protected Animation[] getMovementAnimations() throws SlickException {
		// load default movement sprites
		Image[] movementUp = {new Image("res/gfx/sprites1/up2.png"), new Image("res/gfx/sprites1/up1.png"), new Image("res/gfx/sprites1/up3.png")};
		Image[] movementDown = {new Image("res/gfx/sprites1/down2.png"), new Image("res/gfx/sprites1/down1.png"), new Image("res/gfx/sprites1/down3.png")};
		Image[] movementLeft = {new Image("res/gfx/sprites1/left2.png"), new Image("res/gfx/sprites1/left1.png")};
		Image[] movementRight = {new Image("res/gfx/sprites1/right2.png"), new Image("res/gfx/sprites1/right1.png")};
		
		// create animations
		Animation[] animations = {new Animation(movementUp, MOVEMENT_DURATION, false), 
								  new Animation(movementDown, MOVEMENT_DURATION, false),
								  new Animation(movementLeft, MOVEMENT_DURATION_2, false),
								  new Animation(movementRight, MOVEMENT_DURATION_2, false)};
		
		return animations;
	}
}