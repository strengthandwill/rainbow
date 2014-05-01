package pohkahkong.game.rainbow.bean.animation;

import android.graphics.Bitmap;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class RandomSetPSA extends PropertySpriteAnimation {
	private int sets;
	private int randomFrame;
	private int set;

	public RandomSetPSA(Bitmap bitmap, int left, int top, int width,
			int height, int frames, int sets, float fps, int randomFrame) {
		super(bitmap, left, top, width, height, frames, fps);
		this.sets = sets;
		this.randomFrame = randomFrame;
	}
	
	@Override
	public boolean update(long newTime) {
		if (newTime<time+1.0/fps*1000) {
			return false;
		}
		time = newTime;
		update();
		if (set==randomFrame && Math.random()>0.1) {
			return false;
		}
		set++;
		if (set>sets) {
			set = 0;
		}
		return true;
	}	
	
	@Override
	public void update() {		
		frame++;
		if (frame>=frames*(set+1)) {
			frame = frames*set;
		}			
	}
	
	public RandomSetPSA clone() {
		return new RandomSetPSA(bitmap, left, top, width, height, frames, set, fps, randomFrame);
	}
}
