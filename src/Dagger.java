
public class Dagger extends Sword{
	private int price;
	
	public Dagger(String name, int rarity, int level, int power, int price) {
		super.setName(name);
		super.setRarity(rarity);
		super.setLevel(level);
		super.setPower(power);
		setPrice(price);
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
}
