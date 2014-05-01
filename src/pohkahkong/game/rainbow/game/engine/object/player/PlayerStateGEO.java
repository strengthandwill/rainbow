package pohkahkong.game.rainbow.game.engine.object.player;

import pohkahkong.game.rainbow.bean.animation.Animation;
import pohkahkong.game.rainbow.bean.animation.RandomPSA;
import pohkahkong.game.rainbow.callback.OnEventReceivedListener;
import pohkahkong.game.rainbow.game.GameSoundService;
import pohkahkong.game.rainbow.game.engine.GameEngine.Event;
import pohkahkong.game.rainbow.game.engine.object.GameEngineObject;
import pohkahkong.game.rainbow.service.AccelerometerService;
import pohkahkong.game.rainbow.service.DimensionService;
import pohkahkong.game.rainbow.service.VibratorService;
import android.graphics.Canvas;
import android.util.Log;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public abstract class PlayerStateGEO implements GameEngineObject {
	private static final String TAG = "PlayerStateGEO";
	protected static enum State {NORMAL, JUMP, SLOW, STUN, SLIP, OUTSIDE, INSIDE, WIN, GAMEOVER}
	
	private DimensionService dimensionService = DimensionService.getInstance();
	private AccelerometerService accelerometerService = AccelerometerService.getInstance();
	private VibratorService vibratorService = VibratorService.getInstance();
	private GameSoundService soundService = GameSoundService.getInstance();
		
	protected float gameWidth;
	protected float gameHeight;	
	protected float x;
	protected float y;		
	protected float deltaX = 0.0f;
	protected float deltaY = 0.0f;
	protected boolean isLock;
	protected boolean isPaused;
	protected State state = State.NORMAL;
	protected OnEventReceivedListener onEventReceivedListener;
	protected volatile boolean lock = false;
	
	protected float normalSpeedLimit = 1.0f;
	protected final float slowSpeedLimit = 0.2f;
	protected final float jumpSpeed = 1.5f;
	protected final float jumpDuration = 2.0f;
	protected final float slowDuration = 5.0f;
	protected final float stunDuration = 5.0f;
	protected final float slipDuration = 2.0f;
	protected final float outsideDuration = 5.0f;
	protected final float insideDuration = 5.0f;
	private long time = 0;
	private long lastTime = 0;
		
	// images attributes	
	protected float size;	
	protected Animation normalUp;
	protected Animation normalDown;
	protected Animation normalLeft;
	protected Animation normalRight;
	protected Animation normalBlink;	
	protected Animation slowUp;
	protected Animation slowDown;
	protected Animation slowLeft;
	protected Animation slowRight;
	protected Animation slowBlink;
	protected Animation jumpUp;
	protected Animation jumpDown;
	protected Animation jumpLeft;
	protected Animation jumpRight;
	protected Animation stun;
	protected Animation slip;
	protected Animation gameOver;
	protected Animation sprite;
	
	public PlayerStateGEO(OnEventReceivedListener onEventReceivedListener) {
		this.onEventReceivedListener = onEventReceivedListener;
		gameWidth = dimensionService.getGameWidth();
		gameHeight = dimensionService.getGameHeight();
		size = dimensionService.getSize();
	}

	// game engine states
	public void play() {
		setIsPaused(false);
		setState(State.NORMAL);
	}
	
	public void pause() {
		setIsPaused(true);
	}	
	
	public void stop() {
		pause();
	}
	
	// game engine events
	public void load() {
		setSprite(normalBlink);		
	}
	
	public void update(long newTime) {
		time = newTime;
		updatePosition();
		updateState();
		checkBoundary();
		updateSprite(newTime);
	}
	
	protected void updatePosition() {		
		if (isPaused) { // pause
			deltaX = 0.0f;
			deltaY = 0.0f;
		} else if (state == State.NORMAL || state == State.SLOW
				|| state == State.OUTSIDE || state == State.INSIDE) { // update position from accelerometer 
			float curDeltaX = accelerometerService.getDeltaX();
			float curDeltaY = accelerometerService.getDeltaY();		
			if (Math.abs(curDeltaX)>Math.abs(curDeltaY)) {			
				deltaX = curDeltaX;
				deltaY = 0.0f;
			} else {
				deltaX = 0.0f;
				deltaY = curDeltaY;		
			}
		}
		
		// update sprite and delta x and y
		switch (state) {
		case NORMAL:
			if (deltaX==0.0f && deltaY==0.0f) { // stop		
				setSprite(normalBlink);
			} else if (deltaX==0.0f && deltaY<0.0f) { // up
				setSprite(normalUp);
			} else if (deltaX==0.0f && deltaY>0.0f) { // down
				setSprite(normalDown);
			} else if (deltaY==0.0f && deltaX<0.0f) {// left
				setSprite(normalLeft);
			} else if (deltaY==0.0f && deltaX>0.0f) { // right
				setSprite(normalRight);
			}
			deltaX = Math.abs(deltaX) > normalSpeedLimit ? Math.signum(deltaX)
					* normalSpeedLimit : deltaX;
			deltaY = Math.abs(deltaY) > normalSpeedLimit ? Math.signum(deltaY)
					* normalSpeedLimit : deltaY;
			break;
		case OUTSIDE:
		case INSIDE:
		case SLOW:
			if (deltaX==0.0f && deltaY==0.0f) { // stop			
				setSprite(slowBlink);
			} else if (deltaX==0.0f && deltaY<0.0f) { // up
				setSprite(slowUp);
			} else if (deltaX==0.0f && deltaY>0.0f) { // down
				setSprite(slowDown);
			} else if (deltaY==0.0f && deltaX<0.0f) {// left
				setSprite(slowLeft);
			} else if (deltaY==0.0f && deltaX>0.0f) { // right
				setSprite(slowRight);
			}
			deltaX = Math.abs(deltaX) > slowSpeedLimit ? Math.signum(deltaX)
					* slowSpeedLimit : deltaX;
			deltaY = Math.abs(deltaY) > slowSpeedLimit ? Math.signum(deltaY)
					* slowSpeedLimit : deltaY;
			break;
		case STUN:
			if (sprite!=stun) {
				deltaX = 0.0f;
				deltaY = 0.0f;
				setSprite(stun);
			}
			break;
		case SLIP:
			if (sprite!=slip) {
				deltaX = -deltaX;
				deltaY = -deltaY;		
				setSprite(slip);
			}			
			break;						
		case JUMP:
			if (Math.abs(deltaX)>0.0f) {
				deltaX = jumpSpeed * Math.signum(deltaX);
			} else if (Math.abs(deltaY)>0.0f) {
				deltaY = jumpSpeed * Math.signum(deltaY);
			}			
			if (deltaX==0.0f && deltaY==0.0f) { // stop
				setSprite(jumpDown);
			} else if (deltaX==0.0f && deltaY<0.0f) { // up
				setSprite(jumpUp);
			} else if (deltaX==0.0f && deltaY>0.0f) { // down
				setSprite(jumpDown);
			} else if (deltaY==0.0f && deltaX<0.0f) {// left
				setSprite(jumpLeft);
			} else if (deltaY==0.0f && deltaX>0.0f) { // right
				setSprite(jumpRight);
			}
			break;
		case WIN:
			if (sprite!=normalBlink) {
				deltaX = 0.0f;
				deltaY = 0.0f;
				setSprite(normalBlink);
			}
			break;
		case GAMEOVER:
			if (sprite!=gameOver) {
				deltaX = 0.0f;
				deltaY = 0.0f;
				setSprite(gameOver);
			}
			break;	
		default:
			break;		
		}
		
		// update position x and y
		x += deltaX;
		y += deltaY;
	}
	
	protected void updateState() {
		if (isPaused) {
			lastTime = time;
			return;
		}
		
		if (state==State.SLOW  && time>lastTime+((long)slowDuration*1000)) { // slow
			setState(State.NORMAL);
		} if (state==State.STUN && time>lastTime+((long)stunDuration*1000)) { // stun
			setState(State.NORMAL);			
		} else if (state==State.SLIP && time>lastTime+((long)slipDuration*1000)) { // slip
			setState(State.NORMAL);										
		} else if (state==State.OUTSIDE) { // outside
			if (time>lastTime+((long)outsideDuration*1000)) {
				gameOverEnemy();
				onEventReceivedListener.onEventReceived(Event.GAMEOVER);				
			} 
		} else if (state==State.INSIDE) { // inside
			if (time>lastTime+((long)insideDuration*1000)) { 
				setState(State.NORMAL);
			}
		} else if (state==State.JUMP && time>lastTime+((long)jumpDuration*1000)) { // jump
			setState(State.NORMAL);										
		} 
	}

	protected void checkBoundary() {
		if (isPaused) {
			return;
		}
		
		if (x<0.0f) {
			x = 0.0f;
		} else if (x>gameWidth-size) {
			x = gameWidth-size;
		}
		if (y<0.0f) {
			y = 0.0f;
		} else if (y>gameHeight-size) {
			y = gameHeight-size;
		}					
	}
	
	public void updateSprite(long newTime) {
		if (!sprite.update(newTime)) {
			return;
		}
		
		if (sprite instanceof RandomPSA) {
			soundService.playerBlink();
		} else {
			if (state==State.NORMAL) {
				soundService.playerMove();
				vibratorService.playerNormal();
			} else {
				soundService.playerMove();
				vibratorService.playerSlow();
			}
		}
	}
	
	public void draw(Canvas canvas) {
		sprite.draw(canvas, x, y);
	}
	
	public boolean detectCollision(float objX, float objY) {
		return false;
	}
		
	// player actions
	// severity: slow < stun < slip < win / game over, outside / inside	
	public void outside() { // outside
		if (isPaused || state==State.OUTSIDE) {
			return;
		}
		Log.i(TAG, "outside");
		setState(State.OUTSIDE);	
		lastTime = time;
		soundService.playerOuch();
	}
	
	public void inside() { // inside
		if (isPaused || state!=State.OUTSIDE) {
			return;
		}
		Log.i(TAG, "inside");
		setState(State.INSIDE);	
		lastTime = time;
	}
	
	public void slow() { // sunny		
		if (isPaused || state==State.SLOW || state==State.STUN || 
				state==State.SLIP) {
			return;
		}
		Log.i(TAG, "slow");
		setState(State.SLOW);
		lastTime = time;
		soundService.playerOuch();
	}		

	public void stun() { // lightny
		if (isPaused || state==State.STUN || state==State.SLIP) {
			return;
		}
		Log.i(TAG, "stun");
		setState(State.STUN);
		lastTime = time;
		soundService.playerOuch();
		vibratorService.playerStun();
	}
	
	public void slip() { // rainy
		if (isPaused || state==State.SLIP) {
			return;
		}
		Log.i(TAG, "slip");
		setState(State.SLIP);
		lastTime = time;
		soundService.playerOuch();
		vibratorService.playerSlip();		
	}
	
	public boolean gameOverEnemy() { // snowy
		return true;		
	}
	
	public boolean jump() {
		if (isPaused || state!=State.NORMAL) {
			return false;
		}
		Log.i(TAG, "jump");
		setState(State.JUMP);		
		lastTime = time;
		soundService.playerNaNaJump();
		return true;		
	}	
	
	public void win() {
		if (isPaused) {
			return;
		}
		Log.i(TAG, "win");
		setIsPaused(true);
		setState(State.WIN);
		soundService.playerWin();		
	}
	
	public void gameOver() {
		if (isPaused) {
			return;
		}
		Log.i(TAG, "gameOver");
		setIsPaused(true);
		setState(State.GAMEOVER);
		soundService.playerLose();
		vibratorService.playerGameOverEnemy();
	}
	
	public void playStart() {
		soundService.playerHello();
	}
	
	public abstract void playChange();
	
	// getters and setters
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}		
		
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public boolean isLock() {
		return isLock;
	}
	
	public boolean isNormal() {
		return !isPaused && state==State.NORMAL;
	}
	
	public boolean isCollidable() {
		return state!=State.JUMP;
	}
	
	// synchronized method to set variables
	private synchronized void setState(State state) {
		this.state = state;
	}
	
	private synchronized void setSprite(Animation sprite) {
		this.sprite = sprite;
	}
	
	private synchronized void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;		
	}
}