package pohkahkong.game.rainbow.game.engine.object;

import pohkahkong.game.rainbow.bean.Item;
import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.XmlParserService;
import android.graphics.Canvas;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class RainbowGEO implements GameEngineObject {		
	private XmlParserService xmlParserService = XmlParserService.getInstance();
	private DimensionService dimensionService = DimensionService.getInstance();
	private GameImageService gameImageLoader = GameImageService.getInstance();
	
	private final float EQUAL_FACTOR = 0.01f;
	private final float CLOSE_FACTOR = 0.3f;
	private float size;
	private float x;
	private float y;
	private Animation animation;
	
	public RainbowGEO() {
		Item item = xmlParserService.getRainbow();
		x = item.getX();
		y = item.getY();
		size = dimensionService.getSize();
	}
	
	// game engine events
	public void load() {
		animation = gameImageLoader.getRainbow();
	}
	
	public void update(long newTime) {
		animation.update(newTime);
	}	
	
	public void draw(Canvas canvas) {
		animation.draw(canvas, x, y);
	}
	
	public boolean detectCollision(float objX, float objY) {
		if ((Math.abs(x - objX + size) < size * EQUAL_FACTOR && 
			Math.abs(y - objY) < size * CLOSE_FACTOR)
			|| (Math.abs(y - objY) < size * EQUAL_FACTOR && 
			Math.abs(x - objX + size) < size * CLOSE_FACTOR)) {
			return true;			
		} else {
			return false;
		}
	}
	
	// game engine states
	public void play() {
	}

	public void pause() {
	}

	public void stop() {
	}
}
