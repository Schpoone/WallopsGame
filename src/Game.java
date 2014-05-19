import java.awt.Image;

public class Wallopsmon {
	
	private String name;
	private String type;
	private Image mainImage;
	private Image shinyImage;
	private boolean isShiny;
	private int level;
	private int currentExp;
	private int expToLevel;
	private String status;
	private int maxHealth;
	private int currentHealth;
	private int attack;
	private double attackMult;
	private int defense;
	private double defenseMult;
	private int specAttack;
	private double specAttackMult;
	private int specDefense;
	private double specDefenseMult;
	private int speed;
	private double speedMult;
	private double evasion;
	private double accuracy;
	private Item holdItem;
	private Move move1;
	private Move move2;
	private Move move3;
	private Move move4;
	private String description;
	
	public Wallopsmon() {
		name = null;
		type = null;
		mainImage = null;
		shinyImage = null;
		isShiny = false;
		level = 0;
		currentExp = 0;
		expToLevel = 0;
		status = null;
		maxHealth = 0;
		currentHealth = 0;
		attack = 0;
		attackMult = 1;
		defense = 0;
		defenseMult = 1;
		specAttack = 0;
		specAttackMult = 1;
		specDefense = 0;
		specDefenseMult = 1;
		speed = 0;
		speedMult = 1;
		evasion = 1;
		accuracy = 1;
		holdItem = null;
		move1 = null;
		move2 = null;
		move3 = null;
		move4 = null;
		description = null;
	}
	
	public Wallopsmon(String n, String t, Image main, Image shiny, int l, int maxH, int att, int def, int specAtt, int specDef, int spd, Item hold, Move one, Move two, Move three, Move four, String d) {
		name = n;
		type = t;
		mainImage = main;
		shinyImage = shiny;
		if (Math.random()*1000 < 1)
			isShiny = true;
		else
			isShiny = false;
		level = l;
		currentExp = (4 * (int)Math.pow(level, 3) / 5);
		expToLevel = (4 * (int)Math.pow(level + 1, 3) / 5) - currentExp;
		status = null;
		maxHealth = maxH;
		currentHealth = maxH;
		attack = att;
		attackMult = 1;
		defense = def;
		defenseMult = 1;
		specAttack = specAtt;
		specAttackMult = 1;
		specDefense = specDef;
		specDefenseMult = 1;
		speed = spd;
		speedMult = 1;
		evasion = 1;
		accuracy = 1;
		holdItem = hold;
		move1 = one;
		move2 = two;
		move3 = three;
		move4 = four;
		description = d;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public Image getImage() {
		if (isShiny)
			return shinyImage;
		return mainImage;
	}
	
	public boolean isShiny() {
		return isShiny;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getCurrentExp() {
		return currentExp;
	}
	
	public int getExpToLevel() {
		return expToLevel;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public double getAttackMult() {
		return attackMult;
	}
	
	
	public int getCurrentAttack() {
		return (int)(attack * attackMult);
	}
	
	public int getDefense() {
		return defense;
	}
	
	public double getDefenseMult() {
		return defenseMult;
	}
	
	public int getCurrentDefense() {
		return (int)(defense * defenseMult);
	}
	
	public int getSpecAttack() {
		return specAttack;
	}
	
	public double getSpecAttackMult() {
		return specAttackMult;
	}
	
	public int getCurrentSpecAttack() {
		return (int)(specAttack * specAttackMult);
	}
	
	public int getSpecDefense() {
		return specDefense;
	}
	
	public double getSpecDefenseMult() {
		return specDefenseMult;
	}
	
	public int getCurrentSpecDefense() {
		return (int)(specDefense * specDefenseMult);
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public double getSpeedMult() {
		return speedMult;
	}
	
	public int getCurrentSpeed() {
		return (int)(speed * speedMult);
	}
	
	public double getEvasion() {
		return evasion;
	}
	
	public double getAccuracy() {
		return accuracy;
	}
	
	public Item getHoldItem() {
		return holdItem;
	}
	
	public Move getMoveOne() {
		return move1;
	}
	
	public Move getMoveTwo() {
		return move2;
	}
	
	public Move getMoveThree() {
		return move3;
	}
	
	public Move getMoveFour() {
		return move4;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public void setMainImage(Image i) {
		mainImage = i;
	}
	
	public void setShinyImage(Image i) {
		shinyImage = i;
	}
	
	public void setIsShiny(boolean s) {
		isShiny = s;
	}
	
	public void setLevel(int l) {
		level = l;
	}
	
	public void setCurrentExp(int e) {
		currentExp = e;
	}
	
	public void setExpToLevel(int e) {
		expToLevel = e;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	public void setMaxHealth(int h) {
		maxHealth = h;
	}
	
	public void setCurrentHealth(int h) {
		currentHealth = h;
	}
	
	public void setAttack(int a) {
		attack = a;
	}
	
	public void setAttackMult(double am) {
		attackMult = am;
	}
	
	public void setDefense(int d) {
		defense = d;
	}
	
	public void setDefenseMult(double dm) {
		defenseMult = dm;
	}
	
	public void setSpecAttack(int sa) {
		specAttack = sa;
	}
	
	public void setSpecAttackMult(double sam) {
		specAttackMult = sam;
	}
	
	public void setSpecDefense(int sd) {
		specDefense = sd;
	}
	
	public void setSpecDefenseMult(double sdm) {
		specDefenseMult = sdm;
	}
	
	public void setSpeed(int s) {
		speed = s;
	}
	
	public void setSpeedMult(double sm) {
		speedMult = sm;
	}
	
	public void setEvasion(double e) {
		evasion = e;
	}
	
	public void setAccuracy(double a) {
		accuracy = a;
	}
	
	public void setHoldItem(Item i) {
		holdItem = i;
	}
	
	public void setMoveOne(Move o) {
		move1 = o;
	}
	
	public void setMoveTwo(Move t) {
		move2 = t;
	}
	
	public void setMoveThree(Move t) {
		move3 = t;
	}
	
	public void setMoveFour(Move f) {
		move4 = f;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
	public void resetInBattleVars() {
		attackMult = 1;
		defenseMult = 1;
		specAttackMult = 1;
		specDefenseMult = 1;
		speedMult = 1;
		evasion = 1;
		accuracy = 1;
	}
	
	public void fullyHeal() {
		attackMult = 1;
		defenseMult = 1;
		specAttackMult = 1;
		specDefenseMult = 1;
		speedMult = 1;
		evasion = 1;
		accuracy = 1;
		status = null;
		currentHealth = maxHealth;
	}
	
	public void updateExp() {
		currentExp += calcExpGain();
		expToLevel -= calcExpGain();
		if (expToLevel <= 0) {
			level++;
			expToLevel = (4 * (int)Math.pow(level + 1, 3) / 5) - currentExp;
		}
	}
	
	private int calcExpGain() {
		
	}
}
