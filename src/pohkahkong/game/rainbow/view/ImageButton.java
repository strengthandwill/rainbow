package pohkahkong.game.rainbow.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class ImageButton extends ImageView {
	protected int maxAlpha = 255;
	protected OnClickListener onClickListener = null;

	public ImageButton(Context context) {
		super(context);
		init();
	}

	public ImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}	

	@Override
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	private void init() {
		setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_UP) {
					onClick();
					if (onClickListener!=null) {
						onClickListener.onClick(view);
					}
					setAlpha(maxAlpha);	
				} else if (event.getAction()==MotionEvent.ACTION_DOWN) {
					setAlpha(maxAlpha-150);
				}
				return true;
			}
		});
	}	
	
	protected void onClick() {		
	}
	
	public void setMaxAlpha(int maxAlpha) {
		this.maxAlpha = maxAlpha;
		setAlpha(maxAlpha);	
	}
}
