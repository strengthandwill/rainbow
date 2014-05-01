package pohkahkong.game.rainbow.game.engine.object.enemy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pohkahkong.game.rainbow.bean.Enemy;
import pohkahkong.game.rainbow.service.XmlParserService;

import android.graphics.Canvas;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class EnemiesGEO {
	public static enum Type {LIGHTNY, RAINY, SNOWY, SUNNY};
	
	private XmlParserService xmlParserService = XmlParserService.getInstance();	
	
	private ArrayList <EnemyGEO> enemiesGEO = new ArrayList<EnemyGEO>();

	public EnemiesGEO() {
		List<Enemy> enemies = xmlParserService.getEnemies();
		for (int i=0; i<enemies.size(); i++) {
			Enemy enemy = enemies.get(i);					
			switch (enemy.getType()) {
			case SUNNY:
				enemiesGEO.add(new SunnyEGEO(enemy.getX(), enemy
						.getY(), enemy.isHorizontal(), enemy.getDistance()));
				break;
			case LIGHTNY:
				enemiesGEO.add(new LightnyEGEO(enemy.getX(), enemy
						.getY(), enemy.isHorizontal(), enemy.getDistance()));
				break;
			case RAINY:
				enemiesGEO.add(new RainyEGEO(enemy.getX(), enemy
						.getY(), enemy.isHorizontal(), enemy.getDistance()));				
				break;
			case SNOWY:
				enemiesGEO.add(new SnowyEGEO(enemy.getX(), enemy
						.getY(), enemy.isHorizontal(), enemy.getDistance()));
				break;
			default:
				break;			
			}
		}
	}
	
	// game engine states
	public void pause() {
		Iterator<EnemyGEO> iterator = enemiesGEO.iterator();
		while (iterator.hasNext()) {
			iterator.next().pause();
		}		
	}

	public void play() {
		Iterator<EnemyGEO> iterator = enemiesGEO.iterator();
		while (iterator.hasNext()) {
			iterator.next().play();
		}
	}

	public void stop() {
		pause();
	}
	
	// game engine events
	public void load() {
		Iterator<EnemyGEO> iterator = enemiesGEO.iterator();
		while (iterator.hasNext()) {
			iterator.next().load();
		}				
	}
	
	public void update(long newTime) {
		Iterator<EnemyGEO> iterator = enemiesGEO.iterator();
		while (iterator.hasNext()) {
			iterator.next().update(newTime);
		}		
	}	
	
	public void draw(Canvas canvas) {
		Iterator<EnemyGEO> iterator = enemiesGEO.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(canvas);
		}		
	}
	
	public Type detectCollision(int objX, int objY) {
		Iterator<EnemyGEO> iterator = enemiesGEO.iterator();
		while (iterator.hasNext()) {
			EnemyGEO enemy = iterator.next();			
			if (enemy.detectCollision(objX, objY)) {
				if (enemy instanceof SunnyEGEO) {
					return Type.SUNNY;
				} else if (enemy instanceof LightnyEGEO) {
					return Type.LIGHTNY;
				} if (enemy instanceof RainyEGEO) {
					return Type.RAINY;
				} if (enemy instanceof SnowyEGEO) {
					return Type.SNOWY;
				}
			}
		}
		return null;
	}	
}
