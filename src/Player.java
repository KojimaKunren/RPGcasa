import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player implements Human {
	private String name;
	private String role;
	private int hp;
	private int fullhp;
	private int mp;
	private int fullmp;
	private int atk;
	private int def;
	private int exp;
	private int level;
	private int money;
	private int wallet;
	Sword sword;
	Armor armor;
	Sield sield;
	Field field;
	ArrayList<Field> fields;
	ArrayList<Item> items;
	ArrayList<Weapon> weapons;
	ArrayList<String> leveluplist;
	
	public Player(String name, String role, int hp, int fullhp, int mp, int fullmp, int atk, int def, int exp, int level, int money) {
		setName(name);
		setRole(role);
		setHp(hp);
		setFullhp(fullhp);
		setMp(mp);
		setFullmp(fullmp);
		setAtk(atk);
		setDef(def);
		setExp(exp);
		setLevel(level);
		setWallet(this.getWallet() + money);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getFullhp() {
		return this.fullhp;
	}
	
	public void setFullhp(int fullhp) {
		this.fullhp = fullhp;
	}
	
	public int getMp() {
		return this.mp;
	}
	
	public void setMp(int mp) {
		this.mp = mp;
	}
	
	public int getFullmp() {
		return this.fullmp;
	}
	
	public void setFullmp(int fullmp) {
		this.fullmp = fullmp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getWallet() {
		return this.wallet;
	}
	
	public void setWallet(int money) {
		this.wallet = money;
	}
	
	public Weapon getSword() {
		return this.sword;
	}
	
	//通常攻撃メソッド
	public static int attack(Player player,Enemy enemy) {
		int dmg = 0;
		if(player.sword == null) {
		dmg = player.getAtk() - enemy.getDef();
		}else {
			dmg = (player.getAtk() + player.sword.getPower()) - enemy.getDef();
		}
		
		int value = new java.util.Random().nextInt(6);
		
		if (value != 0 && dmg > 0) {
			enemy.setHp(enemy.getHp() - dmg);
		}
		if(dmg > 0) {
		System.out.printf("%sの攻撃! %sに%dのダメージを与えた\n\n",player.getName(),enemy.getName(),dmg);
		}else if(dmg <= 0)System.out.printf("%sの攻撃! %sにダメージを与えられなかった\n\n",player.getName(),enemy.getName());
		return enemy.getHp();
	}
	
	//特殊攻撃メソッド
	public static int specialAttack(Player player,Enemy enemy) {
		int dmg = 0;
		if(player.getMp() >= 10) {
			if (player.sword == null) {
				dmg = (player.getAtk() * 2) - enemy.getDef();
				player.setMp(player.getMp() - 10);
			} else {
				dmg = ((player.getAtk() + player.sword.getPower()) * 2) - enemy.getDef();
				player.setMp(player.getMp() - 10);
			}

		
		int value = new java.util.Random().nextInt(6);
		
		if (value != 0 && dmg > 0) {
			enemy.setHp(enemy.getHp() - dmg);
			}
		}else System.out.println("MPが足りません");
		if(dmg > 0) {
			System.out.printf("%sの攻撃! %sに%dのダメージを与えた\n\n",player.getName(),enemy.getName(),dmg);
		}else if(dmg <= 0)System.out.printf("%sの攻撃! %sにダメージを与えられなかった\n\n",player.getName(),enemy.getName());
		return enemy.getHp();
	}
	
	//GameOver判定
	public int isDead(int lineNum) {
		int r = 0;
		if(lineNum == 28) {
			r = 1;
		}else {
//		boolean isEnd = true;
		System.out.println("\nGAME OVER\n物語はまだはじまらない…");
		}
		return r;
	}
	
	//会話メソッド
	public int talk(String name) {
		System.out.println(name + "に話しかけますか");
		System.out.print("0 : 話しかける\t1 : やめておく");
		int select = new java.util.Scanner(System.in).nextInt();
		return select;

	}
	
	//ステータス表示メソッド
	public void showStatus(ArrayList<Player> playerList) {
//		try {
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("プレイヤー名: " + playerList.get(i).getName() + " 職業: " + playerList.get(i).getRole() + "\nHP: " + playerList.get(i).getHp() + "/"
					+ playerList.get(i).getFullhp()
					+ " MP: " + playerList.get(i).getMp() + "/" + playerList.get(i).getFullmp() + "\n攻撃力: " + playerList.get(i).getAtk() + " 防御力: "
					+ playerList.get(i).getDef() + "\nレベル: "
					+ playerList.get(i).getLevel() + " 経験値: " + playerList.get(i).getExp() + "\n" + "現在地: " + playerList.get(0).field.getName() + "\n");
		}
//		}catch(NullPointerException e) {
//			;
//		}

	}
	
	//装備品表示メソッド
	public void showWeapon(ArrayList<Player> playerList) {
		do {
			if (!playerList.get(0).weapons.isEmpty()) {
				for (int i = 0; i < playerList.get(0).weapons.size(); i++) {
					System.out.println((i + 1) + ": " + playerList.get(0).weapons.get(i).getName() + "×"
							+ playerList.get(0).weapons.get(i).getNum());
				}
			}else {
				System.out.println("装備できる武器がありません\n");
				break;
			}

			System.out.println("装備する武器の番号を入力してください。装備しない場合は100を入力してください（開発中処理）>");
			int select = new java.util.Scanner(System.in).nextInt() - 1;
			if (select == 99)
				break;
			if(select >= weapons.size()) {
				System.out.println("正しい数字を入力してください");
				continue;
			}
			System.out.printf("%sを誰に装備しますか>\n",playerList.get(0).weapons.get(select).getName());
			for(int i = 0; i < playerList.size(); i++) {
				System.out.println(i + ":" + playerList.get(i).getName());
			}
			int select2 = new java.util.Scanner(System.in).nextInt();
			
			if (playerList.get(0).weapons.get(select) instanceof Sword) {
				playerList.get(select2).sword = (Sword) weapons.get(select);
				System.out.printf("%sを装備しました\n",playerList.get(0).weapons.get(select).getName());
				playerList.get(0).weapons.get(select).setNum(playerList.get(0).weapons.get(select).getNum() - 1);
			}else { System.out.println("現在装備できません");
			}
			if (playerList.get(0).weapons.get(select) instanceof Sield) {
				playerList.get(select2).sield = (Sield) weapons.get(select);
				System.out.printf("%sを装備しまし\n",playerList.get(0).weapons.get(select).getName());
				playerList.get(0).weapons.get(select).setNum(playerList.get(0).weapons.get(select).getNum() - 1);
			} else {{ System.out.println("現在装備できません");
			}
			if (playerList.get(0).weapons.get(select) instanceof Armor) {
				playerList.get(select2).armor = (Armor) weapons.get(select);
				System.out.printf("%sを装備しました\n",playerList.get(0).weapons.get(select).getName());
				playerList.get(0).weapons.get(select).setNum(playerList.get(0).weapons.get(select).getNum() - 1);
			} else { System.out.println("現在装備できません");
			}
			}
			
			System.out.println("");
		} while (true);
	}
	
	//支払い
	public void payMoney(Player player, int money) {
		if (money <= this.getWallet()) {
			int n = this.getWallet() - money;
			this.setWallet(n);
		} else if (money <= 0) {
			System.out.println("お金が足りません");
		}
	}
	
	//アイテム表示メソッド
	public void showItem(ArrayList<Player> playerList) {
		System.out.println("財布：" + this.getWallet());
		do {
			if (!playerList.get(0).items.isEmpty()) {
				try {
					for (int i = 0; i < items.size(); i++) {
						System.out.println(
								(i + 1) + ": " + this.items.get(i).getName() + "×" + this.items.get(i).getNum());
					}
					System.out.println("使うアイテムの番号を入力してください。使わない場合は「0」を入力>");
					int select = new java.util.Scanner(System.in).nextInt();
					if (select == 0)
						break;
					select -= 1;
					if (select > items.size()) {
						System.out.println("正しい数字を入力してください");
						continue;
					}
					if (playerList.get(0).items.get(select) instanceof Portion) {
						Portion p = (Portion) playerList.get(0).items.get(select);
						playerList.get(0).usePortion(playerList, p);
					} else
						System.out.println("正しい数字を入力してください");
					System.out.println("");
				} catch (InputMismatchException e) {
					System.out.println("正しい数字を入力してください");
				}
			} else {
				System.out.print("アイテムがありません\n\n");
				break;
			}
		} while (true);
	}
	
	//アイテム使用
	public void useItem() {
		if (this.items.size() > 0) {
			for (int i = 0; i < items.size(); i++) {
				System.out.print(items.get(i) + " ");
			}
		} else if (items.size() <= 0)
			System.out.println("アイテムがありません");
	}
					
	//ポーション利用メソッド
	//※※マジックナンバー利用
	public void usePortion(ArrayList<Player> playerList, Portion portion) {
		boolean b = false;
		do {
			System.out.print("誰にポーションを使いますか。使用しない場合は「0」を入力>\n");
			for (int i = 0; i < playerList.size(); i++) {
				System.out.println((i + 1) + ": " + playerList.get(i).getName());
			}
			int select = new java.util.Scanner(System.in).nextInt();
			if (select == 0)
				break;
			select -= 1;
			if (select > playerList.size()) {
				System.out.println("正しい数字を入力してください");
				continue;
			}
			if (playerList.get(select).getHp() < playerList.get(select).getFullhp()) {
				b = this.recoveryPortion(playerList.get(select), playerList, portion);
				//				System.out.printf("%sのHPが回復しました。HP: %d\n\n", playerList.get(select).getName(),
				//						playerList.get(select).getHp());
			} else if (playerList.get(select).getFullhp() >= playerList.get(select).getHp()) {
				System.out.println("HPはフルです");
			}

		} while (!b);
	}

	//ポーション処理
	public boolean recoveryPortion(Player player,ArrayList<Player> playerList,Portion portion) {
		int r = player.getHp() + portion.getRecover();
		boolean b = false;
		if (r < this.getFullhp()) {
			player.setHp(player.getHp() + portion.getRecover());
			System.out.println("HPが" + portion.getRecover() + "回復しました");
		} else {
			this.setHp(this.getFullhp());
			System.out.println("HPがフルになりました\n");
		}
		int num = playerList.get(0).items.indexOf(portion);
		playerList.get(0).items.get(num).setNum(playerList.get(0).items.get(num).getNum()-1);
		if(playerList.get(0).items.get(num).getNum() == 0) {playerList.get(0).items.remove(num);
		b = true;
	}
		return b;
	}
	
	//フィールド移動メソッド
	public boolean moveField(ArrayList<Field> fields, Battle battle, Player player, ArrayList<Player> playerList,
			CsvReader csvreader, CreateEnemy createEnemy, ArrayList<Enemy> enemyList, ArrayList<String> levelUpList,int lineNum)
			throws IOException {
		boolean isDead = false;
		boolean b = false;
		Scanner scan = new Scanner(System.in);

		do {
			try {
				System.out.println("移動しますか 0: 移動する 1:移動しない");
				int select = new java.util.Scanner(System.in).nextInt();
				if (select == 0) {
					System.out.println("どこに移動しますか>");
					for (int i = 0; i < fields.size(); i++) {
						System.out.println(i + " : " + fields.get(i).getName());
					}
					int select2 = scan.nextInt();
					//隣町開発中のための処置
					//			if(fields.get(select2) instanceof Town){
					//			System.out.println(fields.get(select2).getName() + "へ入りました\n");
					switch(select2){
						case 0:
						System.out.println(fields.get(select2).getName() + "へ入りました\n");
						break;
						
						case 1:
						System.out.println("開発中です\n王都ギロッポンヌに戻ります\n");
						select2 = 0;
						break;
						
						case 2:
						System.out.println(fields.get(select2).getName() + "へ移動します\n");
					if (fields.get(select2) instanceof MidField) {
						MidField mf = (MidField) fields.get(select2);
						isDead = moveField(mf, battle, player, playerList, csvreader, createEnemy, enemyList,levelUpList,lineNum);
					}
						break;
					default:
					}
					this.field = fields.get(select2);
					b = true;
					break;
				} else System.out.println("正しい数字を入力してください");
			} catch (InputMismatchException e) {
				System.out.println("正しい数字を入力してください");
			}
		} while (!b);
		return isDead;
	}
	
	//フィールド移動中のメソッド
	public boolean moveField(MidField mf,Battle battle,Player player, ArrayList<Player> playerList, CsvReader csvReader,CreateEnemy createEnemy, ArrayList<Enemy> enemyList, ArrayList<String> levelUpList,int lineNum)throws IOException{
		int dis = mf.getDistance();
		int r = 0;
		boolean isDead = false;
		
		loop:
		do {
			System.out.println("あと" + dis + "で次の街に着きます。サイコロを振ってください>");
			System.out.println("");			
			r = new java.util.Random().nextInt(6) + 1;
			if (r <= 2) {
				ArrayList<String> str2List = csvReader.csvReader("enemyList.csv");
				int[] nums = new int[4];
				
				nums[2] = new java.util.Random().nextInt(1) + 1 ;
				nums[3] = new java.util.Random().nextInt(1) + 1;
				nums[0] = 3; 
				nums[1] = 4;
				createEnemy.randomCreateEnemy(nums[0],nums[2],enemyList, str2List);
				createEnemy.randomCreateEnemy(nums[1],nums[3],enemyList, str2List);
				battle.battle(playerList, enemyList, levelUpList,lineNum);
				playerList.get(0).setHp(battle.getPlayerDmg());
				//				battle.levelUp(playerList.get(0),playerList,levelUpList);
				if (playerList.get(0).getHp() <= 0) {
					isDead = true;
				} 
				break loop;
			}
			dis -= r;
			if (dis == 0) {
				System.out.println("次の街に着きました\n開発中");
				break;
			}
		} while (dis > 0);
		
		return isDead;
	}
}
