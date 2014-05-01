// Name: 		AhAh
// Ability: 	Anti-Sunny
// Immunity: 	Slow

package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.game.GameSoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class AhahPSGEO extends PlayerStateGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public AhahPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
	}

	// game engine events
	@Override
	public void load() {
		normalUp = imagesDevice.getAhahPlayerNormalUp();
		normalDown = imagesDevice.getAhahPlayerNormalDown();
		normalLeft = imagesDevice.getAhahPlayerNormalLeft();
		normalRight = imagesDevice.getAhahPlayerNormalRight();
		normalBlink = imagesDevice.getAhahPlayerNormalBlink();		
		slowUp = imagesDevice.getAhahPlayerSlowUp();
		slowDown = imagesDevice.getAhahPlayerSlowDown();
		slowLeft = imagesDevice.getAhahPlayerSlowLeft();
		slowRight = imagesDevice.getAhahPlayerSlowRight();
		slowBlink = imagesDevice.getAhahPlayerSlowBlink();			
		stun = imagesDevice.getAhahPlayerStun();
		slip = imagesDevice.getAhahPlayerSlip();
		gameOver = imagesDevice.getAhahPlayerGameOver();
		super.load();
	}

	// player actions
	@Override
	public void slow() {
	}
	
	// player actions
	@Override
	public boolean jump() {
		return false;
	}

	@Override
	public void playChange() {
		gameSoundLoader.playerAhAh();
	}
}
