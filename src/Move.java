public class Move {
	
	private String name;
	private String type;
	private int power;
	private int accuracy;
	private String description;
	
	public Move() {
		name = null;
		type = null;
		power = 0;
		accuracy = 0;
		description = null;
	}
	
	public Move(String n, String t, int p, int a, String d) {
		name = n;
		type = t;
		power = p;
		accuracy = a;
		description = d;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
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
	
	public void setType(String t) {
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
