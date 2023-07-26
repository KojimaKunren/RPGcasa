
public abstract class Weapon {
	private String name;
	private int num; 
	private int rarity;
	private int level;
	private int price;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getRarity() {
		return this.rarity;
	}
	
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
