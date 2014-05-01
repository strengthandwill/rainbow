package pohkahkong.game.rainbow.bean.animation;

import android.graphics.Canvas;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public interface Animation {
	public boolean update(long newTime);
	
	public void draw(Canvas canvas, float x, float y);	
	
	public int getWidth();
	
	public int getHeight();
}
