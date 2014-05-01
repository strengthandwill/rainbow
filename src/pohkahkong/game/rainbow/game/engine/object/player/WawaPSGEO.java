// Name: 		WaWa
// Ability: 	Anti-Snowy	
// Immunity: 	Game Over Enemy

package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.game.GameSoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class WawaPSGEO extends PlayerStateGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public WawaPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
		normalSpeedLimit = 0.2f;
	}
	
	// game engine events
	@Override
	public void load() {
		normalUp = imagesDevice.getWawaPlayerNormalUp();
		normalDown = imagesDevice.getWawaPlayerNormalDown();
		normalLeft = imagesDevice.getWawaPlayerNormalLeft();
		normalRight = imagesDevice.getWawaPlayerNormalRight();
		normalBlink = imagesDevice.getWawaPlayerNormalBlink();
		gameOver = imagesDevice.getWawaPlayerGameOver();		
		super.load();
	}
	
	// player actions
	@Override
	public void slow() {
	}
	
	@Override
	public void stun() {
	}
	
	@Override
	public void slip() {
	}
	
	@Override
	public boolean gameOverEnemy() {
		return false;
	}
	
	@Override
	public boolean jump() {
		return false;
	}
	
	@Override
	public void playChange() {
		gameSoundLoader.playerWaWa();
	}
	
	// getters and setters
	@Override
	public boolean isCollidable() {
		return false;
	}
}
