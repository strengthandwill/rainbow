package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.bean.Player;
import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.engine.object.GameEngineObject;
import pohkahkong.game.rainbow.game.engine.object.player.OnPlayerGesturePerformedListener.PlayerGesture;
import pohkahkong.game.rainbow.service.GestureService;
import pohkahkong.game.rainbow.service.ToastService;
import pohkahkong.game.rainbow.service.XmlParserService;
import android.graphics.Canvas;
import android.util.Log;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class PlayerGEO implements GameEngineObject {	
	private XmlParserService xmlParserService = XmlParserService.getInstance();
	private GestureService gestureService = GestureService.getInstance();	
	private ToastService toastService = ToastService.getInstance();
	
	private PlayerStateGEO rara;
	private PlayerStateGEO ahah;
	private PlayerStateGEO inin;
	private PlayerStateGEO nana;
	private PlayerStateGEO bobo;
	private PlayerStateGEO ohoh;
	private PlayerStateGEO wawa;
	
	private PlayerStateGEO playerState = null;	
	private float x;
	private float y;
	
	public PlayerGEO(OnEventReceivedListener onEventReceivedListener) {		
		// create players
		rara = new RaraPSGEO(onEventReceivedListener);
		ahah = new AhahPSGEO(onEventReceivedListener);
		inin = new IninPSGEO(onEventReceivedListener);
		nana = new NanaPSGEO(onEventReceivedListener);
		bobo = new BoboPSGEO(onEventReceivedListener);
		ohoh = new OhohPSGEO(onEventReceivedListener);
		wawa = new WawaPSGEO(onEventReceivedListener);
		
		Player player = xmlParserService.getPlayer();		
		x = player.getX();
		y = player.getY();
		rara.setLock(player.isRaRaLock());
		ahah.setLock(player.isAhAhLock());
		inin.setLock(player.isInInLock());
		nana.setLock(player.isNaNaLock());
		bobo.setLock(player.isBoBoLock());
		ohoh.setLock(player.isOhOhLock());
		wawa.setLock(player.isWaWaLock());
				
		gestureService.setOnPlayerGesturePerformedListener(new OnPlayerGesturePerformedListener() {
			public void onPlayerGesturePerformed(PlayerGesture playerGesture) {
				if (playerGesture==PlayerGesture.JUMP) {
					if (jump()) {
						toastService.showMessage("JUMP!");
					}
				} else if (changePlayerState(playerGesture)) {
					toastService.showMessage("Change to " + playerGesture.toString() + "!");
				}				
			}			
		});
	}
	
	// game engine states
	public void play() {
		playerState.play();
	}
	
	public void pause() {
		playerState.pause();
	}
	
	public void stop() {
	}
	
	// game engine events
	public void load() {
		rara.load();
		ahah.load();
		inin.load();
		nana.load();
		bobo.load();
		ohoh.load();
		wawa.load();
		if (playerState==null) {
			playerState = rara;
			playerState.setPosition(x, y);
			playerState.playStart();
		}
	}
	
	public void update(long newTime) {
		playerState.update(newTime);
	}
	
	public void draw(Canvas canvas) {
		playerState.draw(canvas);
	}
	
	public boolean detectCollision(float objX, float objY) {
		return false;
	}
	
	// player actions
	private boolean jump() {
		return playerState.jump();
	}
	
	public void slow() { // sunny
		playerState.slow();
	}
	
	public void stun() { // lightny
		playerState.stun();
	}
	
	public void slip() { // rainy
		playerState.slip();
	}
	
	public boolean gameOverEnemy() { // snowy
		return playerState.gameOverEnemy();
	}
	
	public void outside() { // outside
		playerState.outside();
	}
	
	public void inside() { // inside
		playerState.inside();
	}

	public void win() { // win
		playerState.win();
	}
	
	public void gameOver() { // game over
		playerState.gameOver();
	}
	
	private boolean changePlayerState(PlayerGesture playerGesture) {
		Log.i("PlayerGEO", playerGesture.toString());		
		PlayerStateGEO newPlayerState = null;
		switch (playerGesture) {
		case RARA: 	
			newPlayerState = rara;
			break;
		case AHAH: 	
			newPlayerState = ahah;
			break;
		case ININ: 	
			newPlayerState = inin;
			break;
		case NANA: 	
			newPlayerState = nana;
			break;
		case BOBO: 	
			newPlayerState = bobo;
			break;
		case OHOH: 	
			newPlayerState = ohoh;
			break;
		case WAWA: 	
			newPlayerState = wawa;
			break;
		default:
			break;
		}
		if (newPlayerState == playerState || newPlayerState.isLock()
				|| !playerState.isNormal()) {
			return false;
		}
		x = playerState.getX();
		y = playerState.getY();
		playerState = newPlayerState;
		playerState.setPosition(x, y);
		playerState.play();
		playerState.playChange();
		return true;
	}
	
	// getters
	public float getX() {
		return playerState.getX();
	}
	
	public float getY() {
		return playerState.getY();
	}
	
	public boolean isCollidable() {
		return playerState.isCollidable();
	}
}
