package pohkahkong.game.rainbow.menu.stage;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.menu.MenuSoundService;
import pohkahkong.game.rainbow.service.PreferencesService;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public abstract class StageFragment extends Fragment {
	private static final String TAG = "StageFragment";
	
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private MenuSoundService soundService = MenuSoundService.getInstance();
	
	protected ViewGroup rootView;
	protected StageObjectsView stageObjectsView;
	protected int season;
	protected OnCompleteListener onCompleteListener;

	protected void initViews() {
		stageObjectsView = (StageObjectsView) rootView.findViewById(R.id.stageObjectsView);
		TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.stageTL);
		int i=0;
		int lastLevel;
		if (season<preferencesService.getSeason()) {
			lastLevel = 20;
		} else if (season==preferencesService.getSeason()) {
			lastLevel = preferencesService.getLevel();
		} else {
			lastLevel = 0;
		}		
		for (int y=0; y<tableLayout.getChildCount(); y++) {
			TableRow tableRow = (TableRow) tableLayout.getChildAt(y);
			for (int x=0; x<tableRow.getChildCount(); x++) {
				StageLevelBtn levelBtn = (StageLevelBtn) tableRow.getChildAt(x);
				if (i<lastLevel) {					
					final int level = i+1;
					levelBtn.setToggled(true);
					levelBtn.setText(String.valueOf(level));					
					levelBtn.setOnClickListener(new OnClickListener() {
						public void onClick(View view) {
							Log.i(TAG, "" + season + " " + level + " selected");
							soundService.buttonClick();
							onCompleteListener.onComplete(new Object[] {
									Integer.valueOf(season),
									Integer.valueOf(level) });
						}							
					});
					i++;
				} else {
					levelBtn.setNotClickable();
				}
			}
		}
	}
	
	public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
		this.onCompleteListener = onCompleteListener;
	}

	@Override
	public void onDestroyView() {
	    stageObjectsView = null;
	    rootView = null;
	    super.onDestroyView();
	}
}