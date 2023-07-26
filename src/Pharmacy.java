import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacy extends Shop {
	
	ArrayList<Item> items = new ArrayList<Item>();

	Scanner scan = new java.util.Scanner(System.in);

	//コンストラクタ
	public Pharmacy(String name, ArrayList<Item> items) {
		super.setName(name);
		setItemList(items);
	}
	
	public ArrayList<Item>getItemList(){
		return this.items;
	}
	
	public void setItemList(ArrayList<Item> items) {
		this.items = items;
	}

	public void talk(ArrayList<Player> playerList) {
	}

	public void talk(ArrayList<Player> playerList, ArrayList<Item> itemList) {
		System.out.println(this.getName() + "に入った\\n\\nやあ、調子はどうだい\n何か入り用かい");
		System.out.println("0: 買い物をする 1: アイテムを売る 2: やめておく");
		int select = scan.nextInt();
		ArrayList<RecoveryItem> castPrice = new ArrayList<RecoveryItem>();
		switch (select) {
		//買う
		case 0:
			//商品リストの表示
			while (true) {
			for (int i = 0; i < this.items.size(); i++) {
				System.out.print((i + 1) + ":" + this.items.get(i).getName());
				castPrice.add( (RecoveryItem) this.items.get(i));
				System.out.println(castPrice.get(i).getPrice());
			}
			System.out.println("薬屋のグラッド「何番を買う」>");
			int selectItem = scan.nextInt() - 1;
			//購入数の取得
			System.out.println("薬屋のグラッド「" + this.items.get(selectItem).getName() + "をいくつ必要なんだい」>");
			int itemNum = scan.nextInt();
			//購入金額の表示および購入確認
			int itemPrice = itemNum * castPrice.get(selectItem).getPrice();
			System.out.println("薬屋のグラッド「" +
					this.items.get(selectItem).getName() + itemNum + "×" + castPrice.get(selectItem).getPrice()
					+ "=" + itemPrice + "\nこれで良いかい」\n 0:買う　1:やめておく>");
			int confirmation = scan.nextInt();
			//購入処理
			if (confirmation == 0) {
				//代金の処理
				if (itemPrice <= playerList.get(0).getWallet()) {
					playerList.get(0).setWallet(playerList.get(0).getWallet() - itemPrice);
				} else if (itemPrice > playerList.get(0).getWallet()) {
					System.out.println("武器屋のグラッド「足りないなら別のにしときな」\n");
					continue;
				}
				//商品の処理;
				boolean b = playerList.get(0).items.contains(this.items.get(itemNum).getName());
				if (b) {
					int a = playerList.get(0).items.indexOf(this.items.get(itemNum).getName());
					playerList.get(0).items.get(a).setNum(playerList.get(0).items.get(selectItem).getNum() + itemNum);
				} else if (!b) {
					playerList.get(0).items.add(this.items.get(itemNum));
				}
			} else {
			}
			System.out.println("薬屋のグラッド「ほかに何かいるかい」\n 0: 買い物を続ける 1: 店を出る>");
			int select2 = scan.nextInt();
			if (select2 == 0)
				continue;
			else if (select2 == 1) {
				System.out.println("薬屋のグラッド「使用上の注意をよく読んでな」\n");
				break;
			}
		}
			break;

		case 1:
			System.out.println("薬屋のグラッド「開発中だよ」");
			System.out.println("薬屋のグラッド「ほかに何かいるかい」\n 0: 買い物を続ける 1: 店を出る>");
			int select3 = scan.nextInt();
			if (select3 == 0)
				;
			else if (select3 == 1) {
				System.out.println("薬屋のグラッド「またね」\n");
				break;
			}
			break;

		case 2:
			System.out.println("薬屋のグラッド「命大事に」\n");
		default:
		}
	}
}
