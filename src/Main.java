import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);

		//テキスト読み込み
		ArrayList<String> textMain = new ArrayList<String>();
		TextReader textReader = new TextReader();

		//csv読み込み
		CsvReader csvReader = new CsvReader();

		//プレイヤーリスト
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		ArrayList<String> strList = csvReader.csvReader("playerList.csv");
		PlayerCreater playerCreater = new PlayerCreater();
		playerCreater.createPlayer(0, playerList, strList);

		//敵リスト
		ArrayList<String> enemies = new ArrayList<String>();
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		EnemyCreater enemyCreater = new EnemyCreater();

		//フィールド
		ArrayList<Field> fields = new ArrayList<Field>();
		Field field = new Field();
		Town town1 = new Town("王都メトロ・ギロッポンヌ");
		Town town2 = new Town("隣町アオマ・ウンテン");
		Field slum = new Slum("裏町");
		fields.add(town1);
		fields.add(town2);
		MidField midfield1 = new MidField("王都周辺の道", 30);
		fields.add(midfield1);
		playerList.get(0).field = town1;
		playerList.get(0).fields = fields;

		//アイテムリスト
		ArrayList<Item> items = new ArrayList<Item>();
		Portion portion = new Portion("ポーション", 1, 1, 20, 10);
		Portion hiportion = new HiPortion("ハイポーション", 5, 1, 100, 20);
		BlessingWater blessingWater = new BlessingWater("祝福の水", 3, 1, 50, 15);
		playerList.get(0).items = items;

		//武器リスト
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		Sword dagger = new Dagger("ダガー", 1, 2, 2, 10, 10);
		Armor leatherArmor = new LeatherArmor("皮の鎧", 1, 2, 2, 10, 15);
		Sield woodenSield = new WoodenSield("木の盾", 1, 2, 2, 5, 5);
		playerList.get(0).weapons = weapons;

		//バトル
		Battle battle = new Battle();
		ArrayList<String> levelUpList = new ArrayList<String>();
		playerList.get(0).leveluplist = levelUpList;

		//ショップ
		ArrayList<Shop> shopList = new ArrayList<Shop>();
		ArrayList<Weapon> shopWeaponList = new ArrayList<Weapon>();
		ArrayList<Item> shopItemList = new ArrayList<Item>();
		shopList.add(0, new Inn("モーテラの宿屋", 10));
		shopList.add(1, new WeaponShop("ポンズの武器屋", shopWeaponList));
		shopList.add(2, new Pharmacy("グッラーダの薬屋", shopItemList));
		shopWeaponList.add(0, dagger);
		shopWeaponList.add(1, leatherArmor);
		shopWeaponList.add(2, woodenSield);
		shopItemList.add(0, portion);
		shopItemList.add(1, hiportion);
		shopItemList.add(2, blessingWater);
		

		//Start	
		while (true) {
			System.out.print("プレイヤー名を入力してください>");
			playerList.get(0).setName(scan.nextLine());
			if (!playerList.get(0).getName().equals("")) {
				break;
			} else
				System.out.println("\nプレイヤー名が入力されていません\n");
		}
		System.out.printf("%sの冒険を始めます↲\n", playerList.get(0).getName());
		System.out.println("");

		//テキスト読み込み
		textReader.textReader(textMain, "textMain.txt");
		int i = 1;
		do {
//			textMain.get(i);

			//コンソール表示入力
			String console = scan.nextLine();

//			if (textMain.get(i).contains("pn")) {
				textMain.get(i).replace("pn", playerList.get(0).getName());
//			}

			//バトル敵リスト作成
			if (textMain.get(i).contains("ec")) {
				String ec = textMain.get(i).replace("ec", "");
				String[] ecs = ec.split(",");
				int[] ecn = new int[2];
				for (int j = 1; j < ecs.length; j++) {
					ecn[j - 1] = Integer.parseInt(ecs[j]);
				}
				ArrayList<String> str2List = csvReader.csvReader("enemyList.csv");
				ecn[0] = (ecn[0] - 1) * 8;
				enemyCreater.createEnemy(ecn[0], ecn[1], enemyList, str2List);
				ec = textMain.remove(i);
			}

			//味方作成
			if (textMain.get(i).contains("pc")) {
				String pc = textMain.get(i).replace("pc,", "");
				int pcn = Integer.parseInt(pc);
				ArrayList<String> strList4 = csvReader.csvReader("playerList.csv");
				playerCreater.createPlayer(pcn, playerList, strList4);
				i += 1;
				continue;
			}

			//バトル実行
			if (textMain.get(i).contains("btl")) {
				battle.battle(playerList, enemyList, levelUpList, i);
				//				playerList.get(0).setHp(battle.getPlayerDmg());
				//				//				battle.levelUp(playerList.get(0),playerList,levelUpList);
				if (i == 28) {
					i = 29;
					playerList.get(0).setHp(playerList.get(0).getFullhp());
				} else if (i == 143) {
					i = 144;
					playerList.get(0).setHp(playerList.get(0).getFullhp());
				} else if (playerList.get(0).getHp() <= 0) {
					return;

				}
				enemyList.clear();

			}
			//ステータス表示
			if (console.equals("s")) {
				playerList.get(0).showStatus(playerList);
			}

			//アイテム表示
			if (console.equals("i")) {
				playerList.get(0).showItem(playerList);
			}

			//装備品表示
			if (console.equals("w")) {
				playerList.get(0).showWeapon(playerList);
			}

			//街内移動
			if (console.equals("t")) {
				Town t = (Town) playerList.get(0).fields.get(0);
				t.townWalk(playerList, shopList, weapons, items);
			}

			//マップ移動
			if (console.equals("m")) {
				boolean isDead = playerList.get(0).moveField(fields, battle, playerList.get(0), playerList, csvReader,
						enemyCreater, enemyList,
						levelUpList, i);
				if (isDead)
					return;
			}

			//シーン移動
			if (textMain.get(i).contains("#")) {
				String str = textMain.get(i).replace("#", "");
				i = Integer.parseInt(str);
			}

			//ウォレット処理
			if (textMain.get(i).contains("wa")) {
				String money = textMain.get(i).replace("wa,", "");
				int m = Integer.parseInt(money);
				playerList.get(0).setWallet(playerList.get(0).getWallet() + m);
				i += 1;
			}

			//シーン移動(分岐処理)
			if (textMain.get(i).contains("sn")) {
				String str = textMain.get(i).replace("sn", "");
				int[] num = new int[3];
				String[] s = str.split(",");
				for (int j = 1; j < s.length; j++) {
					num[j - 1] = Integer.parseInt(s[j]);
				}

				while (true) {
					if (console.equals("0") || console.equals("1")) {
						int select = Integer.parseInt(console);
						i = num[(select)];
						break;
					} else {
						System.out.println("正しい数字を入力してください");
						//					i = i - 2;
						console = new java.util.Scanner(System.in).nextLine();
					}
				}
			}

			//END
			if (textMain.get(i).contains("END")) {
				playerList.get(0).isDead(i);
				try {
					System.out.println("最初から始めますか？ 0: リスタート 1: 終了>");
					int n = new java.util.Scanner(System.in).nextInt();
					if (n == 0)
						i = 1;
					else
						return;
				} catch (InputMismatchException e) {
					System.out.println("正しい数字を入力してください");
				}
			}

			if (textMain.get(i).contains("pn")) {
				textMain.get(i).replace("pn", playerList.get(0).getName());
			}
			System.out.println(textMain.get(i) + "↲");
			i++;
		} while (true);

		/*
			ダガーを装備
			player.sword = dagger;
			player.sword.setName("ダガー");
			player.sword.setPower(15);
		*/

		/*
		 	鎧を装備
		 	player.armor = ironArmor;
		 	player.armor.setName("鉄の鎧");
		 	player.armor.set20);
		 */

	}

}
