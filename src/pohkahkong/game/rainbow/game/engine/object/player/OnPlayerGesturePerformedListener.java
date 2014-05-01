package pohkahkong.game.rainbow.game.engine.object.player;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public interface OnPlayerGesturePerformedListener {
	public static enum PlayerGesture {RARA, ININ, AHAH, NANA, BOBO, OHOH, WAWA, JUMP}; 
	
	public void onPlayerGesturePerformed(PlayerGesture gesture);
}
