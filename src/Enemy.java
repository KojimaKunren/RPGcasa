public class Enemy implements Human  {
	private String name;
	private int hp;
	private int mp;
	private int atk;
	private int def;
	private int exp;
	private int level;
	private int money;

	public Enemy(String name, int hp, int mp, int atk, int def,int exp, int level, int money) {
		setName(name);
		setHp(hp);
		setMp(mp);
		setAtk(atk);
		setDef(def);
		setExp(exp);
		setLevel(level);
		setMoney(money);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getMp() {
		return this.mp;
	}
	
	public void setMp(int mp) {
		this.mp = mp;
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

	public static int attack(Enemy enemy, Player player) {
		int dmg = 0;
		if(player.armor == null && player.sield == null) {
		dmg = enemy.getAtk() - player.getDef();
		}else if(player.armor == null && player.sield != null){
			dmg = enemy.getAtk() - (player.armor.getDef() + player.getDef());
		}else if(player.sield == null && player.armor != null){
			dmg = enemy.getAtk() - (player.sield.getDef() + player.getDef());
		}else if(player.sield != null && player.armor != null){
			dmg = enemy.getAtk() - ((player.sield.getDef() + player.armor.getDef()) + player.getDef());
		}

		if(dmg > 0) {
		player.setHp(player.getHp() - dmg);
		}
		return player.getHp();
	}
	
	public String showEnemyList() {
		return "名前: "+ this.getName() + " HP: " + this.getHp() + " MP: " + this.getMp() + " 攻撃力; " + this.getAtk() + " 防御力; " + this.getDef();
	}
	
	public boolean isDead() {
		boolean isEnd = true;
		System.out.println("\n戦闘に勝利した");
	return isEnd;
	}
	
}
