package pohkahkong.game.rainbow.bean.bitmapinfo;

import android.graphics.Bitmap;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GetBitmapInfo implements BitmapInfo {
	private Bitmap bitmap;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public GetBitmapInfo(Bitmap bitmap, int x, int y, int width, int height) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}			
}
