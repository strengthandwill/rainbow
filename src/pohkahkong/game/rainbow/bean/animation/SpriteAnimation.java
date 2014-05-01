package pohkahkong.game.rainbow.bean.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class SpriteAnimation implements Animation {
	protected Bitmap bitmap;
	protected int left;
	protected int top;
	protected int width;
	protected int height;
	protected int frames;
	protected float fps;	
	protected int frame = 0;
	protected long time;
	
	public SpriteAnimation(Bitmap bitmap, int left, int top, int width, int height,
			int frames, float fps) {
		this.bitmap = bitmap;
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
		this.frames = frames;
		this.fps = fps;
	}
	
	public SpriteAnimation(Bitmap bitmap, int left, int top, int width,
			int height, float fps) {
		this.bitmap = bitmap;
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
		this.frames = 1;
		this.fps = fps;
	}
	
	public boolean update(long newTime) {
		if (frames==1 || newTime<time+1.0/fps*1000) {
			return false;
		}
		time = newTime;
		update();
		return true;
	}
	
	public void update() {
		if (frames==1) {
			return;
		}		
		frame++;
		if (frame>=frames) {
			frame = 0;
		}	
	}
	
	public void draw(Canvas canvas, float x, float y) {
		Rect src = new Rect(left+width*frame, top, left+width*(frame+1), top+height);
		Rect dst = new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));		
		canvas.drawBitmap(bitmap, src, dst, null);
	}

	// getters and setters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}	
	
	public SpriteAnimation clone() {
		return new SpriteAnimation(bitmap, left, top, width, height, 
				frames, fps);
	}
}
