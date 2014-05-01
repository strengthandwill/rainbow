package pohkahkong.game.rainbow.menu.stage;

import android.content.Context;
import android.util.AttributeSet;
import pohkahkong.game.rainbow.view.TextToggleImageButton;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class StageLevelBtn extends TextToggleImageButton {
	public StageLevelBtn(Context context) {
		super(context);
	}
	
	public StageLevelBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setNotClickable() {
		setOnTouchListener(null);
	}
	
	@Override
	protected void onClick() {
	}
}
