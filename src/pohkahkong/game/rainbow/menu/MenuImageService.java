package pohkahkong.game.rainbow.menu;

import android.content.Context;
import android.graphics.Bitmap;
import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.bean.animation.PropertySpriteAnimation;
import pohkahkong.game.rainbow.bean.animation.UnlockableRandomPSA;
import pohkahkong.game.rainbow.bean.bitmapinfo.LoadBitmapInfo;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.ImageService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MenuImageService {
	private final float SLOW_FPS = 6.0f;
	private final float NORMAL_FPS = 8.0f;
	private final float FAST_FPS = 10.0f;
	
	private static MenuImageService instance = null;	
	private DimensionService dimensionService = DimensionService.getInstance();
	private ImageService imageService = ImageService.getInstance();
	
	private PropertySpriteAnimation rara;
	private PropertySpriteAnimation ahah;
	private PropertySpriteAnimation inin;
	private PropertySpriteAnimation nana;
	private PropertySpriteAnimation bobo;
	private PropertySpriteAnimation ohoh;
	private PropertySpriteAnimation wawa;
	private PropertySpriteAnimation rainbow;
	
	private PropertySpriteAnimation springObject;
	private PropertySpriteAnimation summerObject;
	private PropertySpriteAnimation autumnObject;
	private PropertySpriteAnimation winterObject;
	
	private MenuImageService() {
	}

	public static MenuImageService getInstance() {
		if (instance==null) {
			instance = new MenuImageService();
		}
		return instance;
	}
	
	public void init(Context context) {
		imageService.init(context);
	}
			
	public void loadBitmaps(final OnCompleteListener onCompleteListener) {
		final float size = dimensionService.getSize();
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[6];
		
		// main
		bitmapInfos[0] = new LoadBitmapInfo(R.drawable.main_player, (int) (size*6.0f), (int) (size*14.0f));
		bitmapInfos[1] = new LoadBitmapInfo(R.drawable.main_rainbow, (int) (size*6.0f), (int) (size*2.0f));
		
		// stage
		bitmapInfos[2] = new LoadBitmapInfo(R.drawable.stage_spring_object, (int) (size*2.0f), (int) size);
		bitmapInfos[3] = new LoadBitmapInfo(R.drawable.stage_summer_object, (int) (size*2.0f), (int) (size*2.0f));
		bitmapInfos[4] = new LoadBitmapInfo(R.drawable.stage_autumn_object, (int) (size*3.0f), (int) (size*3.0f));
		bitmapInfos[5] = new LoadBitmapInfo(R.drawable.stage_winter_object, (int) (size*0.5f), (int) (size*0.5f));
		imageService.loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				Bitmap[] bitmaps = (Bitmap[]) objects;
				// main
				rara = 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*0.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				ahah= 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*2.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				inin = 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*4.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				nana = 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*6.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				bobo = 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*8.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				ohoh = 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*10.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				wawa = 	new UnlockableRandomPSA(bitmaps[0], 0, (int) (size*12.0f), (int) (size*2.0f), (int) (size*2.0f), 3, SLOW_FPS, 0);
				rainbow = new PropertySpriteAnimation(bitmaps[1], 0, 0, (int) (size*6.0f), (int) (size*2.0f) , NORMAL_FPS);
				rainbow.setFading(true);
				
				// stage
				springObject = new PropertySpriteAnimation(bitmaps[2], 0, 0, (int) size, (int) size, 2, FAST_FPS);
				summerObject = new PropertySpriteAnimation(bitmaps[3], 0, 0, (int) (size*2.0f), (int) (size*2.0f), FAST_FPS);
				autumnObject = new PropertySpriteAnimation(bitmaps[4], 0, 0, (int) (size*3.0f), (int) (size*3.0f), FAST_FPS);
				winterObject = new PropertySpriteAnimation(bitmaps[5], 0, 0, (int) (size*0.5f), (int) (size*0.5f), FAST_FPS);
				winterObject.setRotating(true);
				onCompleteListener.onComplete(null);						
			}			
		});
	}
	
	public void release() {
		imageService.release();
	}

	// main activity
	public PropertySpriteAnimation getRara() {
		return rara;
	}

	public PropertySpriteAnimation getAhah() {
		return ahah;
	}

	public PropertySpriteAnimation getInin() {
		return inin;
	}

	public PropertySpriteAnimation getNana() {
		return nana;
	}

	public PropertySpriteAnimation getBobo() {
		return bobo;
	}

	public PropertySpriteAnimation getOhoh() {
		return ohoh;
	}

	public PropertySpriteAnimation getWawa() {
		return wawa;
	}

	public PropertySpriteAnimation getRainbow() {
		return rainbow;
	}	
	
	// stage activity
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
}
