
public class Sword extends Weapon {
	private int power;
	
	public void Sword(String name, int rarity, int level, int power) {
		super.setName(name);
		super.setRarity(rarity);
		super.setLevel(level);
		setPower(power);
	}
	
	public int getPower() {
		return this.power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
}
