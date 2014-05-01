package pohkahkong.game.rainbow.bean.bitmapinfo;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class LoadBitmapInfo implements BitmapInfo {
	private int id;
	private int width;
	private int height;
	
	public LoadBitmapInfo(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public int getId() {
		return id;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}			
}
