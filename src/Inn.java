import java.util.ArrayList;

public class Inn extends Shop{
	private int price;
	
	public Inn(String name, int price) {
		super.setName(name);
		setPrice(price);
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void talk(Player Player) {}
	
	public void talk(ArrayList<Player> playerList) {
		System.out.print("宿屋のおかみ「泊まっていくかい。」\n0:泊まる 1:やめておく");
		int select = new java.util.Scanner(System.in).nextInt();
		switch(select) {
		case 0:
			System.out.println("\n宿屋のおかみ「一泊15ね。2階の奥を使いな」\n…\n");
			for(int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setHp(playerList.get(i).getFullhp());
			System.out.printf("HP、MPが回復しました。%s HP:%d MP:%d\n",playerList.get(i).getName(), playerList.get(i).getHp(), playerList.get(i).getMp());
			}
			playerList.get(0).setWallet(playerList.get(0).getWallet() -15);
			System.out.printf("宿屋のおかみ 「またご贔屓に」\n\n");
			break;
			
		case 1:
			System.out.println("\n…\n宿屋のおかみ 「冷やかしならよそでやっとくれ」\n");
			break;
			default:
		}
	}
}
