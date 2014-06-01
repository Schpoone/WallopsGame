
public class Init {

	public static void main(String[] args) {
		Game wallops = new Game();
		wallops.setOpponent(new MudDogWhelk());
		wallops.update();
	}

}
