import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.SlickException;

/**
 * Represents a map
 * 
 * @author		Manuel Joswig
 * @copyright	2012 Manuel Joswig
 */
public class Map {
	// general size for tiles of the map
	public static final double TILE_SIZE = 15.5;
	
	private TiledMap map;
	private String tileValue;
	
	private int tileID;
	private int width, height;
	private boolean[][] collisionMap;
	
	public Map(String res) throws SlickException {
		map = new TiledMap(res);
			
		width = map.getWidth();
		height = map.getHeight();
		
		buildCollisionMap();
	}
	
	public void render(int x, int y) {
		map.render(x, y);
	}
	
	public boolean isBlocked(double x, double y) {
		int tileX = (int) (x / TILE_SIZE);
		int tileY = (int) (y / TILE_SIZE);
		
		return collisionMap[tileX][tileY];
	}
	
	public void setBlocked(double x, double y, boolean value) {
		int tileX = (int) (x / TILE_SIZE);
		int tileY = (int) (y / TILE_SIZE);
		
		collisionMap[tileX][tileY] = value;
	}
	
	// build a collision map based on tile properties in the map
	private void buildCollisionMap() {
		collisionMap = new boolean[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tileID = map.getTileId(i, j, 0);
				tileValue = map.getTileProperty(tileID, "isBlocked", "false");
					
				if (tileValue.equals("true")) {
					collisionMap[i][j] = true;
				}
			}
		}
	}
}
