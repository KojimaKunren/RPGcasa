
public abstract class Sield extends Armor{
	
	private int price; 
	
	public Sield(String name, int rarity, int level, int def, int price) {
		super.setName(name);
		super.setRarity(rarity);
		super.setLevel(level);
		super.setDef(def);
		setPrice(price);
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
