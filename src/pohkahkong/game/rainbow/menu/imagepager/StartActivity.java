package pohkahkong.game.rainbow.menu.imagepager;

import pohkahkong.game.rainbow.R;
import android.os.Bundle;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class StartActivity extends ImagePagerActivity {
	private final Integer[] imageResIds = new Integer[] {	
			R.drawable.start_screen01, R.drawable.start_screen02, R.drawable.start_screen03,
			R.drawable.start_screen04, R.drawable.start_screen05, R.drawable.start_screen06,
			R.drawable.start_screen07, R.drawable.start_screen08, R.drawable.start_screen09,
			R.drawable.start_screen10, R.drawable.start_screen11, R.drawable.start_screen12,
			R.drawable.start_screen13, R.drawable.start_screen14, R.drawable.start_screen15,
			R.drawable.start_screen16, R.drawable.start_screen17, R.drawable.start_screen18,
			R.drawable.start_screen19, R.drawable.start_screen20, R.drawable.start_screen21,
			R.drawable.start_screen22, R.drawable.start_screen23, R.drawable.start_screen24,
			R.drawable.start_screen25, R.drawable.start_screen26};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		imageService.init(this, imageResIds);
		super.onCreate(savedInstanceState);
	}
}
