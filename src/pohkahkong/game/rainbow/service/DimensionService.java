package pohkahkong.game.rainbow.service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class DimensionService {
	private float windowWidth;
	private float windowHeight;
	
	private int mapWidth;
	private int mapHeight;
	private float gameWidth;
	private float gameHeight;
	private float size;
	
	private static DimensionService instance = null;	
	
	private DimensionService() {
	}

	public static DimensionService getInstance() {
		if (instance==null) {
			instance = new DimensionService();
		}
		return instance;
	}
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public void init(Activity activity) {			
		Display display = activity.getWindowManager().getDefaultDisplay();		
		if (android.os.Build.VERSION.SDK_INT >= 13) {		
			Point size = new Point();
			display.getSize(size);
			windowWidth = (float) size.x;
			windowHeight = (float) size.y;
		} else {
			windowWidth = (float) display.getWidth();
			windowHeight = (float) display.getHeight();
		}
	}
	
	public void initTileDimension(int mapWidth, int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;		
		float sizeByWidth = windowWidth / mapWidth;
		float sizeByHeight = windowHeight / mapHeight;
		size = Math.min(sizeByWidth, sizeByHeight);
		gameWidth = mapWidth * size;
		gameHeight = mapHeight * size;
	}

	public float getWindowWidth() {
		return windowWidth;
	}

	public float getWindowHeight() {
		return windowHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public float getGameWidth() {
		return gameWidth;
	}

	public float getGameHeight() {
		return gameHeight;
	}

	public float getSize() {
		return size;
	}	
}