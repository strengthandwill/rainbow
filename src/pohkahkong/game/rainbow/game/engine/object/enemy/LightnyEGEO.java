// Name: 		Lightny
// Ability: 	Stun	
// Immunity: 	InIn

package pohkahkong.game.rainbow.game.engine.object.enemy;

import pohkahkong.game.rainbow.game.GameImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class LightnyEGEO extends EnemyGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	
	public LightnyEGEO(float x, float y, boolean isHorizontal, float distance) {
		super(x, y, isHorizontal, distance);
	}
	
	@Override
	public void load() {
		up = imagesDevice.getLightnyEnemyUp();
		down = imagesDevice.getLightnyEnemyDown();
		left = imagesDevice.getLightnyEnemyLeft();
		right = imagesDevice.getLightnyEnemyRight();
		super.load();
	}
}
