import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Simple RPG demo using Slick2D (based on Pokémon)
 * 
 * @author		Manuel Joswig
 * @copyright	2012 Manuel Joswig
 */
public class RPGDemo extends BasicGame {
	private static final int NPC_OBJECTS = 5;
	private static final String[] GLOBAL_MESSAGES = {"Ein Beispiel für eine primitive Message Box...", 
											  		 "Das Grundsystem funktioniert schon ganz gut...",
											  		 "Aber an den Feinheiten muss noch gearbeitet werden."};
	
	private Map map;
	private MessageBox msgBox;
	private Character player;
	
	private NPC[] npcObjects = new NPC[NPC_OBJECTS];
	
	public RPGDemo() {
		super("RPG Demo");
	}
	
	public void init(GameContainer container) throws SlickException {
		// play background music
		Music bgMusic = new Music("res/audio/background.ogg", true);
		bgMusic.loop();
		
		// load tile map
		map = new Map("res/maps/map1.tmx");
		
		// global message box
		msgBox = new MessageBox(5, 10, GLOBAL_MESSAGES);
		msgBox.setVisible(true);
		
		// create player
		player = new Character("Manuel", 250, 150);
		
		// create some npcs
		for (int i = 0; i < NPC_OBJECTS; i++) {
			npcObjects[i] = new NPC("NPC" + (i + 1), 50 + i * 97, 100);
		}
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if (input.isKeyPressed((Input.KEY_SPACE))) {
			msgBox.nextChunk();
		}
		else if (!msgBox.isVisible()) {
			if (input.isKeyDown(Input.KEY_UP)) {
				player.move(map, 1, delta);
			}
			else if (input.isKeyDown(Input.KEY_DOWN)) {
				player.move(map, 2, delta);
			}
			else if (input.isKeyDown(Input.KEY_LEFT)) {
				player.move(map, 3, delta);
			}
			else if (input.isKeyDown(Input.KEY_RIGHT)) {
				player.move(map, 4, delta);
			}
			else {
				player.move(map, 0, delta);
			}
			
			for (int i = 0; i <= npcObjects.length - 1; i++) {
				npcObjects[i].updateMovement(map, delta);
			}
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.render(0, 0);
		msgBox.render(g);
		player.draw();
		
		for (int i = 0; i <= npcObjects.length - 1; i++) {
			npcObjects[i].draw();
		}
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new RPGDemo());
			app.setIcon("res/gfx/icon.png");
			app.setDisplayMode(512, 320, false);
			app.setShowFPS(false);
			app.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
}