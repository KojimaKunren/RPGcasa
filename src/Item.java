import java.util.ArrayList;
public abstract class Item {
	private String name;
	private int rarity;
	private int num;
	ArrayList<Item> items;
		
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void  setRarity(int rarity) {
		this.rarity = rarity;
	}
	
	public int getRarity() {
		return this.rarity;
	}
	
	public void setNum(int num) {
		this.num += num;		
	}
	
	public int getNum() {
		return this.num;
	}
	
	public ArrayList<Item> getItemList(){
		return this.items;
	}
	
	public void setItemList(ArrayList<Item> items) {
		this.items = items;
	}
	

}
