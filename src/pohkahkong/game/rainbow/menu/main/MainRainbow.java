package pohkahkong.game.rainbow.menu.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MainRainbow {
	private final int speed = 2;
	
	private float x;
	private float y;
	private boolean isCompleted;
	private Paint paint;
	private int alpha = 255;
	private boolean isInc = false;	
	private Bitmap rainbow;
	
	public MainRainbow(float x, float y, boolean isCompleted, Bitmap rainbow) {
		this.x = x;
		this.y = y;
		this.isCompleted = isCompleted;
		this.rainbow = rainbow;
		paint = new Paint();		
	}
	
	private void update() {
		if (isInc) {
			alpha+=speed;
			if (alpha>=255) {
				alpha = 255;
				isInc = false;
			}
		} else {
			alpha-=speed;
			if (alpha<=100) {
				alpha = 100;
				isInc = true;
			}
		}			
	}
	
	public void draw(Canvas canvas) {		
		if (isCompleted) {
			update();
			paint.setAlpha(alpha);
			canvas.drawBitmap(rainbow, x, y, paint);
		}
	}
}
