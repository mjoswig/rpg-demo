import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * A message box that displays lines of text
 * 
 * @author		Manuel Joswig
 * @copyright	2012 Manuel Joswig
 */
public class MessageBox {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 25;
	
	private static final Color BACKGROUND_COLOR = Color.white;
	private static final Color BORDER_COLOR = Color.black;
	private static final Color MESSAGE_COLOR = Color.black;
	
	private float x, y;
	private boolean isVisible;
	private int chunk;
	
	private static Sound clickSound;
	private String[] messages;
	
	public MessageBox(float x, float y, String[] messages) throws SlickException {
		this.x = x;
		this.y = y;
		this.messages = messages;
		
		chunk = 0;
		isVisible = false;
		clickSound = new Sound("res/audio/sounds/select.ogg");
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean value) {
		isVisible = value;
	}
	
	public void nextChunk() throws SlickException {
		if (isVisible) clickSound.play();
		
		if (chunk + 1 < messages.length) chunk++;
		else isVisible = false;
	}
	
	public void render(Graphics g) {
		if (isVisible) {
			g.setColor(BORDER_COLOR);
			g.drawRect(x - 1, y - 1, WIDTH + 1, HEIGHT + 1);
			
			g.setColor(BACKGROUND_COLOR);
			g.fillRect(x, y, WIDTH, HEIGHT);
			
			g.setColor(MESSAGE_COLOR);
			g.drawString(messages[chunk], x + 5, y + 3);
		}
	}
}
