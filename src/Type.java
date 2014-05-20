import java.awt.Color;


public enum Type {
	FIRE(1, Color.ORANGE), WATER(2, Color.BLUE), GRASS(3, Color.GREEN);
	
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
