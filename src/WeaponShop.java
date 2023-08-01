import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WeaponShop extends Shop {

	private ArrayList<Weapon> weapons;

	Scanner scan = new java.util.Scanner(System.in);

	public WeaponShop(String name, ArrayList<Weapon> weapons) {
		super.setName(name);
		setWeaponList(weapons);
	}

	public ArrayList<Weapon> getWeaponList() {
		return this.weapons;
	}

	public void setWeaponList(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}

	public void talk(ArrayList<Player> playerList) {
	}

	public void talk(ArrayList<Player> playerList, ArrayList<Weapon> weaponList) {
		System.out.println(this.getName() + "に入った\n\n武器屋のポンズ「やあ、調子はどうだい」\n「何か入り用かい」\n");
		loop:
		do {
			try {
				System.out.println("0: 買い物をする 1: アイテムを売る 2: やめておく");
				int select = new java.util.Scanner(System.in).nextInt();
				switch (select) {
				//買う
				case 0:
					//商品リストの表示
					while (true) {
						for (int i = 0; i < this.weapons.size(); i++) {
							System.out.println((i + 1) + ":" + this.weapons.get(i).getName() + " price: "
									+ this.weapons.get(i).getPrice());
						}
						System.out.println("武器屋のポンズ「何番を買う」>");
						int selectItem = new java.util.Scanner(System.in).nextInt();
						//購入数の取得
						System.out.println("武器屋のポンズ「" + this.weapons.get(selectItem - 1).getName() + "」をいくつ買う>");
						int itemNum = new java.util.Scanner(System.in).nextInt();
						//購入金額の表示および購入確認
						int itemPrice = itemNum * this.weapons.get(selectItem - 1).getPrice();
						System.out.println("武器屋のポンズ「" +
								this.weapons.get(selectItem - 1).getName() + itemNum + "×"
								+ this.weapons.get(selectItem - 1).getPrice()
								+ "=" + itemPrice + "」\nこれで良いかい」\n 0:買う　1:やめておく>");
						int confirmation = new java.util.Scanner(System.in).nextInt();
						//購入処理
						if (confirmation == 0) {
							//代金の処理
							if (itemPrice <= playerList.get(0).getWallet()) {
								playerList.get(0).setWallet(playerList.get(0).getWallet() - itemPrice);
							} else if (itemPrice > playerList.get(0).getWallet()) {
								System.out.println("武器屋のポンズ「足りないなら別のにしときな」\n");
								continue;
							}
							//商品の処理;
							boolean b = playerList.get(0).weapons.contains(this.weapons.get(selectItem - 1).getName());
							if (b) {
								int a = playerList.get(0).weapons.indexOf(this.weapons.get(selectItem - 1).getName());
								playerList.get(0).weapons.get(a).setNum(+1);
								//					playerList.get(0).weapons.get(a).setNum(playerList.get(0).weapons.get(selectItem-1).getNum() + itemNum);
							} else if (!b) {
								playerList.get(0).weapons.add(this.weapons.get(selectItem - 1));
							}
						} else {
						}
						System.out.println("武器屋のポンズ「ほかに何かいるかい」\n 0: 買い物を続ける 1: 店を出る>");
						int select2 = new java.util.Scanner(System.in).nextInt();
						if (select2 == 0)
							continue;
						else if (select2 == 1) {
							System.out.println("武器屋のポンズ「また来るまで死ぬんじゃないよ」\n");
							break;
						}
					}
					break;

				case 1:
					System.out.println("武器屋のポンズ「開発中だよ」");
					System.out.println("武器屋のポンズ「ほかに何かいるかい」\n 0: 買い物を続ける 1: 店を出る>");
					int select2 = new java.util.Scanner(System.in).nextInt();
					if (select2 == 0)
						continue;
					else if (select2 == 1) {
						System.out.println("武器屋のポンズ「また来るまで死ぬんじゃないよ」\n");
						break;
					}
					break;

				case 2:
					System.out.println("武器屋のポンズ「また来るまで死ぬんじゃないよ」\n");
					break loop;
				default:
					System.out.printf("正しい番号を入力してください\n");
				}
			} catch (InputMismatchException e) {
				System.out.printf("正しい番号を入力してください\n");
			}
		} while (!true);
	}
}
