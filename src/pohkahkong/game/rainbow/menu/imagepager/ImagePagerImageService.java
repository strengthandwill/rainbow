package pohkahkong.game.rainbow.menu.imagepager;

import android.content.Context;
import pohkahkong.game.rainbow.bean.bitmapinfo.LoadBitmapInfo;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.ImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class ImagePagerImageService {
	private static ImagePagerImageService instance = null;	
	private DimensionService dimensionService = DimensionService.getInstance();
	private ImageService imageService = ImageService.getInstance();
	
	private Integer[] imageResIds;
	
	private ImagePagerImageService() {		
	}
	
	public static ImagePagerImageService getInstance() {
		if (instance==null) {
			instance = new ImagePagerImageService();
		}
		return instance;
	}
	
	public void init(Context context, Integer[] imageResIds) {
		this.imageResIds = imageResIds;
		imageService.init(context);
	}
	
	public int getCount() {
		return imageResIds.length;
	}
	
	public void loadBitmap(int imageNum, final OnCompleteListener onCompleteListener) {
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[1];		
		bitmapInfos[0] = new LoadBitmapInfo(imageResIds[imageNum],
				(int) dimensionService.getWindowWidth(),
				(int) (dimensionService.getWindowHeight() * 0.8f));
		imageService.loadBitmaps(bitmapInfos, onCompleteListener);
	}
	
	public void release() {
		imageService.release();
	}
}
