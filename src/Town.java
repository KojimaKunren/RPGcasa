import java.util.ArrayList;
import java.util.InputMismatchException;

public class Town extends Field {
	
	public Town(String name){
		super.setName(name);
	}
	
	public void townWalk(ArrayList<Player> playerList, ArrayList<Shop> shopList, ArrayList<Weapon> weaponList, ArrayList<Item> itemList) {
		loop:
		while(true) {
		try {
		System.out.println("0: 宿屋 1: 武器屋 2: 薬屋 3: レストラン>");
		int select = new java.util.Scanner(System.in).nextInt();
		switch (select) {
		case 0:
			shopList.get(0).talk(playerList);
			break loop;
		case 1:
			WeaponShop w = (WeaponShop)shopList.get(1);
			w.talk(playerList,weaponList);
			break loop;
		case 2:
			Pharmacy p = (Pharmacy)shopList.get(2);
			p.talk(playerList,itemList);
			break loop;
		case 3:
			System.out.println("開発中");
			break loop;
		default:
		}
		}catch(InputMismatchException e) {
			System.out.println("正しい数字を入力してください");
		}
		}
	}
}
