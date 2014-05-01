package pohkahkong.game.rainbow.game.engine.object.enemy;

import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.game.engine.object.GameEngineObject;
import pohkahkong.game.rainbow.service.DimensionService;
import android.graphics.Canvas;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public abstract class EnemyGEO implements GameEngineObject {	
	private DimensionService dimensionService = DimensionService.getInstance();
	
	protected final float closeFactor = 0.25f;
	protected boolean isHorizontal;
	protected boolean isMoving = false;
	protected long time = 0;
	
	// speed attributes
	protected float x;
	protected float y;
	protected float delta = 0.5f;
	protected float distance;
	protected float movedDistance = 0.0f;
	protected boolean isInc = true;
	protected float size;
	
	protected Animation up;
	protected Animation down;
	protected Animation left;
	protected Animation right;
	protected Animation sprite;
		
	public EnemyGEO(float x, float y, boolean isHorizontal, float distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.isHorizontal = isHorizontal;
		size = dimensionService.getSize();
	}	
	
	// game engine states
	public void pause() {
		isMoving = false;
	}
	
	public void play() {
		isMoving = true;
	}
	
	public void stop() {
		isMoving = false;
	}
	
	// game engine events
	public void load() {
		sprite = isHorizontal? right:down;
	}

	public void update(long newTime) {
		// update frame		
		sprite.update(newTime);
		
		// update position
		if (!isMoving) {
			return;
		}
		movedDistance+=delta;
		if (!isInc&&movedDistance<=0.0f || isInc&&movedDistance>=distance) {
			delta *= -1.0f;
			isInc = !isInc;
			if (isHorizontal) {
				sprite = delta<0? left:right;
			} else {
				sprite = delta<0? up:down;
			}
		}		
		if (isHorizontal) {
			x += delta;
		} else {
			y += delta;
		}
	}
	
	public void draw(Canvas canvas) {
		sprite.draw(canvas, x, y);		
	}
	
	public boolean detectCollision(float objX, float objY) {		
		float x1 = x + size*closeFactor;
		float x2 = x + size - size*closeFactor;
		float y1 = y + size*closeFactor;
		float y2 = y + size - size*closeFactor;
		
		float objX1 = objX + size*closeFactor;
		float objX2 = objX + size - size*closeFactor;
		float objY1 = objY + size*closeFactor;
		float objY2 = objY + size- size*closeFactor;
				
		if (objX1>=x1 && objX1<x2 && objY1>y1 && objY1<=y2 ||	
			objX2>=x1 && objX2<x2 && objY1>y1 && objY1<=y2 ||
			objX1>=x1 && objX1<x2 && objY2>y1 && objY2<=y2 ||
			objX2>=x1 && objX2<x2 && objY2>y1 && objY2<=y2)						
			return true;
		else
			return false;
	}
}
