// Name: 		BoBo
// Ability: 	Anti-Rainy
// Immunity: 	Slip

package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.game.GameSoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class BoboPSGEO extends PlayerStateGEO {	
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public BoboPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
	}

	// game engine events
	@Override
	public void load() {		
		normalUp = imagesDevice.getBoboPlayerNormalUp();
		normalDown = imagesDevice.getBoboPlayerNormalDown();
		normalLeft = imagesDevice.getBoboPlayerNormalLeft();
		normalRight = imagesDevice.getBoboPlayerNormalRight();
		normalBlink = imagesDevice.getBoboPlayerNormalBlink();		
		slowUp = imagesDevice.getBoboPlayerSlowUp();
		slowDown = imagesDevice.getBoboPlayerSlowDown();
		slowLeft = imagesDevice.getBoboPlayerSlowLeft();
		slowRight = imagesDevice.getBoboPlayerSlowRight();
		slowBlink = imagesDevice.getBoboPlayerSlowBlink();			
		stun = imagesDevice.getBoboPlayerStun();
		gameOver = imagesDevice.getBoboPlayerGameOver();
		super.load();
	}	

	// player actions
	@Override
	public void slip() {
	}
	
	@Override
	public boolean jump() {
		return false;
	}
	
	@Override
	public void playChange() {
		gameSoundLoader.playerBoBo();
	}	
}
