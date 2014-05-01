package pohkahkong.game.rainbow.bean.animation;

import android.graphics.Bitmap;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class UnlockableRandomPSA extends RandomPSA {
	private boolean lock = true;
	
	public UnlockableRandomPSA(Bitmap bitmap, int left, int top,
			int width, int height, int frames, float fps, int randomFrame) {
		super(bitmap, left, top, width, height, frames, fps, randomFrame);
		this.frames = frames - 1;
	}
	
	@Override
	public boolean update(long newTime) {
		if (lock) {
			return false;
		}
		return super.update(newTime);
	}
	
	@Override
	public void update() {
		super.update();
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
		if (lock) {
			frame = frames;
		}
	}
}
