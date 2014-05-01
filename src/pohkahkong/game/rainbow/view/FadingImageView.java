package pohkahkong.game.rainbow.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class FadingImageView extends ImageView {
	private int alpha = 255;
	private int alphaDelta = -1;
	
	public FadingImageView(Context context) {
		super(context);
	}

	public FadingImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {	
		alpha += alphaDelta;
		if (alpha<100 || alpha>255) {
			alphaDelta = -alphaDelta;
			alpha += alphaDelta;
		}
		setAlpha(alpha);
		super.onDraw(canvas);
		invalidate();
	}
}
