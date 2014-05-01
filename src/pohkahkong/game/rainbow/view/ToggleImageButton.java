package pohkahkong.game.rainbow.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class ToggleImageButton extends ImageButton {
	private Bitmap bitmap;
	private Rect src;
	private Rect dst;
	private Paint paint = new Paint();
	protected boolean toggled = false;
	
	public ToggleImageButton(Context context) {
		super(context);
		init();
	}

	public ToggleImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
		setMaxAlpha(200);
		setToggled(toggled);		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int size = getMeasuredHeight();
		if (size>0) {
			setMeasuredDimension(size, size);	
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		dst = new Rect(0, 0, w, h);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (bitmap!=null) {
			canvas.drawBitmap(bitmap, src, dst, paint);
		}
	}
	
	@Override
	protected void onClick() {
		setToggled(!toggled);
	}
	
	@Override
	public void setAlpha(int alpha) {
		paint.setAlpha(alpha);
		invalidate();
	}	
	
	// getters and setters
	public boolean isToggled() {
		return toggled;
	}
	
	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		int frame = toggled? 1:0;		
		src = new Rect(bitmap.getWidth()/2*frame, 0, bitmap.getWidth()/2*(frame+1), bitmap.getHeight());			
		invalidate();
	}
}