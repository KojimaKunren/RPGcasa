
public class Armor extends Weapon {
	private int def;
	
	public void Armor(String name, int rarity, int level, int def) {
			super.setName(name);
			super.setRarity(rarity);
			super.setLevel(level);
			setDef(def);
		}
	
	public int getDef() {
		return this.def;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
}
