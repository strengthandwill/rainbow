// Name: 		OhOh
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
public class OhohPSGEO extends PlayerStateGEO {	
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public OhohPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
	}

	// game engine events
	@Override
	public void load() {		
		normalUp = imagesDevice.getOhohPlayerNormalUp();
		normalDown = imagesDevice.getOhohPlayerNormalDown();
		normalLeft = imagesDevice.getOhohPlayerNormalLeft();
		normalRight = imagesDevice.getOhohPlayerNormalRight();
		normalBlink = imagesDevice.getOhohPlayerNormalBlink();		
		slowUp = imagesDevice.getOhohPlayerSlowUp();
		slowDown = imagesDevice.getOhohPlayerSlowDown();
		slowLeft = imagesDevice.getOhohPlayerSlowLeft();
		slowRight = imagesDevice.getOhohPlayerSlowRight();
		slowBlink = imagesDevice.getOhohPlayerSlowBlink();			
		stun = imagesDevice.getOhohPlayerStun();
		slip = imagesDevice.getOhohPlayerSlip();
		gameOver = imagesDevice.getOhohPlayerGameOver();
		super.load();
	}
		
	// player actions
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
		gameSoundLoader.playerOhOh();
	}	
}
