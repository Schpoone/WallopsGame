import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public enum Move {
	NONE(),
	//(name, type, category, pp, power, accuracy)
	FLAMETHROWER("Flamethrower", Type.FIRE, Move.SPECIAL, 15, 90, 100),
	WATER_GUN("WaterGun", Type.WATER, Move.SPECIAL, 25, 40, 100),
	WITHDRAW("Withdraw", Type.WATER, Move.STATUS, 40, 0, -1),
	SPLASH("Splash", Type.WATER, Move.STATUS, 40, 0, -1),
	SOLAR_BEAM("SolarBeam", Type.GRASS, Move.SPECIAL, 10, 120, 100),
	TACKLE("Tackle", Type.NORMAL, Move.PHYSICAL, 35, 50, 100),
	SLAM("Slam", Type.NORMAL, Move.PHYSICAL, 20, 80, 75),
	THUNDERBOLT("Thunderbolt", Type.ELECTRIC, Move.SPECIAL, 15, 90, 100),
	BLIZZARD("Blizzard", Type.ICE, Move.SPECIAL, 5, 110, 70),
	BITE("Bite", Type.DARK, Move.PHYSICAL, 25, 60, 100),
	MACH_PUNCH("MachPunch", Type.FIGHTING, Move.PHYSICAL, 30, 40, 100),
	FLY("Fly", Type.FLYING, Move.PHYSICAL, 15, 90, 95),
	TOXIC("Toxic", Type.POISON, Move.STATUS, 10, 0, 90),
	DIG("Dig", Type.GROUND, Move.PHYSICAL, 10, 80, 100),
	ROCK_THROW("RockThrow", Type.ROCK, Move.PHYSICAL, 15, 50, 90),
	BUG_BUZZ("BugBuzz", Type.BUG, Move.SPECIAL, 10, 90, 100),
	IRON_HEAD("IronHead", Type.STEEL, Move.PHYSICAL, 15, 80, 100),
	DRAGON_CLAW("DragonClaw", Type.DRAGON, Move.PHYSICAL, 15, 80, 100)
	;
	
	public static final int PHYSICAL = 1;
	public static final int SPECIAL = 2;
	public static final int STATUS = 3;
	
	private String name;
	private Type type;
	private int category;
	private int power;
	private int accuracy;
	private int maxPP;
	private int currentPP;
	private String description;
	
	private Move() {
		name = "";
		type = null;
		category = 0;
		maxPP = 0;
		power = 0;
		accuracy = 0;
		description = null;
	}
	
	private Move(String n, Type t, int c, int points, int p, int a) {
		name = n;
		type = t;
		category = c;
		maxPP = points;
		power = p;
		accuracy = a;
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("move_description.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		description = prop.getProperty(name);
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	public int getCategory() {
		return category;
	}
	
	public int getPower() {
		return power;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public int getMaxPP() {
		return maxPP;
	}
	
	public int getCurrentPP() {
		return currentPP;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setType(Type t) {
		type = t;
	}
	
	public void setCategory(int c) {
		category = c;
	}
	
	public void setPower(int p) {
		power = p;
	}
	
	public void setAccuracy(int a) {
		accuracy = a;
	}
	
	public void setMaxPP(int p) {
		maxPP = p;
	}
	
	public void setCurrentPP(int p) {
		currentPP = p;
	}
	
	public void setDescription(String d) {
		description = d;
	}
}
