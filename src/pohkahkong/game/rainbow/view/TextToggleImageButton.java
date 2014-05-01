package pohkahkong.game.rainbow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class TextToggleImageButton extends ToggleImageButton {
	private String text;
	private Paint paint = new Paint();
	private Rect bounds = new Rect();
	public TextToggleImageButton(Context context) {
		super(context);
	}
	
	public TextToggleImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);		
		if (text==null || !toggled) {
			return;
		}
		paint.setTextSize(getHeight()*0.8f);
		paint.getTextBounds(text, 0, text.length(), bounds);
		float width = paint.measureText(text);
		float height = bounds.height();		
		canvas.drawText(text, (getWidth()-width)/2, 
			(getHeight()+height)/2, paint);
	}
	
	public void setText(String text) {
		this.text = text;		
		invalidate();
	}
	
	public String getText() {
		return text;
	}
}
