package pohkahkong.game.rainbow.menu.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import pohkahkong.game.rainbow.bean.animation.PropertySpriteAnimation;
import pohkahkong.game.rainbow.bean.animation.UnlockableRandomPSA;
import pohkahkong.game.rainbow.menu.MenuImageService;
import pohkahkong.game.rainbow.menu.MenuSoundService;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.PreferencesService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class MainView extends View {
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private DimensionService dimensionService = DimensionService.getInstance();
	private MenuImageService imageLoader = MenuImageService.getInstance();
	private MenuSoundService soundLoader = MenuSoundService.getInstance();

	private PropertySpriteAnimation[] players = null;
	private PointF points[] = null;
	private PropertySpriteAnimation rainbow;
	private int unlockNum;
	private boolean isCompleted;
	private float size;

	public MainView(Context context) {
		super(context);
		setWillNotDraw(true);
	}

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(true);
	}

	public void init() {
		unlockNum = preferencesService.getUnlockNum();
		isCompleted = preferencesService.isCompleted();
		size = dimensionService.getSize();
		invalidate();
	}

	public void load() {
		players = new PropertySpriteAnimation[7];
		players[0] = imageLoader.getRara();
		players[1] = imageLoader.getAhah();
		players[2] = imageLoader.getInin();
		players[3] = imageLoader.getNana();
		players[4] = imageLoader.getBobo();
		players[5] = imageLoader.getOhoh();
		players[6] = imageLoader.getWawa();
		rainbow = imageLoader.getRainbow();		
		int i = 0;
		for (; i < unlockNum; i++) {
			((UnlockableRandomPSA) players[i]).setLock(false);
		}
		for (; i < players.length; i++) {
			((UnlockableRandomPSA) players[i]).setLock(true);
		}
		setWillNotDraw(false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getMeasuredWidth();
		if (width>0) {
			setMeasuredDimension(width, (int) (width * 0.625f));	
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		points = new PointF[players.length];
		for (int i = players.length - 1; i >= 0; i--) {
			float x = 0.0f;
			float y = 0.0f;
			switch (i) {
			case 0:
				x = w * 0.5f - size;
				y = size * 3.0f;
				break;
			case 1:
				x = w * 0.5f - size * 2.0f;
				y = size * 2.0f;
				break;
			case 2:
				x = w * 0.5f;
				y = size * 2.0f;
				break;
			case 3:
				x = w * 0.5f + size;
				y = size;
				break;
			case 4:
				x = w * 0.5f - size * 3.0f;
				y = size;
				break;
			case 5:
				x = w * 0.5f - size * 4.0f;
				y = 0;
				break;
			case 6:
				x = w * 0.5f + size * 2.0f;
				y = 0;
				break;
			}
			points[i] = new PointF(x, y);
		}
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		update(System.currentTimeMillis());
		drawScreen(canvas);
		invalidate();
	}
	
	private void update(long newTime) {
		if (isCompleted) {
			rainbow.update(newTime);
		}
		int i = (int) (Math.random() * unlockNum);
		if (players[i].update(newTime)) {
			soundLoader.playerBlink();
		}
	}

	private void drawScreen(Canvas canvas) {
		if (isCompleted) {
			rainbow.draw(canvas, size, 0);
		}
		for (int i = players.length - 1; i >= 0; i--) {			
			players[i].draw(canvas, points[i].x, points[i].y);
		}
	}
	
	public void setTranslucent(boolean translucent) {
		for (int i = players.length - 1; i >= 0; i--) {
			players[i].setTranslucent(translucent);
		}
		rainbow.setTranslucent(translucent);
		if (!translucent) {
			rainbow.setFading(true);
		}		
	}	

	public void pause() {
		setWillNotDraw(true);
	}
	
	public void resume() {
		if (players!=null) {
			setWillNotDraw(false);
		}
	}
}
