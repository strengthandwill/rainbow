package pohkahkong.game.rainbow.service;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.game.engine.object.player.OnPlayerGesturePerformedListener;
import pohkahkong.game.rainbow.game.engine.object.player.OnPlayerGesturePerformedListener.PlayerGesture;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GestureService {
	private static GestureService instance = null;	
	private GestureOverlayView gestureOverlayView;
	private GestureLibrary gestureLib;
	
	private GestureService() {
	}

	public static GestureService getInstance() {
		if (instance==null) {
			instance = new GestureService();
		}
		return instance;
	}
	
	public void init(Context context) {
		gestureLib = GestureLibraries.fromRawResource(context, R.raw.gestures);		
		if (!gestureLib.load()) {
			return;
		}		
		gestureOverlayView = new GestureOverlayView(context);
	}
	
	public void setOnPlayerGesturePerformedListener(final OnPlayerGesturePerformedListener onPlayerGesturePerformedListener) {
		gestureOverlayView.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				Prediction prediction = gestureLib.recognize(gesture).get(0);		
				if (prediction.score > 1.0) {
					if (prediction.name.equals("RaRa")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.RARA);
					} else if (prediction.name.equals("AhAh")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.AHAH);
					} else if (prediction.name.equals("InIn")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.ININ);
					} else if (prediction.name.equals("NaNa")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.NANA);
					} else if (prediction.name.equals("BoBo")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.BOBO);
					} else if (prediction.name.equals("OhOh")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.OHOH);
					} else if (prediction.name.equals("WaWa")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.WAWA);
					} else if (prediction.name.equals("Jump")) {
						onPlayerGesturePerformedListener.onPlayerGesturePerformed(PlayerGesture.JUMP);
					}
				}
			}
		});
	}
	
	public GestureOverlayView getView() {
		return gestureOverlayView;
	}
	
	public void show() {			
		gestureOverlayView.setEnabled(true);	
	}
	
	public void hide() {
		gestureOverlayView.setEnabled(false);
	}
}
