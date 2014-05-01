package pohkahkong.game.rainbow.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.bean.Enemy;
import pohkahkong.game.rainbow.bean.Item;
import pohkahkong.game.rainbow.bean.Player;
import pohkahkong.game.rainbow.game.engine.object.enemy.EnemiesGEO.Type;
import android.content.Context;
import android.content.res.XmlResourceParser;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class XmlParserService {
	private static XmlParserService instance = null;
	private DimensionService dimensionService = DimensionService.getInstance();	
	
	private XmlResourceParser xmlResourceParser;	
	
	private int curSeason;
	private int curLevel;
	private float size;
	
	private int duration;
	private int[][] mapRoad;
	private Player player;
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private Item rainbow;
	
	private XmlParserService() {
	}

	public static XmlParserService getInstance() {
		if (instance==null) {
			instance = new XmlParserService();
		}
		return instance;
	}
	
	public void init(Context context, int curSeason, int curLevel) {
		this.curSeason = curSeason;
		this.curLevel = curLevel;
		size = dimensionService.getSize();
		xmlResourceParser = context.getResources().getXml(getStageXmlId());	
		parse();
	}
	
    private int getStageXmlId() {
    	String curStage = curSeason + "-" + curLevel;
    	int id = -1;    	
    	if (curStage.equals("1-1"))
    		id = R.xml.stage_1_01;
    	else if (curStage.equals("1-2"))
    		id = R.xml.stage_1_02;
    	else if (curStage.equals("1-3"))
    		id = R.xml.stage_1_03;
    	else if (curStage.equals("1-4"))
    		id = R.xml.stage_1_04;
    	else if (curStage.equals("1-5"))
    		id = R.xml.stage_1_05;
    	else if (curStage.equals("1-6"))
    		id = R.xml.stage_1_06;
    	else if (curStage.equals("1-7"))
    		id = R.xml.stage_1_07;
    	else if (curStage.equals("1-8"))
    		id = R.xml.stage_1_08;
    	else if (curStage.equals("1-9"))
    		id = R.xml.stage_1_09;
    	else if (curStage.equals("1-10"))
    		id = R.xml.stage_1_10;
    	else if (curStage.equals("1-11"))
    		id = R.xml.stage_1_11;
    	else if (curStage.equals("1-12"))
    		id = R.xml.stage_1_12;
    	else if (curStage.equals("1-13"))
    		id = R.xml.stage_1_13;
    	else if (curStage.equals("1-14"))
    		id = R.xml.stage_1_14;
    	else if (curStage.equals("1-15"))
    		id = R.xml.stage_1_15;
    	else if (curStage.equals("1-16"))
    		id = R.xml.stage_1_16;
    	else if (curStage.equals("1-17"))
    		id = R.xml.stage_1_17;
    	else if (curStage.equals("1-18"))
    		id = R.xml.stage_1_18;
    	else if (curStage.equals("1-19"))
    		id = R.xml.stage_1_19;
    	else if (curStage.equals("1-20"))
    		id = R.xml.stage_1_20;    	
    	else if (curStage.equals("2-1"))
    		id = R.xml.stage_2_01;
    	else if (curStage.equals("2-2"))
    		id = R.xml.stage_2_02;
    	else if (curStage.equals("2-3"))
    		id = R.xml.stage_2_03;
    	else if (curStage.equals("2-4"))
    		id = R.xml.stage_2_04;
    	else if (curStage.equals("2-5"))
    		id = R.xml.stage_2_05;
    	else if (curStage.equals("2-6"))
    		id = R.xml.stage_2_06;
    	else if (curStage.equals("2-7"))
    		id = R.xml.stage_2_07;
    	else if (curStage.equals("2-8"))
    		id = R.xml.stage_2_08;
    	else if (curStage.equals("2-9"))
    		id = R.xml.stage_2_09;
    	else if (curStage.equals("2-10"))
    		id = R.xml.stage_2_10;
    	else if (curStage.equals("2-11"))
    		id = R.xml.stage_2_11;
    	else if (curStage.equals("2-12"))
    		id = R.xml.stage_2_12;
    	else if (curStage.equals("2-13"))
    		id = R.xml.stage_2_13;
    	else if (curStage.equals("2-14"))
    		id = R.xml.stage_2_14;
    	else if (curStage.equals("2-15"))
    		id = R.xml.stage_2_15;
    	else if (curStage.equals("2-16"))
    		id = R.xml.stage_2_16;
    	else if (curStage.equals("2-17"))
    		id = R.xml.stage_2_17;
    	else if (curStage.equals("2-18"))
    		id = R.xml.stage_2_18;
    	else if (curStage.equals("2-19"))
    		id = R.xml.stage_2_19;
    	else if (curStage.equals("2-20"))
    		id = R.xml.stage_2_20;  
    	else if (curStage.equals("3-1"))
    		id = R.xml.stage_3_01;
    	else if (curStage.equals("3-2"))
    		id = R.xml.stage_3_02;
    	else if (curStage.equals("3-3"))
    		id = R.xml.stage_3_03;
    	else if (curStage.equals("3-4"))
    		id = R.xml.stage_3_04;
    	else if (curStage.equals("3-5"))
    		id = R.xml.stage_3_05;
    	else if (curStage.equals("3-6"))
    		id = R.xml.stage_3_06;
    	else if (curStage.equals("3-7"))
    		id = R.xml.stage_3_07;
    	else if (curStage.equals("3-8"))
    		id = R.xml.stage_3_08;
    	else if (curStage.equals("3-9"))
    		id = R.xml.stage_3_09;
    	else if (curStage.equals("3-10"))
    		id = R.xml.stage_3_10;
    	else if (curStage.equals("3-11"))
    		id = R.xml.stage_3_11;
    	else if (curStage.equals("3-12"))
    		id = R.xml.stage_3_12;
    	else if (curStage.equals("3-13"))
    		id = R.xml.stage_3_13;
    	else if (curStage.equals("3-14"))
    		id = R.xml.stage_3_14;
    	else if (curStage.equals("3-15"))
    		id = R.xml.stage_3_15;
    	else if (curStage.equals("3-16"))
    		id = R.xml.stage_3_16;
    	else if (curStage.equals("3-17"))
    		id = R.xml.stage_3_17;
    	else if (curStage.equals("3-18"))
    		id = R.xml.stage_3_18;
    	else if (curStage.equals("3-19"))
    		id = R.xml.stage_3_19;
    	else if (curStage.equals("3-20"))
    		id = R.xml.stage_3_20;      	
    	else if (curStage.equals("4-1"))
    		id = R.xml.stage_4_01;
    	else if (curStage.equals("4-2"))
    		id = R.xml.stage_4_02;
    	else if (curStage.equals("4-3"))
    		id = R.xml.stage_4_03;
    	else if (curStage.equals("4-4"))
    		id = R.xml.stage_4_04;
    	else if (curStage.equals("4-5"))
    		id = R.xml.stage_4_05;
    	else if (curStage.equals("4-6"))
    		id = R.xml.stage_4_06;
    	else if (curStage.equals("4-7"))
    		id = R.xml.stage_4_07;
    	else if (curStage.equals("4-8"))
    		id = R.xml.stage_4_08;
    	else if (curStage.equals("4-9"))
    		id = R.xml.stage_4_09;
    	else if (curStage.equals("4-10"))
    		id = R.xml.stage_4_10;
    	else if (curStage.equals("4-11"))
    		id = R.xml.stage_4_11;
    	else if (curStage.equals("4-12"))
    		id = R.xml.stage_4_12;
    	else if (curStage.equals("4-13"))
    		id = R.xml.stage_4_13;
    	else if (curStage.equals("4-14"))
    		id = R.xml.stage_4_14;
    	else if (curStage.equals("4-15"))
    		id = R.xml.stage_4_15;
    	else if (curStage.equals("4-16"))
    		id = R.xml.stage_4_16;
    	else if (curStage.equals("4-17"))
    		id = R.xml.stage_4_17;
    	else if (curStage.equals("4-18"))
    		id = R.xml.stage_4_18;
    	else if (curStage.equals("4-19"))
    		id = R.xml.stage_4_19;
    	else if (curStage.equals("4-20"))
    		id = R.xml.stage_4_20;    	
    	return id;
    }	

	private void parse() {
		try {
			int eventType;
			do{ 				
				xmlResourceParser.next();
				eventType = xmlResourceParser.getEventType();
									
				if (eventType==XmlPullParser.START_TAG) {
					if (xmlResourceParser.getName().equals("Time"))
						parseTimeNode();
					else if (xmlResourceParser.getName().equals("Map"))
						parseMapNode();
					else if (xmlResourceParser.getName().equals("Rainbow"))
						parseRainbowNode();					
					else if (xmlResourceParser.getName().equals("Player"))
						parsePlayerNode();
					else if (xmlResourceParser.getName().equals("Enemy"))
						parseEnemyNode();
				}				
			} while (eventType!=XmlPullParser.END_DOCUMENT);

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String parseLeafNode() throws XmlPullParserException, IOException {
		String str = "";		
		xmlResourceParser.next(); // text node
		if (xmlResourceParser.getEventType()==XmlPullParser.TEXT)
			str += xmlResourceParser.getText();	
		xmlResourceParser.next(); // end tag node
		return str;
	}		
		
	private void parseTimeNode() throws XmlPullParserException, IOException {				
		int eventType;
		do{ 
			xmlResourceParser.next();	
			eventType = xmlResourceParser.getEventType();			
			if (eventType==XmlPullParser.START_TAG) {
				if (xmlResourceParser.getName().equals("Duration")) {
					duration = Integer.parseInt(parseLeafNode());
				}
			}
			
		} while (eventType!=XmlPullParser.END_TAG || !xmlResourceParser.getName().equals("Time"));		
	}	
	
	private void parseMapNode() throws XmlPullParserException, IOException {		
		List<int[]> mapRow = new ArrayList<int[]>();		
		int eventType;		
		do{ 
			xmlResourceParser.next();	
			eventType = xmlResourceParser.getEventType();			
			if (eventType==XmlPullParser.START_TAG) {
				if (xmlResourceParser.getName().equals("Row")) {
					String[] values = parseLeafNode().split(",");
					int mapCol[] = new int[values.length];					
					for (int i=0; i<values.length; i++) {
						mapCol[i] = Integer.parseInt(values[i].trim());
					}
					mapRow.add(mapCol);
				}
			}			
		} while (eventType!=XmlPullParser.END_TAG || !xmlResourceParser.getName().equals("Map"));
		mapRoad = new int[mapRow.size()][];
		for (int i=0; i<mapRow.size(); i++) {
			mapRoad[i] = mapRow.get(i);
		}		
	}	
	
	private void parseRainbowNode() throws XmlPullParserException, IOException {	
		rainbow = new Item();
		int eventType;
		do{ 
			xmlResourceParser.next();	
			eventType = xmlResourceParser.getEventType();			
			if (eventType==XmlPullParser.START_TAG) {
				if (xmlResourceParser.getName().equals("MapPosition")) {
					String[] values = parseLeafNode().split(",");
					rainbow.setX(Integer.parseInt(values[0])*size);
					rainbow.setY(Integer.parseInt(values[1])*size);
				}
			}			
		} while (eventType!=XmlPullParser.END_TAG || !xmlResourceParser.getName().equals("Rainbow"));
	}
	
	private void parsePlayerNode() throws XmlPullParserException, IOException {		
		player = new Player();
		int eventType;
		do{ 
			xmlResourceParser.next();	
			eventType = xmlResourceParser.getEventType();			
			if (eventType==XmlPullParser.START_TAG) {
				if (xmlResourceParser.getName().equals("MapPosition")) {
					String[] values = parseLeafNode().split(",");
					player.setX((int) (Integer.parseInt(values[0])*size));
					player.setY((int) (Integer.parseInt(values[1])*size));
				}
				else if (xmlResourceParser.getName().equals("Lock"))
					parsePlayerLockNode(player);
			}
			
		} while (eventType!=XmlPullParser.END_TAG || !xmlResourceParser.getName().equals("Player"));
	}
	
	private void parsePlayerLockNode(Player player) throws XmlPullParserException, IOException {
		int eventType;
		do{ 
			xmlResourceParser.next();	
			eventType = xmlResourceParser.getEventType();
			if (eventType==XmlPullParser.START_TAG) {
				if (xmlResourceParser.getName().equals("RaRa")) {
					if (parseLeafNode().equals("True"))
						player.setRaRaLock(true);
					else
						player.setRaRaLock(false);										
				}
				else if (xmlResourceParser.getName().equals("AhAh")) {
					if (parseLeafNode().equals("True"))
						player.setAhAhLock(true);
					else
						player.setAhAhLock(false);
				}
				else if (xmlResourceParser.getName().equals("InIn")) {
					if (parseLeafNode().equals("True"))
						player.setInInLock(true);
					else
						player.setInInLock(false);
				}
				else if (xmlResourceParser.getName().equals("NaNa")) {
					if (parseLeafNode().equals("True"))
						player.setNaNaLock(true);
					else
						player.setNaNaLock(false);
				}
				else if (xmlResourceParser.getName().equals("BoBo")) {
					if (parseLeafNode().equals("True"))
						player.setBoBoLock(true);
					else
						player.setBoBoLock(false);
				}
				else if (xmlResourceParser.getName().equals("OhOh")) {
					if (parseLeafNode().equals("True"))
						player.setOhOhLock(true);
					else
						player.setOhOhLock(false);
				}
				else if (xmlResourceParser.getName().equals("WaWa")) {
					if (parseLeafNode().equals("True"))
						player.setWaWaLock(true);
					else
						player.setWaWaLock(false);
				}
			}
			
		} while (eventType!=XmlPullParser.END_TAG || !xmlResourceParser.getName().equals("Lock"));
	}
	
	private void parseEnemyNode() throws XmlPullParserException, IOException {	
		Enemy enemy = new Enemy();
		int eventType;
		do{
			
			
			
			xmlResourceParser.next();	
			eventType = xmlResourceParser.getEventType();			
			if (eventType==XmlPullParser.START_TAG) {
				if (xmlResourceParser.getName().equals("Name")) {
					String name = parseLeafNode();
					if (name.equals("Sunny")) {
						enemy.setType(Type.SUNNY);	
					} else if (name.equals("Lightny")) {
						enemy.setType(Type.LIGHTNY);
					} else if (name.equals("Rainy")) {
						enemy.setType(Type.RAINY);
					} else if (name.equals("Snowy")) {
						enemy.setType(Type.SNOWY);
					}
				} else if (xmlResourceParser.getName().equals("MapPosition")) {
					String[] values = parseLeafNode().split(",");
					enemy.setX((int) (Integer.parseInt(values[0])*size));
					enemy.setY((int) (Integer.parseInt(values[1])*size));	
				} else if (xmlResourceParser.getName().equals("Direction")) {
					if (parseLeafNode().equals("Horizontal")) {
						enemy.setHorizontal(true);
					} else {
						enemy.setHorizontal(false);
					}
				} else if (xmlResourceParser.getName().equals("Distance")) {
					enemy.setDistance((int) ((Integer.parseInt(parseLeafNode())-1)*size));
				}
			}											
		} while (eventType!=XmlPullParser.END_TAG || !xmlResourceParser.getName().equals("Enemy"));
		enemies.add(enemy);	
	}

	public int getDuration() {
		return duration;
	}

	public int[][] getMapRoad() {
		return mapRoad;
	}

	public Player getPlayer() {
		return player;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public Item getRainbow() {
		return rainbow;
	}	
	
	public void release() {		
		enemies.clear();		
	}
}
