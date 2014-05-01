package pohkahkong.game.rainbow.game.engine.object;

import android.graphics.Canvas;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public interface GameEngineObject {
	// game engine states
	public void play();
	
	public void pause();
	
	public void stop();
	
	// game engine events
	public void load();
	
	public void update(long time); 
	
	public void draw(Canvas canvas);
	
	public boolean detectCollision(float objX, float objY);
}
