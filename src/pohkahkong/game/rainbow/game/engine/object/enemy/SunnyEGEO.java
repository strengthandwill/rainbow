// Name: 		Sunny
// Ability: 	Slow	
// Immunity: 	AhAh

package pohkahkong.game.rainbow.game.engine.object.enemy;

import pohkahkong.game.rainbow.game.GameImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class SunnyEGEO extends EnemyGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();
	
	public SunnyEGEO(float x, float y, boolean isHorizontal, float distance) {
		super(x, y, isHorizontal, distance);
	}

	@Override
	public void load() {
		up = imagesDevice.getSunnyEnemyUp();
		down = imagesDevice.getSunnyEnemyDown();
		left = imagesDevice.getSunnyEnemyLeft();
		right = imagesDevice.getSunnyEnemyRight();
		super.load();
	}
}