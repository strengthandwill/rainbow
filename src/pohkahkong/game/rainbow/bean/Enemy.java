package pohkahkong.game.rainbow.bean;

import pohkahkong.game.rainbow.game.engine.object.enemy.EnemiesGEO.Type;

/**
 * 
 * 
 * @author Poh Kah Kong
 *
 */
public class Enemy {
	private Type type;
	private float x;
	private float y;
	private boolean isHorizontal;
	private float distance;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public boolean isHorizontal() {
		return isHorizontal;
	}
	
	public void setHorizontal(boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}
	
	public float getDistance() {
		return distance;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
}
