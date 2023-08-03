import java.util.ArrayList;

public class BlessingWater extends RecoveryItem {
	
	private int recover;
	
	public BlessingWater(String name, int rarity, int num, int recover, int price) {
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
	
	public boolean recoveryBlessingWater(Player player, ArrayList<Player> playerList, Item recovery) {
		BlessingWater bw = (BlessingWater)recovery;
		int r = player.getMp() + this.getRecover();
		boolean b = false;
		if (r < player.getFullmp()) {
			player.setMp(player.getMp() + bw.getRecover());
			System.out.println("MPが" + bw.getRecover() + "回復しました");
		} else {
			player.setMp(player.getFullmp());
			System.out.println("MPがフルになりました\n");
		}
		int num = playerList.get(0).items.indexOf(recovery);
		System.out.println(num);
		playerList.get(0).items.get(num).setNum(playerList.get(0).items.get(num).getNum() - 1);
		if (playerList.get(0).items.get(num).getNum() == 0) {
			playerList.get(0).items.remove(num);
			b = true;
		}
		return b;
	}
}
