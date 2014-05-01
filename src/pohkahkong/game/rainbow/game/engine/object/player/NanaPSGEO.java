// Name:		NaNa
// Ability: 	Jump
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
public class NanaPSGEO extends PlayerStateGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	private GameSoundService gameSoundLoader = GameSoundService.getInstance();
	
	public NanaPSGEO(OnEventReceivedListener onEventReceivedListener) {
		super(onEventReceivedListener);
		
	}
	
	// game engine events
	@Override
	public void load() {		
		normalUp = imagesDevice.getNanaPlayerNormalUp();
		normalDown = imagesDevice.getNanaPlayerNormalDown();
		normalLeft = imagesDevice.getNanaPlayerNormalLeft();
		normalRight = imagesDevice.getNanaPlayerNormalRight();
		normalBlink = imagesDevice.getNanaPlayerNormalBlink();		
		slowUp = imagesDevice.getNanaPlayerSlowUp();
		slowDown = imagesDevice.getNanaPlayerSlowDown();
		slowLeft = imagesDevice.getNanaPlayerSlowLeft();
		slowRight = imagesDevice.getNanaPlayerSlowRight();
		slowBlink = imagesDevice.getNanaPlayerSlowBlink();
		jumpUp = imagesDevice.getNanaPlayerJumpUp();
		jumpDown = imagesDevice.getNanaPlayerJumpDown();
		jumpLeft = imagesDevice.getNanaPlayerJumpLeft();
		jumpRight = imagesDevice.getNanaPlayerJumpRight();
		stun = imagesDevice.getNanaPlayerStun();
		slip = imagesDevice.getNanaPlayerSlip();
		gameOver = imagesDevice.getNanaPlayerGameOver();	
		super.load();
	}
	
	// player actions
	@Override
	public void playChange() {
		gameSoundLoader.playerNaNa();
	}
}