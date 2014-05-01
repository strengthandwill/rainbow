package pohkahkong.game.rainbow.game;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.bean.animation.RandomPSA;
import pohkahkong.game.rainbow.bean.animation.RandomSetPSA;
import pohkahkong.game.rainbow.bean.animation.PropertySpriteAnimation;
import pohkahkong.game.rainbow.bean.bitmapinfo.GetBitmapInfo;
import pohkahkong.game.rainbow.bean.bitmapinfo.LoadBitmapInfo;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.ImageService;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameImageService {
	private final float FAST_FPS = 8.0f;
	private final float NORMAL_FPS = 5.0f;
	private final float SLOW_FPS = 3.0f;
	
	private static GameImageService instance = null;	
	private ImageService imageService = ImageService.getInstance();
	private DimensionService dimensionService = DimensionService.getInstance();
	private int season;
	private int level;
	private int loadingCounter;
	private OnCompleteListener onLoadingCompleteListener;

	// map
	private Bitmap mapRoad;	
	private Bitmap[] mapOutside = new Bitmap[5];
	
	private PropertySpriteAnimation sunnyEnemyUp;
	private PropertySpriteAnimation sunnyEnemyDown;
	private PropertySpriteAnimation sunnyEnemyLeft;
	private PropertySpriteAnimation sunnyEnemyRight;
	private PropertySpriteAnimation lightnyEnemyUp;
	private PropertySpriteAnimation lightnyEnemyDown;
	private PropertySpriteAnimation lightnyEnemyLeft;
	private PropertySpriteAnimation lightnyEnemyRight;
	private PropertySpriteAnimation rainyEnemyUp;
	private PropertySpriteAnimation rainyEnemyDown;
	private PropertySpriteAnimation rainyEnemyLeft;
	private PropertySpriteAnimation rainyEnemyRight;
	private PropertySpriteAnimation snowyEnemyUp;
	private PropertySpriteAnimation snowyEnemyDown;
	private PropertySpriteAnimation snowyEnemyLeft;
	private PropertySpriteAnimation snowyEnemyRight;	

	// player rara
	private PropertySpriteAnimation raraPlayerNormalUp;
	private PropertySpriteAnimation raraPlayerNormalDown;
	private PropertySpriteAnimation raraPlayerNormalLeft;
	private PropertySpriteAnimation raraPlayerNormalRight;
	private PropertySpriteAnimation raraPlayerNormalBlink;
	private PropertySpriteAnimation raraPlayerSlowUp;
	private PropertySpriteAnimation raraPlayerSlowDown;
	private PropertySpriteAnimation raraPlayerSlowLeft;
	private PropertySpriteAnimation raraPlayerSlowRight;		
	private PropertySpriteAnimation raraPlayerSlowBlink;
	private PropertySpriteAnimation raraPlayerStun;
	private PropertySpriteAnimation raraPlayerSlip;
	private PropertySpriteAnimation raraPlayerGameOver;

	// player ahah
	private PropertySpriteAnimation ahahPlayerNormalUp;
	private PropertySpriteAnimation ahahPlayerNormalDown;
	private PropertySpriteAnimation ahahPlayerNormalLeft;
	private PropertySpriteAnimation ahahPlayerNormalRight;
	private PropertySpriteAnimation ahahPlayerNormalBlink;
	private PropertySpriteAnimation ahahPlayerSlowUp;
	private PropertySpriteAnimation ahahPlayerSlowDown;
	private PropertySpriteAnimation ahahPlayerSlowLeft;
	private PropertySpriteAnimation ahahPlayerSlowRight;		
	private PropertySpriteAnimation ahahPlayerSlowBlink;	
	private PropertySpriteAnimation ahahPlayerStun;
	private PropertySpriteAnimation ahahPlayerSlip;
	private PropertySpriteAnimation ahahPlayerGameOver;

	// player inin
	private PropertySpriteAnimation ininPlayerNormalUp;
	private PropertySpriteAnimation ininPlayerNormalDown;
	private PropertySpriteAnimation ininPlayerNormalLeft;
	private PropertySpriteAnimation ininPlayerNormalRight;
	private PropertySpriteAnimation ininPlayerNormalBlink;
	private PropertySpriteAnimation ininPlayerSlowUp;
	private PropertySpriteAnimation ininPlayerSlowDown;
	private PropertySpriteAnimation ininPlayerSlowLeft;
	private PropertySpriteAnimation ininPlayerSlowRight;		
	private PropertySpriteAnimation ininPlayerSlowBlink;
	private PropertySpriteAnimation ininPlayerSlip;
	private PropertySpriteAnimation ininPlayerGameOver;

	// player nana
	private PropertySpriteAnimation nanaPlayerNormalUp;
	private PropertySpriteAnimation nanaPlayerNormalDown;
	private PropertySpriteAnimation nanaPlayerNormalLeft;
	private PropertySpriteAnimation nanaPlayerNormalRight;
	private PropertySpriteAnimation nanaPlayerNormalBlink;
	private PropertySpriteAnimation nanaPlayerSlowUp;
	private PropertySpriteAnimation nanaPlayerSlowDown;
	private PropertySpriteAnimation nanaPlayerSlowLeft;
	private PropertySpriteAnimation nanaPlayerSlowRight;		
	private PropertySpriteAnimation nanaPlayerSlowBlink;
	private PropertySpriteAnimation nanaPlayerJumpUp;
	private PropertySpriteAnimation nanaPlayerJumpDown;
	private PropertySpriteAnimation nanaPlayerJumpLeft;
	private PropertySpriteAnimation nanaPlayerJumpRight;			
	private PropertySpriteAnimation nanaPlayerStun;
	private PropertySpriteAnimation nanaPlayerSlip;
	private PropertySpriteAnimation nanaPlayerGameOver;	

	// player bobo
	private PropertySpriteAnimation boboPlayerNormalUp;
	private PropertySpriteAnimation boboPlayerNormalDown;
	private PropertySpriteAnimation boboPlayerNormalLeft;
	private PropertySpriteAnimation boboPlayerNormalRight;
	private PropertySpriteAnimation boboPlayerNormalBlink;
	private PropertySpriteAnimation boboPlayerSlowUp;
	private PropertySpriteAnimation boboPlayerSlowDown;
	private PropertySpriteAnimation boboPlayerSlowLeft;
	private PropertySpriteAnimation boboPlayerSlowRight;		
	private PropertySpriteAnimation boboPlayerSlowBlink;		
	private PropertySpriteAnimation boboPlayerStun;
	private PropertySpriteAnimation boboPlayerGameOver;

	// player ohoh
	private PropertySpriteAnimation ohohPlayerNormalUp;
	private PropertySpriteAnimation ohohPlayerNormalDown;
	private PropertySpriteAnimation ohohPlayerNormalLeft;
	private PropertySpriteAnimation ohohPlayerNormalRight;
	private PropertySpriteAnimation ohohPlayerNormalBlink;
	private PropertySpriteAnimation ohohPlayerSlowUp;
	private PropertySpriteAnimation ohohPlayerSlowDown;
	private PropertySpriteAnimation ohohPlayerSlowLeft;
	private PropertySpriteAnimation ohohPlayerSlowRight;		
	private PropertySpriteAnimation ohohPlayerSlowBlink;		
	private PropertySpriteAnimation ohohPlayerStun;
	private PropertySpriteAnimation ohohPlayerSlip;
	private PropertySpriteAnimation ohohPlayerGameOver;

	// player wawa
	private PropertySpriteAnimation wawaPlayerNormalUp;
	private PropertySpriteAnimation wawaPlayerNormalDown;
	private PropertySpriteAnimation wawaPlayerNormalLeft;
	private PropertySpriteAnimation wawaPlayerNormalRight;
	private PropertySpriteAnimation wawaPlayerNormalBlink;
	private PropertySpriteAnimation wawaPlayerGameOver;	
	
	// rainbow
	private PropertySpriteAnimation rainbow;

	// gamewait
	private Bitmap gameWaitInstruction;
		
	private GameImageService() {
	}

	public static GameImageService getInstance() {
		if (instance==null) {
			instance = new GameImageService();
		}
		return instance;
	}
	
	public void init(Context context, int season, int level) {
		imageService.init(context);
		this.season = season;
		this.level = level;
	}
	
	public void release() {
		imageService.release();
		gameWaitInstruction = null;
	}

	public void loadBitmaps(OnCompleteListener onLoadingCompleteListener) {
		float size = dimensionService.getSize();
		this.onLoadingCompleteListener = onLoadingCompleteListener;
		loadingCounter = 0;
		loadMap(size);
		loadRainbow(size);
		loadPlayer(size);
		loadEnemies(size);
		loadGameWait(size);
	}
	
	private void loadMap(final float size) {		
		LoadBitmapInfo[] loadBitmapInfos = new LoadBitmapInfo[1];
		loadBitmapInfos[0] = new LoadBitmapInfo(R.drawable.game_map, (int) size*5, (int) size*5);		
		loadBitmaps(loadBitmapInfos, new OnCompleteListener() {
			public void onComplete(Object[] objects) {								
				final Bitmap bitmap = ((Bitmap[]) objects)[0];
				GetBitmapInfo[] getBitmapInfos = new GetBitmapInfo[6];
				getBitmapInfos[0] = new GetBitmapInfo(bitmap, 0, (int) size*4, (int) size, (int) size);
				for (int i=1; i<getBitmapInfos.length; i++) {
					getBitmapInfos[i] = new GetBitmapInfo(bitmap, (int) size*(i-1), (int) size*(season-1), (int) size, (int) size);
				}
				getBitmaps(getBitmapInfos, new OnCompleteListener() {
					public void onComplete(Object[] objects2) {
						Bitmap[] bitmaps2 = (Bitmap[]) objects2;
						mapRoad = bitmaps2[0];
						for (int i=1; i<bitmaps2.length; i++) {
							mapOutside[i-1] = bitmaps2[i];
						}
						bitmap.recycle();
					}					
				});
			}			
		});
	}

	private void loadRainbow(final float size) {
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[1];
		bitmapInfos[0] = new LoadBitmapInfo(R.drawable.game_rainbow, (int) size*3, (int) size);
		loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				Bitmap bitmap = ((Bitmap[]) objects)[0];
				rainbow = new PropertySpriteAnimation(bitmap, 0, 0, (int) size*3, (int) size , NORMAL_FPS);
				rainbow.setFading(true);
			}			
		});		
	}
	
	private void loadEnemies(final float size) {
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[4];
		bitmapInfos[0] = new LoadBitmapInfo(R.drawable.enemy_sunny, (int) size*2, (int) size*4);
		bitmapInfos[1] = new LoadBitmapInfo(R.drawable.enemy_lightny, (int) size*2, (int) size*4);
		bitmapInfos[2] = new LoadBitmapInfo(R.drawable.enemy_rainy, (int) size*2, (int) size*4);
		bitmapInfos[3] = new LoadBitmapInfo(R.drawable.enemy_snowy, (int) size*2, (int) size*4);
		loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				Bitmap[] bitmaps = (Bitmap[]) objects;
				
				sunnyEnemyUp = 		new PropertySpriteAnimation(bitmaps[0], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS);
				sunnyEnemyDown = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				sunnyEnemyLeft = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				sunnyEnemyRight = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				
				lightnyEnemyUp = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS);				
				lightnyEnemyDown = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				lightnyEnemyLeft = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				lightnyEnemyRight = new PropertySpriteAnimation(bitmaps[1], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				
				rainyEnemyUp = 		new PropertySpriteAnimation(bitmaps[2], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS);
				rainyEnemyDown = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);				
				rainyEnemyLeft = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				rainyEnemyRight = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				
				snowyEnemyUp = 		new PropertySpriteAnimation(bitmaps[3], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS);
				snowyEnemyDown = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				snowyEnemyLeft = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				snowyEnemyRight = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
			}			
		});		
	}
	
	private void loadPlayer(final float size) {
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[7];
		bitmapInfos[0] = new LoadBitmapInfo(R.drawable.player_rara, (int) size*2, (int) size*13);
		bitmapInfos[1] = new LoadBitmapInfo(R.drawable.player_ahah, (int) size*2, (int) size*13);
		bitmapInfos[2] = new LoadBitmapInfo(R.drawable.player_inin, (int) size*2, (int) size*12);
		bitmapInfos[3] = new LoadBitmapInfo(R.drawable.player_nana, (int) size*2, (int) size*17);
		bitmapInfos[4] = new LoadBitmapInfo(R.drawable.player_bobo, (int) size*2, (int) size*12);
		bitmapInfos[5] = new LoadBitmapInfo(R.drawable.player_ohoh, (int) size*2, (int) size*13);
		bitmapInfos[6] = new LoadBitmapInfo(R.drawable.player_wawa, (int) size*4, (int) size*6);
		
		loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				Bitmap[] bitmaps = (Bitmap[]) objects;
				
				raraPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*0, (int) size, (int) size, 2, FAST_FPS); 
				raraPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*1, (int) size, (int) size, 2, FAST_FPS);
				raraPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*2, (int) size, (int) size, 2, FAST_FPS);
				raraPlayerNormalRight = new PropertySpriteAnimation(bitmaps[0], 0, (int) size*3, (int) size, (int) size, 2, FAST_FPS);
				raraPlayerNormalBlink = new RandomPSA(bitmaps[0], 0, (int) size*4, (int) size, (int) size, 2, FAST_FPS, 0);
				raraPlayerSlowUp = 		new PropertySpriteAnimation(bitmaps[0], 0, (int) size*5, (int) size, (int) size, 2, SLOW_FPS);
				raraPlayerSlowDown = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*6, (int) size, (int) size, 2, SLOW_FPS);
				raraPlayerSlowLeft = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*7, (int) size, (int) size, 2, SLOW_FPS);
				raraPlayerSlowRight = 	new PropertySpriteAnimation(bitmaps[0], 0, (int) size*8, (int) size, (int) size, 2, SLOW_FPS);
				raraPlayerSlowBlink = 	new RandomPSA(bitmaps[0], 0, (int) size*9, (int) size, (int) size, 2, SLOW_FPS, 0);
				raraPlayerStun = 		new RandomPSA(bitmaps[0], 0, (int) size*10, (int) size, (int) size, 2, NORMAL_FPS, 0);
				raraPlayerSlip = 		new RandomPSA(bitmaps[0], 0, (int) size*11, (int) size, (int) size, 2, NORMAL_FPS, 0);
				raraPlayerGameOver = 	new RandomPSA(bitmaps[0], 0, (int) size*12, (int) size, (int) size, 2, NORMAL_FPS, 0);
				
				ahahPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS); 
				ahahPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				ahahPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				ahahPlayerNormalRight = new PropertySpriteAnimation(bitmaps[1], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				ahahPlayerNormalBlink = new RandomPSA(bitmaps[1], 0, (int) size*4, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ahahPlayerSlowUp = 		new PropertySpriteAnimation(bitmaps[1], 0, (int) size*5, (int) size, (int) size, 2, SLOW_FPS);
				ahahPlayerSlowDown = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*6, (int) size, (int) size, 2, SLOW_FPS);
				ahahPlayerSlowLeft = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*7, (int) size, (int) size, 2, SLOW_FPS);
				ahahPlayerSlowRight = 	new PropertySpriteAnimation(bitmaps[1], 0, (int) size*8, (int) size, (int) size, 2, SLOW_FPS);
				ahahPlayerSlowBlink = 	new RandomPSA(bitmaps[1], 0, (int) size*9, (int) size, (int) size, 2, SLOW_FPS, 0);
				ahahPlayerStun = 		new RandomPSA(bitmaps[1], 0, (int) size*10, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ahahPlayerSlip = 		new RandomPSA(bitmaps[1], 0, (int) size*11, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ahahPlayerGameOver = 	new RandomPSA(bitmaps[1], 0, (int) size*12, (int) size, (int) size, 2, NORMAL_FPS, 0);
				
				ininPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS); 
				ininPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				ininPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				ininPlayerNormalRight = new PropertySpriteAnimation(bitmaps[2], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				ininPlayerNormalBlink = new RandomPSA(bitmaps[2], 0, (int) size*4, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ininPlayerSlowUp = 		new PropertySpriteAnimation(bitmaps[2], 0, (int) size*5, (int) size, (int) size, 2, SLOW_FPS);
				ininPlayerSlowDown = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*6, (int) size, (int) size, 2, SLOW_FPS);
				ininPlayerSlowLeft = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*7, (int) size, (int) size, 2, SLOW_FPS);
				ininPlayerSlowRight = 	new PropertySpriteAnimation(bitmaps[2], 0, (int) size*8, (int) size, (int) size, 2, SLOW_FPS);
				ininPlayerSlowBlink = 	new RandomPSA(bitmaps[2], 0, (int) size*9, (int) size, (int) size, 2, SLOW_FPS, 0);
				ininPlayerSlip = 		new RandomPSA(bitmaps[2], 0, (int) size*10, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ininPlayerGameOver = 	new RandomPSA(bitmaps[2], 0, (int) size*11, (int) size, (int) size, 2, NORMAL_FPS, 0);
				
				nanaPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS); 
				nanaPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				nanaPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				nanaPlayerNormalRight = new PropertySpriteAnimation(bitmaps[3], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				nanaPlayerNormalBlink = new RandomPSA(bitmaps[3], 0, (int) size*4, (int) size, (int) size, 2, NORMAL_FPS, 0);
				nanaPlayerSlowUp = 		new PropertySpriteAnimation(bitmaps[3], 0, (int) size*5, (int) size, (int) size, 2, SLOW_FPS);
				nanaPlayerSlowDown = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*6, (int) size, (int) size, 2, SLOW_FPS);
				nanaPlayerSlowLeft = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*7, (int) size, (int) size, 2, SLOW_FPS);
				nanaPlayerSlowRight = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*8, (int) size, (int) size, 2, SLOW_FPS);
				nanaPlayerSlowBlink = 	new RandomPSA(bitmaps[3], 0, (int) size*9, (int) size, (int) size, 2, SLOW_FPS, 0);
				nanaPlayerJumpUp = 		new PropertySpriteAnimation(bitmaps[3], 0, (int) size*10, (int) size, (int) size, 2, NORMAL_FPS); 
				nanaPlayerJumpDown = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*11, (int) size, (int) size, 2, NORMAL_FPS);
				nanaPlayerJumpLeft = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*12, (int) size, (int) size, 2, NORMAL_FPS);
				nanaPlayerJumpRight = 	new PropertySpriteAnimation(bitmaps[3], 0, (int) size*13, (int) size, (int) size, 2, NORMAL_FPS);
				nanaPlayerStun = 		new RandomPSA(bitmaps[3], 0, (int) size*14, (int) size, (int) size, 2, NORMAL_FPS, 0);
				nanaPlayerSlip = 		new RandomPSA(bitmaps[3], 0, (int) size*15, (int) size, (int) size, 2, NORMAL_FPS, 0);
				nanaPlayerGameOver = 	new RandomPSA(bitmaps[3], 0, (int) size*16, (int) size, (int) size, 2, NORMAL_FPS, 0);
				
				boboPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[4], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS); 
				boboPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[4], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				boboPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[4], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				boboPlayerNormalRight = new PropertySpriteAnimation(bitmaps[4], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				boboPlayerNormalBlink = new RandomPSA(bitmaps[4], 0, (int) size*4, (int) size, (int) size, 2, NORMAL_FPS, 0);
				boboPlayerSlowUp = 		new PropertySpriteAnimation(bitmaps[4], 0, (int) size*5, (int) size, (int) size, 2, SLOW_FPS);
				boboPlayerSlowDown = 	new PropertySpriteAnimation(bitmaps[4], 0, (int) size*6, (int) size, (int) size, 2, SLOW_FPS);
				boboPlayerSlowLeft = 	new PropertySpriteAnimation(bitmaps[4], 0, (int) size*7, (int) size, (int) size, 2, SLOW_FPS);
				boboPlayerSlowRight = 	new PropertySpriteAnimation(bitmaps[4], 0, (int) size*8, (int) size, (int) size, 2, SLOW_FPS);
				boboPlayerSlowBlink = 	new RandomPSA(bitmaps[4], 0, (int) size*9, (int) size, (int) size, 2, SLOW_FPS, 0);
				boboPlayerStun = 		new RandomPSA(bitmaps[4], 0, (int) size*10, (int) size, (int) size, 2, NORMAL_FPS, 0);
				boboPlayerGameOver = 	new RandomPSA(bitmaps[4], 0, (int) size*11, (int) size, (int) size, 2, NORMAL_FPS, 0);
				
				ohohPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[5], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS); 
				ohohPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[5], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				ohohPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[5], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				ohohPlayerNormalRight = new PropertySpriteAnimation(bitmaps[5], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				ohohPlayerNormalBlink = new RandomPSA(bitmaps[5], 0, (int) size*4, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ohohPlayerSlowUp = 		new PropertySpriteAnimation(bitmaps[5], 0, (int) size*5, (int) size, (int) size, 2, SLOW_FPS);
				ohohPlayerSlowDown = 	new PropertySpriteAnimation(bitmaps[5], 0, (int) size*6, (int) size, (int) size, 2, SLOW_FPS);
				ohohPlayerSlowLeft = 	new PropertySpriteAnimation(bitmaps[5], 0, (int) size*7, (int) size, (int) size, 2, SLOW_FPS);
				ohohPlayerSlowRight = 	new PropertySpriteAnimation(bitmaps[5], 0, (int) size*8, (int) size, (int) size, 2, SLOW_FPS);
				ohohPlayerSlowBlink = 	new RandomPSA(bitmaps[5], 0, (int) size*9, (int) size, (int) size, 2, SLOW_FPS, 0);
				ohohPlayerStun = 		new RandomPSA(bitmaps[5], 0, (int) size*10, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ohohPlayerSlip = 		new RandomPSA(bitmaps[5], 0, (int) size*11, (int) size, (int) size, 2, NORMAL_FPS, 0);
				ohohPlayerGameOver = 	new RandomPSA(bitmaps[5], 0, (int) size*12, (int) size, (int) size, 2, NORMAL_FPS, 0);
				
				wawaPlayerNormalUp = 	new PropertySpriteAnimation(bitmaps[6], 0, (int) size*0, (int) size, (int) size, 2, NORMAL_FPS); 
				wawaPlayerNormalDown = 	new PropertySpriteAnimation(bitmaps[6], 0, (int) size*1, (int) size, (int) size, 2, NORMAL_FPS);
				wawaPlayerNormalLeft = 	new PropertySpriteAnimation(bitmaps[6], 0, (int) size*2, (int) size, (int) size, 2, NORMAL_FPS);
				wawaPlayerNormalRight = new PropertySpriteAnimation(bitmaps[6], 0, (int) size*3, (int) size, (int) size, 2, NORMAL_FPS);
				wawaPlayerNormalBlink = new RandomSetPSA(bitmaps[6], 0, (int) size*4, (int) size, (int) size, 2, 2, NORMAL_FPS, 0);
				wawaPlayerGameOver = 	new RandomSetPSA(bitmaps[6], 0, (int) size*5, (int) size, (int) size, 2, 2, NORMAL_FPS, 0);
			}			
		});		
	}	

	private int getInstructionBitmapId() {
		int id = -1;
		if (season==1 && level==1)
			id = R.drawable.game_gamewait_instructions_rara;
		else if (season==1 && level==6)
			id = R.drawable.game_gamewait_instructions_sunny;
		else if (season==1 && level==11)
			id = R.drawable.game_gamewait_instructions_ahah;
		else if (season==2 && level==1)
			id = R.drawable.game_gamewait_instructions_lightny;
		else if (season==2 && level==6)
			id = R.drawable.game_gamewait_instructions_inin;
		else if (season==2 && level==16)
			id = R.drawable.game_gamewait_instructions_nana;
		else if (season==3 && level==1)
			id = R.drawable.game_gamewait_instructions_rainy;
		else if (season==3 && level==6)
			id = R.drawable.game_gamewait_instructions_bobo;
		else if (season==4 && level==1)
			id = R.drawable.game_gamewait_instructions_snowy;
		else if (season==4 && level==6)
			id = R.drawable.game_gamewait_instructions_ohoh;
		else if (season==4 && level==16)
			id = R.drawable.game_gamewait_instructions_wawa;		
		return id;
	}

	private void loadGameWait(float size) {
		int id = getInstructionBitmapId();
		if (id==-1) {
			return;
		}
		LoadBitmapInfo[] bitmapInfos = new LoadBitmapInfo[1];	
		bitmapInfos[0] = new LoadBitmapInfo(id, (int) size*10, (int) size*17);	
		loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				Bitmap bitmap = ((Bitmap[]) objects)[0];
				gameWaitInstruction = bitmap;
			}			
		});				
	}
	
	private void loadBitmaps(LoadBitmapInfo[] bitmapInfos,
			final OnCompleteListener onCompleteListener) {
		loadingCounter++;
		imageService.loadBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				onCompleteListener.onComplete(objects);
				loadingCounter--;
				if (loadingCounter==0) {
					onLoadingCompleteListener.onComplete(null);					
				}	
			}			
		});
	}
	
	private void getBitmaps(GetBitmapInfo[] bitmapInfos,
			final OnCompleteListener onCompleteListener) {
		loadingCounter++;
		imageService.getBitmaps(bitmapInfos, new OnCompleteListener() {			
			public void onComplete(Object[] objects) {
				onCompleteListener.onComplete(objects);
				loadingCounter--;
				if (loadingCounter==0) {
					onLoadingCompleteListener.onComplete(null);					
				}	
			}			
		});
	}


	// get bitmaps
	public Bitmap getMapRoad() {
		return mapRoad;
	}

	public Bitmap[] getMapOutside() {
		return mapOutside;
	}

	public Animation getRainbow() {
		return rainbow;
	}

	public Bitmap getGameWaitInstruction() {
		return gameWaitInstruction;
	}
	
	// get sprites
	public Animation getSunnyEnemyUp() {
		return sunnyEnemyUp.clone();
	}

	public Animation getSunnyEnemyDown() {
		return sunnyEnemyDown.clone();
	}

	public Animation getSunnyEnemyLeft() {
		return sunnyEnemyLeft.clone();
	}

	public Animation getSunnyEnemyRight() {
		return sunnyEnemyRight.clone();
	}

	public Animation getLightnyEnemyUp() {
		return lightnyEnemyUp.clone();
	}

	public Animation getLightnyEnemyDown() {
		return lightnyEnemyDown.clone();
	}

	public Animation getLightnyEnemyLeft() {
		return lightnyEnemyLeft.clone();
	}

	public Animation getLightnyEnemyRight() {
		return lightnyEnemyRight.clone();
	}

	public Animation getRainyEnemyUp() {
		return rainyEnemyUp.clone();
	}

	public Animation getRainyEnemyDown() {
		return rainyEnemyDown.clone();
	}

	public Animation getRainyEnemyLeft() {
		return rainyEnemyLeft.clone();
	}

	public Animation getRainyEnemyRight() {
		return rainyEnemyRight.clone();
	}

	public Animation getSnowyEnemyUp() {
		return snowyEnemyUp.clone();
	}

	public Animation getSnowyEnemyDown() {
		return snowyEnemyDown.clone();
	}

	public Animation getSnowyEnemyLeft() {
		return snowyEnemyLeft.clone();
	}

	public Animation getSnowyEnemyRight() {
		return snowyEnemyRight.clone();
	}

	public ImageService getImageService() {
		return imageService;
	}

	public Animation getRaraPlayerNormalUp() {
		return raraPlayerNormalUp;
	}

	public Animation getRaraPlayerNormalDown() {
		return raraPlayerNormalDown;
	}

	public Animation getRaraPlayerNormalLeft() {
		return raraPlayerNormalLeft;
	}

	public Animation getRaraPlayerNormalRight() {
		return raraPlayerNormalRight;
	}

	public Animation getRaraPlayerNormalBlink() {
		return raraPlayerNormalBlink;
	}

	public Animation getRaraPlayerSlowUp() {
		return raraPlayerSlowUp;
	}

	public Animation getRaraPlayerSlowDown() {
		return raraPlayerSlowDown;
	}

	public Animation getRaraPlayerSlowLeft() {
		return raraPlayerSlowLeft;
	}

	public Animation getRaraPlayerSlowRight() {
		return raraPlayerSlowRight;
	}

	public Animation getRaraPlayerSlowBlink() {
		return raraPlayerSlowBlink;
	}

	public Animation getRaraPlayerStun() {
		return raraPlayerStun;
	}

	public Animation getRaraPlayerSlip() {
		return raraPlayerSlip;
	}

	public Animation getRaraPlayerGameOver() {
		return raraPlayerGameOver;
	}

	public Animation getAhahPlayerNormalUp() {
		return ahahPlayerNormalUp;
	}

	public Animation getAhahPlayerNormalDown() {
		return ahahPlayerNormalDown;
	}

	public Animation getAhahPlayerNormalLeft() {
		return ahahPlayerNormalLeft;
	}

	public Animation getAhahPlayerNormalRight() {
		return ahahPlayerNormalRight;
	}

	public Animation getAhahPlayerNormalBlink() {
		return ahahPlayerNormalBlink;
	}

	public Animation getAhahPlayerSlowUp() {
		return ahahPlayerSlowUp;
	}

	public Animation getAhahPlayerSlowDown() {
		return ahahPlayerSlowDown;
	}

	public Animation getAhahPlayerSlowLeft() {
		return ahahPlayerSlowLeft;
	}

	public Animation getAhahPlayerSlowRight() {
		return ahahPlayerSlowRight;
	}

	public Animation getAhahPlayerSlowBlink() {
		return ahahPlayerSlowBlink;
	}

	public Animation getAhahPlayerStun() {
		return ahahPlayerStun;
	}

	public Animation getAhahPlayerSlip() {
		return ahahPlayerSlip;
	}

	public Animation getAhahPlayerGameOver() {
		return ahahPlayerGameOver;
	}

	public Animation getIninPlayerNormalUp() {
		return ininPlayerNormalUp;
	}

	public Animation getIninPlayerNormalDown() {
		return ininPlayerNormalDown;
	}

	public Animation getIninPlayerNormalLeft() {
		return ininPlayerNormalLeft;
	}

	public Animation getIninPlayerNormalRight() {
		return ininPlayerNormalRight;
	}

	public Animation getIninPlayerNormalBlink() {
		return ininPlayerNormalBlink;
	}

	public Animation getIninPlayerSlowUp() {
		return ininPlayerSlowUp;
	}

	public Animation getIninPlayerSlowDown() {
		return ininPlayerSlowDown;
	}

	public Animation getIninPlayerSlowLeft() {
		return ininPlayerSlowLeft;
	}

	public Animation getIninPlayerSlowRight() {
		return ininPlayerSlowRight;
	}

	public Animation getIninPlayerSlowBlink() {
		return ininPlayerSlowBlink;
	}

	public Animation getIninPlayerSlip() {
		return ininPlayerSlip;
	}

	public Animation getIninPlayerGameOver() {
		return ininPlayerGameOver;
	}

	public Animation getNanaPlayerNormalUp() {
		return nanaPlayerNormalUp;
	}

	public Animation getNanaPlayerNormalDown() {
		return nanaPlayerNormalDown;
	}

	public Animation getNanaPlayerNormalLeft() {
		return nanaPlayerNormalLeft;
	}

	public Animation getNanaPlayerNormalRight() {
		return nanaPlayerNormalRight;
	}

	public Animation getNanaPlayerNormalBlink() {
		return nanaPlayerNormalBlink;
	}

	public Animation getNanaPlayerSlowUp() {
		return nanaPlayerSlowUp;
	}

	public Animation getNanaPlayerSlowDown() {
		return nanaPlayerSlowDown;
	}

	public Animation getNanaPlayerSlowLeft() {
		return nanaPlayerSlowLeft;
	}

	public Animation getNanaPlayerSlowRight() {
		return nanaPlayerSlowRight;
	}

	public Animation getNanaPlayerSlowBlink() {
		return nanaPlayerSlowBlink;
	}

	public Animation getNanaPlayerJumpUp() {
		return nanaPlayerJumpUp;
	}

	public Animation getNanaPlayerJumpDown() {
		return nanaPlayerJumpDown;
	}

	public Animation getNanaPlayerJumpLeft() {
		return nanaPlayerJumpLeft;
	}

	public Animation getNanaPlayerJumpRight() {
		return nanaPlayerJumpRight;
	}

	public Animation getNanaPlayerStun() {
		return nanaPlayerStun;
	}

	public Animation getNanaPlayerSlip() {
		return nanaPlayerSlip;
	}

	public Animation getNanaPlayerGameOver() {
		return nanaPlayerGameOver;
	}

	public Animation getBoboPlayerNormalUp() {
		return boboPlayerNormalUp;
	}

	public Animation getBoboPlayerNormalDown() {
		return boboPlayerNormalDown;
	}

	public Animation getBoboPlayerNormalLeft() {
		return boboPlayerNormalLeft;
	}

	public Animation getBoboPlayerNormalRight() {
		return boboPlayerNormalRight;
	}

	public Animation getBoboPlayerNormalBlink() {
		return boboPlayerNormalBlink;
	}

	public Animation getBoboPlayerSlowUp() {
		return boboPlayerSlowUp;
	}

	public Animation getBoboPlayerSlowDown() {
		return boboPlayerSlowDown;
	}

	public Animation getBoboPlayerSlowLeft() {
		return boboPlayerSlowLeft;
	}

	public Animation getBoboPlayerSlowRight() {
		return boboPlayerSlowRight;
	}

	public Animation getBoboPlayerSlowBlink() {
		return boboPlayerSlowBlink;
	}

	public Animation getBoboPlayerStun() {
		return boboPlayerStun;
	}

	public Animation getBoboPlayerGameOver() {
		return boboPlayerGameOver;
	}

	public Animation getOhohPlayerNormalUp() {
		return ohohPlayerNormalUp;
	}

	public Animation getOhohPlayerNormalDown() {
		return ohohPlayerNormalDown;
	}

	public Animation getOhohPlayerNormalLeft() {
		return ohohPlayerNormalLeft;
	}

	public Animation getOhohPlayerNormalRight() {
		return ohohPlayerNormalRight;
	}

	public Animation getOhohPlayerNormalBlink() {
		return ohohPlayerNormalBlink;
	}

	public Animation getOhohPlayerSlowUp() {
		return ohohPlayerSlowUp;
	}

	public Animation getOhohPlayerSlowDown() {
		return ohohPlayerSlowDown;
	}

	public Animation getOhohPlayerSlowLeft() {
		return ohohPlayerSlowLeft;
	}

	public Animation getOhohPlayerSlowRight() {
		return ohohPlayerSlowRight;
	}

	public Animation getOhohPlayerSlowBlink() {
		return ohohPlayerSlowBlink;
	}

	public Animation getOhohPlayerStun() {
		return ohohPlayerStun;
	}

	public Animation getOhohPlayerSlip() {
		return ohohPlayerSlip;
	}

	public Animation getOhohPlayerGameOver() {
		return ohohPlayerGameOver;
	}

	public Animation getWawaPlayerNormalUp() {
		return wawaPlayerNormalUp;
	}

	public Animation getWawaPlayerNormalDown() {
		return wawaPlayerNormalDown;
	}

	public Animation getWawaPlayerNormalLeft() {
		return wawaPlayerNormalLeft;
	}

	public Animation getWawaPlayerNormalRight() {
		return wawaPlayerNormalRight;
	}

	public Animation getWawaPlayerNormalBlink() {
		return wawaPlayerNormalBlink;
	}

	public Animation getWawaPlayerGameOver() {
		return wawaPlayerGameOver;
	}
}
