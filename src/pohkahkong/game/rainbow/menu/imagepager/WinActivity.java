package pohkahkong.game.rainbow.menu.imagepager;

import pohkahkong.game.rainbow.R;
import android.os.Bundle;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class WinActivity extends ImagePagerActivity {
	private final Integer[] imageResIds = new Integer[] {	
			R.drawable.win_screen01, R.drawable.win_screen02, R.drawable.win_screen03,
			R.drawable.win_screen04, R.drawable.win_screen05, R.drawable.win_screen06,
			R.drawable.win_screen07, R.drawable.win_screen08, R.drawable.win_screen09,
			R.drawable.win_screen10, R.drawable.win_screen11, R.drawable.win_screen12,
			R.drawable.win_screen13, R.drawable.win_screen14};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		imageService.init(this, imageResIds);
		super.onCreate(savedInstanceState);
	}
}
