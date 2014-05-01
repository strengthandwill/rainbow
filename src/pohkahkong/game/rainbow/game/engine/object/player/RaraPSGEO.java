// Name:		Rara
// Ability: 	Speed
// Immunity: 	None

package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameImageService;
import pohkahkong.game.rainbow.game.GameSoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class RaraPSGEO extends PlayerStateGEO {	
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public RaraPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
		normalSpeedLimit = 1.5f;
	}

	// game engine events
	@Override
	public void load() {		
		normalUp = imagesDevice.getRaraPlayerNormalUp();
		normalDown = imagesDevice.getRaraPlayerNormalDown();
		normalLeft = imagesDevice.getRaraPlayerNormalLeft();
		normalRight = imagesDevice.getRaraPlayerNormalRight();
		normalBlink = imagesDevice.getRaraPlayerNormalBlink();		
		slowUp = imagesDevice.getRaraPlayerSlowUp();
		slowDown = imagesDevice.getRaraPlayerSlowDown();
		slowLeft = imagesDevice.getRaraPlayerSlowLeft();
		slowRight = imagesDevice.getRaraPlayerSlowRight();
		slowBlink = imagesDevice.getRaraPlayerSlowBlink();			
		stun = imagesDevice.getRaraPlayerStun();
		slip = imagesDevice.getRaraPlayerSlip();
		gameOver = imagesDevice.getRaraPlayerGameOver();
		super.load();
	}
	
	// player actions
	@Override
	public boolean jump() {
		return false;
	}
	
	@Override
	public void playChange() {
		gameSoundLoader.playerRaRa();
	}	
}