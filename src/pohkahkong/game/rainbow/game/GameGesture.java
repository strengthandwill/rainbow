package pohkahkong.game.rainbow.game;

import android.view.View;
import pohkahkong.game.rainbow.service.GestureService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameGesture implements GameActivityScreen {
	private GestureService gestureService = GestureService.getInstance();
	
	// game activity states	
	public void load() {
		gestureService.hide();
	}
	
	public void hide() {
		gestureService.hide();
	}

	public void hold() {
		gestureService.hide();
	}

	public void play() {
		gestureService.show();
	}

	public void pause() {
		gestureService.hide();
	}

	public void stop() {
		gestureService.hide();
	}
	
	// getters and setters
	public View getView() {
		return gestureService.getView();
	}
}
