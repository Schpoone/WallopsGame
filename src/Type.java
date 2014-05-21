import java.awt.Color;


public enum Type {
	FIRE(1, Color.RED), WATER(2, Color.BLUE), GRASS(3, Color.GREEN), NORMAL(4, new Color(200, 200, 150)),
	ELECTRIC(5, Color.YELLOW), ICE(6, Color.CYAN), DARK(7, Color.BLACK), FIGHTING(8, new Color(200, 35, 15)),
	FLYING(9, new Color(215, 190, 245)), POISON(10, new Color(185, 10, 230)), GROUND(11, new Color(240, 200, 115)),
	ROCK(12, new Color(180, 155, 20)), BUG(13, new Color(130, 190, 60)), STEEL(14, Color.GRAY.brighter());
	//this is missing psychic, ghost, dragon, and fairy
	
	private int value;
	private Color color;
	
	private Type(int v, Color c) {
		value = v;
		color = c;
	}
	
	public boolean equals(Enum o) {
		Type t = (Type)o;
		return t.getValue() == this.getValue();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
