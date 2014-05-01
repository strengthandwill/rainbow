// Name: 		Rainy
// Ability: 	Slip	
// Immunity: 	BoBo

package pohkahkong.game.rainbow.game.engine.object.enemy;

import pohkahkong.game.rainbow.game.GameImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class RainyEGEO extends EnemyGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	
	public RainyEGEO(float x, float y, boolean isHorizontal, float distance) {
		super(x, y, isHorizontal, distance);
	}
	
	@Override
	public void load() {
		up = imagesDevice.getRainyEnemyUp();
		down = imagesDevice.getRainyEnemyDown();
		left = imagesDevice.getRainyEnemyLeft();
		right = imagesDevice.getRainyEnemyRight();
		super.load();
	}
}
