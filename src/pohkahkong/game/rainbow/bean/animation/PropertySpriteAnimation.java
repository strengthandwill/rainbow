package pohkahkong.game.rainbow.bean.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class PropertySpriteAnimation extends SpriteAnimation {
	protected static enum State {FALSE, RESTARTING, CONTINUOUS};
	
	protected Matrix matrix = new Matrix();
	protected Paint paint = new Paint();

	// rotating
	protected boolean rotating = false;
	protected float degrees = 0.0f;
	
	// fading
	protected boolean fading = false;
	protected int alpha = 255;
	protected float alphaDelta = -2.0f;
	
	// translating
	protected State translatingX = State.FALSE;
	protected State translatingY = State.FALSE;
	protected float translateDistX;
	protected float translateDistY;
	protected float translateX = 0;
	protected float translateY = 0;
	protected float translateDeltaX = 1.0f;
	protected float translateDeltaY = 1.0f;

	public PropertySpriteAnimation(Bitmap bitmap, int left, int top, int width,
			int height, int frames, float fps) {
		super(bitmap, left, top, width, height, frames, fps);
	}
	
	public PropertySpriteAnimation(Bitmap bitmap, int left, int top, int width,
			int height, float fps) {
		super(bitmap, left, top, width, height, fps);
	}
	
	@Override
	public boolean update(long newTime) {
		updateProperty(newTime);
		return updateSprite(newTime);
	}	
	
	protected boolean updateSprite(long newTime) {
		return super.update(newTime);
	}
	
	protected boolean updateProperty(long newTime) {
		if (newTime<time+1.0/fps*50) {
			return false;
		}
		if (rotating || translatingX!=State.FALSE || translatingY!=State.FALSE) {
			matrix.reset();		
			rotate();
			translate();
		}
		if (fading) {
			paint.reset();
			fading();
		}
		return true;
	}

	private void rotate() {
		if (!rotating) {
			return;
		}
		degrees++;
		if (degrees>360.0f) {
			degrees = 0.0f;
		}		
		matrix.postRotate(degrees, width/2, height/2);
	}
	
	private void translate() {
		if (translatingX == State.FALSE
				&& translatingY == State.FALSE) {
			return;
		}
		if (translatingX!=State.FALSE) {
			translateX+=translateDeltaX;
			if (Math.abs(translateX)<0 || Math.abs(translateX)>translateDistX) {
				if (translatingX==State.RESTARTING) {
					translateX = 0.0f;
				} else {
					translateDeltaX = -translateDeltaX;
				}
			}
		}
		if (translatingY!=State.FALSE) {
			translateY+=translateDeltaY;
			if (Math.abs(translateY)<0 || Math.abs(translateY)>translateDistY) {
				if (translatingY==State.RESTARTING) {
					translateY = 0.0f;
				} else {
					translateDeltaY = -translateDeltaY;
				}
			}
		}
		matrix.postTranslate(translateX, translateY);
	}
	
	private void fading() {
		if (!fading) {
			return;
		}
		alpha+=alphaDelta;
		if (alpha<=100 || alpha>=255) {
			alphaDelta = -alphaDelta;
			alpha+=alphaDelta;
		}
		paint.setAlpha(alpha);
	}
	
	@Override
	public void draw(Canvas canvas, float x, float y) {
		canvas.save();
		Rect src = new Rect(left+width*frame, top, left+width*(frame+1), top+height);
		Rect dst = new Rect(0, 0, width, height);
		canvas.translate(x,y);
		canvas.concat(matrix);
		canvas.drawBitmap(bitmap, src, dst, paint);
		canvas.restore();
	}

	// getters and setters
	public void setTranslucent(boolean translucent) {
		if (translucent) {
			paint.setAlpha(100);
			fading = false;
		} else {
			paint.setAlpha(255);
		}
		
	}
	
	public void setRotating(boolean rotating) {
		this.rotating = rotating;
	}
	
	public void setTranslateX(boolean translating, float translateDistX, boolean up, boolean restarting) {
		if (!translating) {
			translatingX = State.FALSE;
			return;
		}
		this.translateDistX = translateDistX;
		translateDeltaX = up? -translateDeltaX:translateDeltaX;
		translatingX = restarting? State.RESTARTING:State.CONTINUOUS;
	}
	
	public void setTranslateY(boolean translating, float translateDistY, boolean left, boolean restarting) {		
		if (!translating) {
			translatingY = State.FALSE;
			return;
		}
		this.translateDistY = translateDistY;
		translateDeltaY = left? -translateDeltaY:translateDeltaY;
		translatingY = restarting? State.RESTARTING:State.CONTINUOUS;
	}

	public void setFading(boolean fading) {
		this.fading = fading;
	}
	
	public PropertySpriteAnimation clone() {
		return new PropertySpriteAnimation(bitmap, left, top, width, height, 
				frames, fps);
	}
}
