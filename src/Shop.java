import java.util.ArrayList;

public abstract class Shop{
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void talk(ArrayList<Player> playerList);
}