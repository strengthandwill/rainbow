package pohkahkong.game.rainbow.game;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public interface GameActivityScreen {
	// game activity states
	public void load();
	
	public void hold();
	
	public void play();
	
	public void pause();
	
	public void hide();
	
	public void stop();
}
