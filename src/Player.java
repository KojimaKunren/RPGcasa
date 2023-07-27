import java.util.ArrayList;
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
		setMoney(money);
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
		return enemy.getHp();
	}
	
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
		return enemy.getHp();
	}

	public int talk(String name) {
		System.out.println(name + "に話しかけますか");
		System.out.print("0 : 話しかける\t1 : やめておく");
		int select = new java.util.Scanner(System.in).nextInt();
		return select;

	}
	
	public void showStatus() {
		System.out.println( "プレイヤー名: " + this.getName() + " 職業: " + this.getRole() + "\nHP: " + this.getHp() + "/" + this.getFullhp()
		+" MP: " + this.getMp() + "/" + this.getFullmp()	+ "\n攻撃力: "	+ this.getAtk() + " 防御力: " + this.getDef() + "\nレベル: "
		+ this.getLevel() + " 経験値: " + this.getExp() + "\n" + "現在地: " + this.field.getName() + "\n"); 
	}
	
	public void showItem() {
		System.out.println("財布：" + this.getWallet());
		for(int i = 0; i < items.size(); i++) {
			System.out.println(this.items.get(i).getName() +  "×" + this.items.get(i).getNum());
		}
		try {
		System.out.println("Sword: " + this.sword.getName() + " Sield: " + this.sield.getName() + "\nArmor: " + this.armor.getName());
		}catch(Exception e) {;}
		System.out.println("");
		
	}
	
	public void payMoney(Player player, int money) {
		if (money <= this.getWallet()) {
			int n = this.getWallet() - money;
			this.setWallet(n);
		} else if (money <= 0) {
			System.out.println("お金が足りません");
		}
	}
		
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
	public void usePortion(Player player, ArrayList<Player> playerList, Portion portion) {
		if (playerList.get(0).items.get(0).getNum() != 0) {
			System.out.print("誰にポーションを使いますか>");
			for(int i = 0; i < playerList.size(); i ++){
				System.out.println(playerList.get(i).getName());
			}
			int select = new java.util.Scanner(System.in).nextInt();
			if (playerList.get(select).getHp() < playerList.get(select).getFullhp()) {
				this.recoveryPortion(playerList.get(select), portion);
				playerList.get(0).items.get(0).setNum(playerList.get(0).items.get(0).getNum() - 1);
				System.out.printf("%sのHPが回復しました。HP: %d\n\n", playerList.get(select).getName(),
						playerList.get(select).getHp());
			} else if (playerList.get(select).getFullhp() >= playerList.get(select).getHp()) {
				System.out.println("HPはフルです");
			}
		} else if (playerList.get(0).items.get(0).getNum() <= 0) {
			System.out.println("ポーションがありません");
		}
	}

	public void recoveryPortion(Player player, Portion portion) {
		int r =player.getHp() + portion.getRecover();
		if(r < this.getFullhp()) {
			player.setHp(player.getHp() + portion.getRecover());
			System.out.println("HPが" + portion.getRecover() + "回復しました");
		}else {
			this.setHp(this.getFullhp());
			System.out.println("HPがフルになりました\n");
		}
	}
	
	public boolean isDead() {
		boolean isEnd = true;
		System.out.println("\nGAME OVER\n物語はまだはじまらない…");
	return isEnd;
	}
	
	//フィールド移動メソッド
	public void moveField(ArrayList<Field> fields) {
		Scanner scan = new Scanner(System.in);
		System.out.println("移動しますか 0: 移動する 1:移動しない");
		int select = scan.nextInt();
		if(select ==0) {
			System.out.println("どこに移動しますか>");
			for(int i = 0; i < fields.size(); i++) {
				System.out.println(i+ " : " +fields.get(i).getName());
			}
			int select2 = scan.nextInt();
			//隣町開発中のための処置
//			if(fields.get(select2) instanceof Town){
//			System.out.println(fields.get(select2).getName() + "へ入りました\n");
//
			if(select2 == 0) {
				System.out.println(fields.get(select2).getName() + "へ入りました\n");
			}else if(select2 == 1) {
				System.out.println("開発中です\n王都ギロッポンヌに戻ります\n");
				select = 0;
			}else if(select2 == 2){
				System.out.println(fields.get(select2).getName() + "へ移動します\n");
			this.field = fields.get(select2);
			}
		}
	}
	
	//フィールド移動中のメソッド
	public int moveField(MidField mf){
		int dis = mf.getDistance();
		int r =0;
		do {
			System.out.println("あと" + dis + "で次の街に着きます");
		r = new java.util.Random().nextInt(6) + 1;
		dis -= r;
		if(dis == 0) {
			System.out.println("次の街に着きました\n開発中");
			break;
		}
		}while(dis > 0);
		return r;
	}
}
