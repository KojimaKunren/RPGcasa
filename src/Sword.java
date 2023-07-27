
public class Sword extends Weapon {
	private int power;
	
	public void Sword(String name, int num, int rarity, int level, int price, int power) {
		super.setName(name);
		super.setNum(num);
		super.setRarity(rarity);
		super.setLevel(level);
		super.setPrice(price);
		setPower(power);
	}
	
	public int getPower() {
		return this.power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
}
