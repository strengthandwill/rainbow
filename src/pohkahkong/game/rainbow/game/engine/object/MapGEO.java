package pohkahkong.game.rainbow.game.engine.object;

import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.XmlParserService;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MapGEO {		
	private XmlParserService xmlParserService = XmlParserService.getInstance();
	private DimensionService dimensionService = DimensionService.getInstance();
	private GameImageService gameImageLoader = GameImageService.getInstance();
	
	private int width;
	private int height;
	private int size;
	private Bitmap road;
	private Bitmap[] outside;
	
	private int[][] mapRoad;
	private int[][] map;
	
	public MapGEO() {
		mapRoad = xmlParserService.getMapRoad();
		width = mapRoad[0].length+1;
		height = mapRoad.length+1;	
		map = new int[height][width];
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				if (x<mapRoad[0].length && y<mapRoad.length && mapRoad[y][x]==1) // road
					map[y][x] = 0;
				else // outside
					map[y][x] = (int)(Math.random()*5.0)+1;
			}
		}
		size = (int) dimensionService.getSize();
	}
	
	// *************************** general methods *************************** //
	public void load() {
		road = gameImageLoader.getMapRoad();
		outside = gameImageLoader.getMapOutside();
	}
	
	public void draw(Canvas canvas) {		
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				switch (map[y][x]) {
				case 0: 					
					canvas.drawBitmap(road, x*size, y*size, null);
					break;
				case 1: 					
					canvas.drawBitmap(outside[0], x*size, y*size, null);
					break;
				case 2: 
					canvas.drawBitmap(outside[1], x*size, y*size, null);
					break;
				case 3: 
					canvas.drawBitmap(outside[2], x*size, y*size, null);
					break;
				case 4: 
					canvas.drawBitmap(outside[3], x*size, y*size, null);
					break;
				case 5: 
					canvas.drawBitmap(outside[4], x*size, y*size, null);
					break;
				}
			}
		}		
	}
	
	public boolean collisionDetection(int objX, int objY) {
		int objMapX = (int)((objX+size/2.0f)/size);
		int objMapY = (int)((objY+size/2.0f)/size);		
		if (mapRoad[objMapY][objMapX]==0)
			return true;
		else
			return false;
	}
}
