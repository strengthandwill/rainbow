package pohkahkong.game.rainbow.game;

import android.content.Context;
import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.service.PreferencesService;
import pohkahkong.game.rainbow.service.SoundService;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class GameSoundService {
	private static GameSoundService instance = null;

	private SoundService soundService = SoundService.getInstance();
	private PreferencesService preferencesService = PreferencesService.getInstance();
	private int season;

	private int playerMove;
	private int playerBlink;	
	private int playerHello;
	private int playerOuch;
	private int playerNaNaJump;
	private int playerRaRa;
	private int playerAhAh;
	private int playerInIn;
	private int playerNaNa;
	private int playerBoBo;
	private int playerOhOh;
	private int playerWaWa;
	private int playerYea;
	private int playerNo;
	private int[] background = new int[2];
	private int buttonClick;
	private int screenChange;
	private int timeTick;

	private GameSoundService() {
	}

	public static GameSoundService getInstance() {
		if (instance==null) {
			instance = new GameSoundService();
		}
		return instance;
	}
	
	public void init(Context context, int season) {
		soundService.init(context);
		this.season = season;
		soundService.setEnabled(preferencesService.isSoundEnabled());
	}
	
	public void increaseVolume() {
		soundService.increaseVolume();
	}
	
	public void decreaseVolume() {
		soundService.decreaseVolume();
	}
	
	public void release() {
		soundService.release();
	}

	public void load() {
		playerMove = soundService.addSound(R.raw.player_move);
		playerBlink = soundService.addSound(R.raw.player_blink);		
		playerHello = soundService.addSound(R.raw.player_hello);
		playerOuch = soundService.addSound(R.raw.player_ouch);
		playerNaNaJump = soundService.addSound(R.raw.player_nanajump);
		playerRaRa = soundService.addSound(R.raw.player_rara);
		playerAhAh = soundService.addSound(R.raw.player_ahah);
		playerInIn = soundService.addSound(R.raw.player_inin);
		playerNaNa = soundService.addSound(R.raw.player_nana);
		playerBoBo = soundService.addSound(R.raw.player_bobo);
		playerOhOh = soundService.addSound(R.raw.player_ohoh);
		playerWaWa = soundService.addSound(R.raw.player_wawa);				
		playerYea = soundService.addSound(R.raw.player_yea);	
		playerNo = soundService.addSound(R.raw.player_no);	
		timeTick = soundService.addSound(R.raw.time_tick);
		buttonClick = soundService.addSound(R.raw.button_click);
		screenChange = soundService.addSound(R.raw.screen_change);

		switch (season) {
		case 1: 
			background[0] = soundService.addSound(R.raw.spring_background1);
			background[1] = soundService.addSound(R.raw.spring_background2);
			break;
		case 2:
			background[0] = soundService.addSound(R.raw.summer_background1);
			background[1] = soundService.addSound(R.raw.summer_background2);
			break;
		case 3: 
			background[0] = soundService.addSound(R.raw.autumn_background1);
			background[1] = soundService.addSound(R.raw.autumn_background2);
			break;
		case 4: 
			background[0] = soundService.addSound(R.raw.winter_background1);
			background[1] = soundService.addSound(R.raw.winter_background2);
			break;
		}		
	}

	public void playerMove() {		
		soundService.play(playerMove);
	}

	public void playerBlink() {		
		soundService.play(playerBlink);
	}	

	public void playerHello() {		
		soundService.play(playerHello);
	}	

	public void playerOuch() {		
		soundService.play(playerOuch);
	}	

	public void playerNaNaJump() {		
		soundService.play(playerNaNaJump);
	}

	public void playerRaRa() {		
		soundService.play(playerRaRa);
	}	

	public void playerAhAh() {		
		soundService.play(playerAhAh);
	}	

	public void playerInIn() {		
		soundService.play(playerInIn);
	}	

	public void playerNaNa() {	
		soundService.play(playerNaNa);
	}	

	public void playerBoBo() {	
		soundService.play(playerBoBo);
	}

	public void playerOhOh() {
		soundService.play(playerOhOh);
	}	

	public void playerWaWa() {		
		soundService.play(playerWaWa);
	}	

	public void playerWin() {		
		soundService.play(playerYea);
	}

	public void playerLose() {		
		soundService.play(playerNo);
	}

	public void timeTick() {		
		soundService.play(timeTick);
	}

	public void buttonClick() {
		soundService.play(buttonClick);
	}	

	public void screenChange() {		
		soundService.play(screenChange);
	}	

	public void background() {		 
		if (Math.random()<0.5f)
			soundService.play(background[0]);
		else
			soundService.play(background[1]);
	}
}