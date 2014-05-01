package pohkahkong.game.rainbow.menu.stage;

import android.content.Context;
import android.graphics.Bitmap;
import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.bean.animation.PropertySpriteAnimation;
import pohkahkong.game.rainbow.bean.bitmapinfo.LoadBitmapInfo;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.ImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class StageImageService {
	private final float NORMAL_FPS = 8.0f;	
	private static StageImageService instance = null;	
	private DimensionService dimensionService = DimensionService.getInstance();
	private ImageService imageService = ImageService.getInstance();
	
	private PropertySpriteAnimation springObject;
	private PropertySpriteAnimation summerObject;
	private PropertySpriteAnimation autumnObject;
	private PropertySpriteAnimation winterObject;
	
	private StageImageService() {
	}

	public static StageImageService getInstance() {
		if (instance==null) {
			instance = new StageImageService();
		}
		return instance;
	}
	
	public void init(Context context) {
		imageService.init(context);
	}
			
	public void loadBitmaps(final OnCompleteListener onCompleteListener) {
		final float size = dimensionService.getSize();
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[4];
		bitmapInfos[0] = new LoadBitmapInfo(R.drawable.stage_spring_object, (int) (size*2.0f), (int) size);
		bitmapInfos[1] = new LoadBitmapInfo(R.drawable.stage_summer_object, (int) (size*2.0f), (int) (size*2.0f));
		bitmapInfos[2] = new LoadBitmapInfo(R.drawable.stage_autumn_object, (int) (size*3.0f), (int) (size*3.0f));
		bitmapInfos[3] = new LoadBitmapInfo(R.drawable.stage_winter_object, (int) (size*0.5f), (int) (size*0.5f));
		imageService.loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				Bitmap[] bitmaps = (Bitmap[]) objects;
				springObject = new PropertySpriteAnimation(bitmaps[0], 0, 0, (int) size, (int) size, 2, NORMAL_FPS);
				summerObject = new PropertySpriteAnimation(bitmaps[1], 0, 0, (int) (size*2.0f), (int) (size*2.0f), NORMAL_FPS);
				autumnObject = new PropertySpriteAnimation(bitmaps[2], 0, 0, (int) (size*3.0f), (int) (size*3.0f), NORMAL_FPS);
				winterObject = new PropertySpriteAnimation(bitmaps[3], 0, 0, (int) (size*0.5f), (int) (size*0.5f), NORMAL_FPS);
				winterObject.setRotating(true);
				onCompleteListener.onComplete(null);									
			}			
		});
	}

	public Animation getSpringObject() {
		return springObject;
	}
	
	public Animation getSummerObject() {
		return summerObject;
	}
	
	public Animation getAutumnObject() {
		return autumnObject;
	}
	
	public Animation getWinterObject() {
		return winterObject;
	}
	
	public void release() {
		imageService.release();
	}
}
