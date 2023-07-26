import java.util.ArrayList;

public class Town extends Field {
	
	public Town(String name){
		super.setName(name);
	}
	
	public void townWalk(ArrayList<Player> playerList, ArrayList<Shop> shopList, ArrayList<Weapon> weaponList, ArrayList<Item> itemList) {
		System.out.println("0: 宿屋 1: 武器屋 2: 薬屋 3: レストラン>");
		int select = new java.util.Scanner(System.in).nextInt();
		switch (select) {
		case 0:
			shopList.get(0).talk(playerList);
			break;
		case 1:
			WeaponShop w = (WeaponShop)shopList.get(1);
			w.talk(playerList,weaponList);
			break;
		case 2:
			Pharmacy p = (Pharmacy)shopList.get(2);
			p.talk(playerList,itemList);
			break;
		case 3:
			System.out.println("開発中");
		default:
		}
	}
}
