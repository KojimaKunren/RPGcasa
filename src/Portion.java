
public class Portion extends RecoveryItem {
	private int recover;
	
	public Portion(String name, int rarity, int num, int recover, int price) {
		super.setName(name);
		super.setRarity(rarity);
		super.setNum(num);
		super.setPrice(price);
		setRecover(recover);
	}
	
	public int getRecover() {
		return this.recover;
	}
	
	public void setRecover(int recover) {
		this.recover = recover;
	}

	
}
