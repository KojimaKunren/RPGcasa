
public class Armor extends Weapon {
	private int def;
	
	public void Armor(String name, int num,int rarity, int level, int price,int def) {
			super.setName(name);
			super.setNum(num);
			super.setRarity(rarity);
			super.setLevel(level);
			super.setPrice(price);
			setDef(def);
		}
	
	public int getDef() {
		return this.def;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
}
