import java.util.ArrayList;

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

	public int talk(String name) {
		System.out.println(name + "に話しかけますか");
		System.out.print("0 : 話しかける\t1 : やめておく");
		int select = new java.util.Scanner(System.in).nextInt();
		return select;

	}
	
	public static String showStatus(Player player) {
		return "プレイヤー名: " + player.getName() + " 職業: " + player.getRole() + "\nHP: " + player.getHp() + "/" + player.getFullhp()
		+" MP: " + player.getMp() + "/" + player.getFullmp()	+ "\n攻撃力: "	+ player.getAtk() + " 防御力: " + player.getDef() + "\nレベル: "
		+ player.getLevel() + " 経験値: " + player.getExp() + "\n"; 
	}
	
	public static String showItem(Player player) {
		return "財布: " + player.wallet + "\n";
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
					
	public void usePortion(Player player, ArrayList<Player> playerList, Portion portion) {
		if (player.items.get(0).getNum() != 0) {
			System.out.print("誰にポーションを使いますか>");
			int select = new java.util.Scanner(System.in).nextInt();
			this.usePortion(playerList.get(select), portion);
			player.items.get(0).setNum(player.items.get(0).getNum() - 1);
			System.out.printf("%sのHPが回復しました。HP: %d\n\n",playerList.get(select).getName(), playerList.get(select).getHp());
		} else if (player.items.get(0).getNum() <= 0) {
			System.out.println("ポーションがありません");
		}
	}

	public void usePortion(Player player, Portion portion) {
		player.setHp(player.getHp() + portion.getRecover());
	}
	
	public boolean isDead() {
		boolean isEnd = true;
		System.out.println("\nGAME OVER\n物語はまだはじまらない…");
	return isEnd;
	}
	
	public void moveField(ArrayList<Field> field) {
		System.out.println("どこに移動しますか "):
			
	}
}
