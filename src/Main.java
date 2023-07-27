import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		
		//テキスト読み込み
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> textMain = new ArrayList<String>();
		String path = "textMain.txt";

		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		BufferedReader br = new BufferedReader(isr);

		String line = null;

		while ((line = br.readLine()) != null) {
			textMain.add(line);
		}
		
		//csv読み込み
		CsvReader csvReader = new CsvReader();

		//プレイヤーリスト
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		ArrayList<String> strList = csvReader.csvReader("playerList.csv");
		CreatePlayer createPlayer = new CreatePlayer();
		createPlayer.createPlayer(0,playerList, strList);
		
		//敵リスト
		ArrayList<String> enemies = new ArrayList<String>();
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		CreateEnemy createEnemy = new CreateEnemy();
		
		//武器仮置き
//		playerList.get(0).sword = dagger;
//		playerList.get(0).armor = leatherArmor;
//		playerList.get(0).sield = woodenSield;

		//フィールド
		ArrayList<Field> fields = new ArrayList<Field>();
		Field field = new Field();
		Town town1 = new Town("王都ギロッポンヌ");
		Town town2 = new Town("隣町モンブル");
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
		BlessingWater blessingWater = new BlessingWater("祝福の水",3,1,50,15);
		playerList.get(0).items = items;
		playerList.get(0).items.add(portion);
		
		//武器リスト
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		playerList.get(0).weapons = weapons;
		Sword dagger = new Dagger("ダガー", 1, 2, 2,10,10);
		Armor leatherArmor = new LeatherArmor("皮の鎧",1,2, 2,10,15);
		Sield woodenSield = new WoodenSield("木の盾",1,2,2,5,5);
		
		//バトル
		Battle battle = new Battle();
		ArrayList<String> levelUpList = new ArrayList<String>();
		playerList.get(0).leveluplist = levelUpList;
				
		//ショップ
		ArrayList<Shop> shopList = new ArrayList<Shop>();
		ArrayList<Weapon> shopWeaponList = new ArrayList<Weapon>();
		ArrayList<Item>shopItemList = new ArrayList<Item>();
		shopList.add(0,	new Inn("モーテラの宿屋", 10));
		shopList.add(1, new WeaponShop("ポンズの武器屋", shopWeaponList));
		shopList.add(2, new Pharmacy("グッラーダの薬屋", shopItemList));
		shopWeaponList.add(0, dagger);
		shopWeaponList.add(1, leatherArmor);
		shopWeaponList.add(2, woodenSield);
		shopItemList.add(0, portion);
		shopItemList.add(1, hiportion);
		shopItemList.add(2, blessingWater);
		
		

		//Start	
		System.out.print("プレイヤー名を入力してください>");
		playerList.get(0).setName(scan.nextLine());
		System.out.printf("%sの冒険を始めます↲\n\n「s」でステータス、「i」でアイテム、「w」で装備品が確認できます。\n「t」で街内の移動、「m」でマップの移動ができます。\n"
				+ "",playerList.get(0).getName());
		System.out.println("");
		
		//テキスト読み込み
		for (int i = 0; i < textMain.size(); i++) {
			if (textMain.get(i).contains("pn")) {
				System.out.println(textMain.get(i).replace("pn", playerList.get(0).getName()) + "↲");
				continue;
			}

			//バトル敵リスト作成
			if (textMain.get(i).contains("ec")) {
				String ec = textMain.get(i).replace("ec", "");
				String[] ecs = ec.split(",");
				int[] ecn = new int[2];
				for (int j = 1; j < ecs.length; j++) {
					ecn[j - 1] = Integer.parseInt(ecs[j]);
				}
				ArrayList<String> str2List = csvReader.csvReader("enemyList.csv");
				ecn[0] = (ecn[0] -1) * 8;
				createEnemy.createEnemy(ecn[0], ecn[1], enemyList, str2List);
				ec = textMain.remove(i);
			}

			//バトル実行
			if (textMain.get(i).contains("btl")) {
				battle.battle(playerList.get(0), playerList, enemyList, (Portion) items.get(0), levelUpList);
				playerList.get(0).setHp(battle.getPlayerDmg());
				//				battle.levelUp(playerList.get(0),playerList,levelUpList);
				if (playerList.get(0).getHp() <= 0) {
					return;
				}
				enemyList.clear();
			}
			
			//味方作成
			if(textMain.get(i).contains("pc")) {
				String pc = textMain.get(i).replace("pc", "");
				int pcn = Integer.parseInt(pc);
				ArrayList<String> strList4 = csvReader.csvReader("playerList.csv");
				createPlayer.createPlayer(pcn,playerList, strList4);
				textMain.remove(i);
				continue;
			}

			//コンソール表示入力
			String console = scan.nextLine();

			//ステータス表示
			if (console.equals("s")) {
				playerList.get(0).showStatus();
			}
			
			//アイテム表示
			if (console.equals("i")) {
				playerList.get(0).showItem(playerList.get(0), playerList, portion);
			}
			
			//装備品表示
			if (console.equals("w")) {
				playerList.get(0).showWeapon();
			}
			
			//街内移動
			if (console.equals("t")) {
				Town t = (Town) playerList.get(0).fields.get(0);
				t.townWalk(playerList, shopList, weapons, items);
			}

			//マップ移動
			if (console.equals("m")) {
				ArrayList<String> str2List = csvReader.csvReader("enemyList.csv");
				int[] nums = new int[4];
				
				nums[2] = new java.util.Random().nextInt(2) + 1 ;
				nums[3] = new java.util.Random().nextInt(2) + 1;
				nums[0] = 3; 
				nums[1] = 4;
				createEnemy.randomCreateEnemy(nums[0],nums[2],enemyList, str2List);
				createEnemy.randomCreateEnemy(nums[1],nums[3],enemyList, str2List);
				playerList.get(0).moveField(fields, battle, playerList.get(0), playerList, enemyList,
						(Portion) items.get(0), levelUpList);
			}

			//シーン移動
			if (textMain.get(i).contains("#")) {
				String str = textMain.get(i).replace("#", "");
				i = Integer.parseInt(str);
			}

			//			if (playerList.get(0).field instanceof MidField) {
			//				MidField m = (MidField) playerList.get(0).field;
			//				int r = playerList.get(0).moveField(m);
			//				if (r <= 2) {
			//					battle.battle(playerList.get(0), playerList, enemyList, (Portion) items.get(0), levelUpList);
			//					playerList.get(0).setHp(battle.getPlayerDmg());
			//					//				battle.levelUp(playerList.get(0),playerList,levelUpList);
			//					if (playerList.get(0).getHp() <= 0) {
			//						return;
			//					}
			//				}
			//			}

			System.out.println(textMain.get(i) + "↲");
			//テキスト行間用
			//			String waitEnter = scan.nextLine();
			//			if(waitEnter.equals("\n"));

	}
		//会話選択
		//		int s = player.talk(enemy1.name);
		//		if (s == 0)
		//			System.out.println(textMain.get(2));
		//		else if (s == 1)
		//			System.out.println(textMain.get(3));

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
