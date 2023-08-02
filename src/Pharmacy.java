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
		boolean isOut = false;
		System.out.println(this.getName() + "に入った\n\n薬屋のグッラーダ「やあ、調子はどう。何か入り用かい」");
		do {
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
			System.out.println("薬屋のグッラーダ「何番を買う」>");
			int selectItem = scan.nextInt() - 1;
			//購入数の取得
			System.out.println("薬屋のグッラーダ「" + this.items.get(selectItem).getName() + "をいくつ必要なんだい」>");
			int itemNum = scan.nextInt();
			//購入金額の表示および購入確認
			int itemPrice = itemNum * castPrice.get(selectItem).getPrice();
			System.out.printf("薬屋のグッラーダ「%sを%dつで%dポンギだ、これで良いかい」\n 0:買う　1:やめておく>" ,
					this.items.get(selectItem).getName(),itemNum , itemPrice);
			int confirmation = scan.nextInt();
			//購入処理
			if (confirmation == 0) {
				//代金の処理
				if (itemPrice <= playerList.get(0).getWallet()) {
					playerList.get(0).setWallet(playerList.get(0).getWallet() - itemPrice);
				} else if (itemPrice > playerList.get(0).getWallet()) {
					System.out.println("武器屋のグッラーダ「足りないなら別のにしときな」\n");
					continue;
				}
				//商品の処理;
				boolean b = playerList.get(0).items.contains(this.items.get(selectItem));
				String s = this.items.get(selectItem).getName();
				if (b) {
					int a = playerList.get(0).items.indexOf(this.items.get(selectItem));
					playerList.get(0).items.get(a).setNum(playerList.get(0).items.get(a).getNum() + itemNum);
				} else if (!b) {
					playerList.get(0).items.add(this.items.get(selectItem));
				}
			} else {
			}
			System.out.println("薬屋のグッラーダ「ほかに何かいるかい」\n 0: 買い物を続ける 1: 店を出る>");
			int select2 = scan.nextInt();
			if (select2 == 0)
				;
			else if (select2 == 1) {
				System.out.println("薬屋のグッラーダ「使用上の注意をよく読んでな」\n");
				isOut = true;
				break;
			}
		}
		break;

		case 1:
			System.out.println("薬屋のグッラーダ「開発中だよ」");
			System.out.println("薬屋のグッラーダ「ほかに何かいるかい」\n 0: 買い物を続ける 1: 店を出る>");
			int select3 = scan.nextInt();
			if (select3 == 0)
				continue;
			else if (select3 == 1) {
				System.out.println("薬屋のグッラーダ「またね」\n");
				isOut = true;
				break;
			}
			break;

		case 2:
			System.out.println("薬屋のグッラーダ「命大事にね」\n");
			isOut = true;
			break;
		default:
			System.out.println("正しい数字を入力してください");
		}
		}while(!isOut);
	}
}
