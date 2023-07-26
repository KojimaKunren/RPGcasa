
public class HiPortion extends Portion{
	
	public HiPortion(String name, int rarity, int num, int recover, int price) {
		super(name, rarity, num, recover, price);
	}
	
	public void setName(String name) {
		super.setName(name);
	}
	
	public String getName() {
		return super.getName();
	}
	
	public void  setRarity(int rarity) {
		super.setRarity(rarity);
	}
	
	public int getRarity() {
		return super.getRarity();
	}
	
	public void setNum(int num) {
		super.setNum(super.getNum() + num);		
	}
	
	public int getNum() {
		return super.getNum();
	}
	public int getRecover() {
		return super.getRecover();
	}
	
	public void setRecover(int recover) {
		super.setRecover(recover);
	}
	
	public int getPrice() {
		return super.getPrice();
	}
	
	public void setPrice(int price) {
		super.setPrice(price);
	}
	
	public void usePortion(Player player) {
		player.setHp(player.getHp() + this.getRecover());
	}

}
