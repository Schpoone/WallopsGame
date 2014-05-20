public enum Move {
	NONE(),
	TACKLE("Tackle", Type.FIRE, 10, 10, "Meow :3"),
	;
	
	private String name;
	private Type type;
	private int power;
	private int accuracy;
	private String description;
	
	private Move() {
		name = "";
		type = null;
		power = 0;
		accuracy = 0;
		description = null;
	}
	
	private Move(String n, Type t, int p, int a, String d) {
		name = n;
		type = t;
		power = p;
		accuracy = a;
		description = d;
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	public int getPower() {
		return power;
	}
	
	public int getAccuracy() {
		return accuracy;
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
	
	public void setPower(int p) {
		power = p;
	}
	
	public void setAccuracy(int a) {
		accuracy = a;
	}
	
	public void setDescription(String d) {
		description = d;
	}
}
