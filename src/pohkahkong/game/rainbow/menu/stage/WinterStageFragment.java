package pohkahkong.game.rainbow.menu.stage;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.menu.MenuImageService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class WinterStageFragment extends StageFragment {
	private MenuImageService imageService = MenuImageService.getInstance();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		season = 4;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.stage_winter, container, false);						
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {		
		super.onActivityCreated(savedInstanceState);
		initViews();
		stageObjectsView.init(5, false, 0.5f);	
		stageObjectsView.load(imageService.getWinterObject());
	}	
}
