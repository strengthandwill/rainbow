// Name: 		AhAh
// Ability: 	Anti-Lightny
// Immunity: 	Stun

package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.game.GameSoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class IninPSGEO extends PlayerStateGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public IninPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
	}

	// game engine events
	@Override
	public void load() {		
		normalUp = imagesDevice.getIninPlayerNormalUp();
		normalDown = imagesDevice.getIninPlayerNormalDown();
		normalLeft = imagesDevice.getIninPlayerNormalLeft();
		normalRight = imagesDevice.getIninPlayerNormalRight();
		normalBlink = imagesDevice.getIninPlayerNormalBlink();		
		slowUp = imagesDevice.getIninPlayerSlowUp();
		slowDown = imagesDevice.getIninPlayerSlowDown();
		slowLeft = imagesDevice.getIninPlayerSlowLeft();
		slowRight = imagesDevice.getIninPlayerSlowRight();
		slowBlink = imagesDevice.getIninPlayerSlowBlink();			
		slip = imagesDevice.getIninPlayerSlip();
		gameOver = imagesDevice.getIninPlayerGameOver();
		super.load();
	}	

	// player actions
	@Override
	public void stun() {
	}
	
	@Override
	public boolean jump() {
		return false;
	}
	
	@Override
	public void playChange() {
		gameSoundLoader.playerInIn();
	}	
}
