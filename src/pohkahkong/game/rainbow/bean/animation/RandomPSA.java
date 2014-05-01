package pohkahkong.game.rainbow.bean.animation;

import android.graphics.Bitmap;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class RandomPSA extends PropertySpriteAnimation {
	private int randomFrame;
	
	public RandomPSA(Bitmap bitmap, int left, int top, int width,
			int height, int frames, float fps, int randomFrame) {
		super(bitmap, left, top, width, height, frames, fps);
		this.randomFrame = randomFrame;
	}
	
	@Override
	public boolean updateSprite(long newTime) {
		if (newTime<time+1.0/fps*1000) {
			return false;
		}
		time = newTime;
		if (frame!=randomFrame) {
			update();
			return false;
		}
		if (Math.random()<0.1) {
			update();
			return true;
		}
		return false;
	}	
	
	public RandomPSA clone() {
		return new RandomPSA(bitmap, left, top, width, height, frames, 
				fps, randomFrame);
	}
}
