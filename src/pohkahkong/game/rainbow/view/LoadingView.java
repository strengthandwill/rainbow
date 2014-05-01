package pohkahkong.game.rainbow.view;

import pohkahkong.game.rainbow.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class LoadingView extends LinearLayout {
	public LoadingView(Context context) {
		super(context);
		init(context);
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context) {
		inflate(context, R.layout.loading, this);
	}
}
