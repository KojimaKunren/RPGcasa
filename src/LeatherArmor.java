
public class LeatherArmor extends Armor{
	
	private int price; 
	
	public LeatherArmor(String name, int rarity, int level, int def, int price) {
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
