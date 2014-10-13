package com.wallops.java.wallopsmon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.wallops.java.reference.Move;
import com.wallops.java.reference.Type;


public abstract class Wallopsmon {
	//(String n, Type t1, Type t2, int l, int maxH, int att, int def, int specAtt, int specDef, int spd, /*Item hold,*/ Move one, Move two, Move three, Move four)
	
	private String name;
	private Type type1;
	private Type type2;
	private Texture mainImage;
	private Texture shinyImage;
	private boolean shiny;
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
	//private Item holdItem;
	private Move move1;
	private Move move2;
	private Move move3;
	private Move move4;
	private String description;
	
	public Wallopsmon() {
		name = null;
		type1 = null;
		type2 = null;
		mainImage = null;
		shinyImage = null;
		shiny = false;
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
		//holdItem = null;
		move1 = null;
		move2 = null;
		move3 = null;
		move4 = null;
		description = null;
	}
	

	public Wallopsmon(String n, Type t1, Type t2, int l, int maxH, int att, int def, int specAtt, int specDef, int spd, /*Item hold,*/ Move one, Move two, Move three, Move four) {
		name = n;
		type1 = t1;
		type2 = t2;
		
		try {
			mainImage = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("src/com/wallops/resources/img/" + name + ".jpg"));
			shinyImage = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("src/com/wallops/resources/img/Shiny" + name + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (Math.random()*8192 < 1)
			shiny = true;
		else
			shiny = false;
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
		//holdItem = hold;
		move1 = one;
		move2 = two;
		move3 = three;
		move4 = four;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("src/com/wallops/resources/wallopsmon_description.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		description = prop.getProperty(name);
	}
	
	public String getName() {
		String actualName = "";
		String[] words = name.split("_");
		for (int i = 0; i < words.length; i++) {
			actualName += words[i] + " ";
		}
		actualName = actualName.substring(0, actualName.length() - 1);
		if(shiny)
			actualName = "Shiny " + actualName;
		return actualName;
	}
	

	public Type getType1() {
		return type1;
	}
	
	public Type getType2() {
		return type2;
	}
	
	public Texture getImage() {
		return this.shiny ? this.shinyImage : this.mainImage;
	}
	
	public Texture getShinyImage() {
		return this.shinyImage;
	}
	
	public boolean isShiny() {
		return shiny;
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
	
	/*public Item getHoldItem() {
		return holdItem;
	}*/
	
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
	

	public void setType1(Type t) {
		type1 = t;
	}
	
	public void setType2(Type t) {
		type2 = t;
	}
	
	public void setMainImage(Texture i) {
		mainImage = i;
	}
	
	public void setShinyImage(Texture i) {
		shinyImage = i;
	}
	
	public void setIsShiny(boolean s) {
		shiny = s;
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
	
	/*public void setHoldItem(Item i) {
		holdItem = i;
	}*/
	
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

	public void updateExp(int fntLvl) {
		currentExp += calcExpGain(fntLvl);
		expToLevel -= calcExpGain(fntLvl);
		while (expToLevel <= 0) {
			level++;
			expToLevel = (4 * (int)Math.pow(level + 1, 3) / 5) - currentExp;
		}
	}

	public int calcExpGain(int fntLvl) {
		int num = (int)(1.5 * 200 * fntLvl);
		int denom = (int)(7 * level);
		int gain = (int)(num / denom);
		return gain;
	}
}
