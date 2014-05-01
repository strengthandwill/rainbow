package pohkahkong.game.rainbow.menu.stage;

import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.service.DimensionService;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class StageObjectsView extends View {
	private DimensionService dimensionService = DimensionService.getInstance();
	private Animation animation;
	private boolean horizontal;
	private Item[] items;
	private float coverage;
	private boolean running = false;
	
	public StageObjectsView(Context context) {
		super(context);
	}

	public StageObjectsView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void init(int number, boolean horizontal, float coverage) {
		this.horizontal = horizontal;
		this.coverage = coverage;
		items = new Item[number];	
	}
	
	public void load(Animation animation) {
		this.animation = animation;		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension((int) dimensionService.getWindowWidth(), 
				(int) dimensionService.getWindowHeight());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (animation==null) {
			return;
		}		
		long newTime = System.currentTimeMillis();
		update(newTime);
		drawScreen(canvas);
		invalidate();
	}
	
	private void update(long newTime) {		
		for (int i=0; i<items.length; i++) {
			if (items[i]==null) {
				items[i] = new Item();
			} else if (!items[i].update(newTime)) {
				items[i] = null;				
			}
		}	
		running = true;
	}
	
	private void drawScreen(Canvas canvas) {
		for (int i=0; i<items.length; i++) {
			if (items[i]!=null && animation!=null) {
				items[i].draw(canvas);
			}
		}
	}

	private class Item {
		private float x;
		private float y;
		private float deltaX;
		private float deltaY;
		private boolean drawing = true;
				
		public Item() {			
			float delta = ((float) Math.random()) + 0.5f;			
			if (horizontal) { // horizontal				
				y = (float) Math.random() * dimensionService.getWindowHeight() * coverage;
				if (Math.random() > 0.5) { // left
					x = running? -animation.getWidth() : (float) (Math.random() * 
						(dimensionService.getWindowWidth() + animation.getWidth()) - animation.getWidth());
					deltaX = delta;
				} else { // right
					x = running? dimensionService.getWindowWidth() : (float) (Math.random() * 
						(dimensionService.getWindowWidth() + animation.getWidth()) - animation.getWidth());
					deltaX = -delta;
				}
			} else { // vertical										
				y = running? 0 : (float) (Math.random() * dimensionService.getWindowHeight() * coverage);
				deltaY = delta;
				x = (float) Math.random() * dimensionService.getWindowWidth();
			}
		}

		public boolean update(long newTime) {
			animation.update(newTime);
			if (horizontal) { // horizontal
				x+=deltaX;			
				if ((Math.signum(deltaX) > 0 && x > dimensionService.getWindowWidth()) || // left
					(Math.signum(deltaX) < 0 && x < -animation.getWidth())) { // right
					drawing = false;
					return false;
				}
			} else { // vertical
				y+=deltaY;	
				if (y > dimensionService.getWindowHeight() * 0.5f) {
					drawing = false;
					return false;
				}
			}
			return true;
		}

		public void draw(Canvas canvas) {
			if (!drawing) {
				return;				
			}
			animation.draw(canvas, x, y);
		}
	}
}
