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
		

		//プレイヤーリスト
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		CreatePlayer createPlayer = new CreatePlayer();
		createPlayer.createPlayers(players, playerList);

		//武器仮置き
//		playerList.get(0).sword = dagger;
//		Armor leatherArmor = new LeatherArmor("皮の鎧",2, 2,10,15);
//		playerList.get(0).armor = leatherArmor;
//		Sield woodenSield = new WoodenSield("木の盾",2,2,5,5);
//		playerList.get(0).sield = woodenSield;

		//敵リスト
//		CreateEnemy createEnemy = new CreateEnemy();
//		createEnemy.createEnemies(enemies, enemyList);
		
		//フィールド
		ArrayList<Field> fields = new ArrayList<Field>();
		Field field = new Field();
		Town town1 = new Town("ギロッポンヌ");
		fields.add(town1);
		MidField midfield1 = new MidField("王都周辺", 30);
		fields.add(midfield1);
		playerList.get(0).fields = fields;
		
		//アイテムリスト
		ArrayList<Item> items = new ArrayList<Item>();
		Portion portion = new Portion("ポーション", 1, 1, 20, 10);
		Portion hiportion = new HiPortion("ハイポーション", 5, 1, 100, 20);
		BlessingWater blessingWater = new BlessingWater("祝福の水",3,1,50,15);
		items.add(0, portion);
		items.add(1, hiportion);
		items.add(2, blessingWater);
		
		playerList.get(0).items = items;
		
		//武器リスト
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		playerList.get(0).weapons = weapons;
		Sword dagger = new Dagger("ダガー", 1, 2, 2, 10);
		
		//バトル
		Battle battle = new Battle();
		ArrayList<String> levelUpList = new ArrayList<String>();
		playerList.get(0).leveluplist = levelUpList;
				
		//ショップ
		ArrayList<Shop> shopList = new ArrayList<Shop>();
		ArrayList<Weapon> shopWeaponList = new ArrayList<Weapon>();
		ArrayList<Item>shopItemList = new ArrayList<Item>();
		Inn inn = new Inn("宿屋「ニューイン」", 10);
		shopList.add(0,inn);
		shopList.add(1, new WeaponShop("武器屋「ディープヴァレイ」", shopWeaponList));
		shopList.add(2, new Pharmacy("薬屋「センターガイ」", shopItemList));
		shopWeaponList.add(0, dagger);
		shopItemList.add(0, portion);
		shopItemList.add(1, hiportion);
		shopItemList.add(2, blessingWater);
		
		
		CreateAdd createAdd = new CreateAdd();
		CsvReader csvReader = new CsvReader();

		//Start
		System.out.print("プレイヤー名を入力してください>");
		playerList.get(0).setName(scan.nextLine());
		System.out.printf("%sの冒険を始めます↲\n\n「s」でステータス、「i」でアイテムが確認できます。\n「t」で街内の移動、「m」でマップの移動ができます。\n"
				+ "",playerList.get(0).getName());
		System.out.println("");

		for (int i = 0; i < textMain.size(); i++) {
			if(textMain.get(i).contains("pn")) {
				System.out.println(textMain.get(i).replace("pn",playerList.get(0).getName()) + "↲");
				continue;
			}
			//コンソール表示入力
			String console = scan.nextLine();
			
			//ステータス表示
			if(console.equals("s")) {
				System.out.println(Player.showStatus(playerList.get(0)));
			}
			//アイテム表示
			if(console.equals("i")) {
				System.out.println(Player.showItem(playerList.get(0)));
			}
			
			//街内移動
			if(console.equals("t")) {
				Town t = (Town)playerList.get(0).fields.get(0);
				t.townWalk(playerList, shopList, weapons, items);
			}
			
			//マップ移動
			if(console.equals("m")) {
				playerList.get(0).moveField(fields);
			}
			
			//シーン移動
			if (textMain.get(i).contains("#")) {
				String str = textMain.get(i).replace("#", "");
				i = Integer.parseInt(str);
			} 
			
			//敵作成
			ArrayList<String> enemies = new ArrayList<String>();
			ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
			if (textMain.get(i).contains("ec")) {
				String ec = textMain.get(i).replace("ec", "");
				String[] ecs = ec.split(",");
				int[] ecn = new int[2];
				for (int j = 0; j < ecs.length; j++) {
					ecn[j] = Integer.parseInt(ecs[j]);
				}
				ArrayList<String> strList = csvReader.csvReader("enemyList.csv");
				createAdd.createAdd(ecn[0], ecn[1], enemyList, strList);
			}
			
			
			if (textMain.get(i).contains("btl")) {
				battle.battle(playerList.get(0), playerList, enemyList, (Portion) items.get(0),levelUpList);
				playerList.get(0).setHp(battle.getPlayerDmg());
//				battle.levelUp(playerList.get(0),playerList,levelUpList);
				if(playerList.get(0).getHp() <= 0) {
					return;
				}

			}
			
			
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
