// Name: 		Snowy
// Ability: 	Game Over Enemy 	
// Immunity: 	OhOh

package pohkahkong.game.rainbow.game.engine.object.enemy;

import pohkahkong.game.rainbow.game.GameImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class SnowyEGEO extends EnemyGEO {
	private GameImageService imagesDevice = GameImageService.getInstance();

	public SnowyEGEO(float x, float y, boolean isHorizontal, float distance) {
		super(x, y, isHorizontal, distance);
	}

	@Override
	public void load() {
		up = imagesDevice.getSnowyEnemyUp();
		down = imagesDevice.getSnowyEnemyDown();
		left = imagesDevice.getSnowyEnemyLeft();
		right = imagesDevice.getSnowyEnemyRight();
		super.load();
	}
}
