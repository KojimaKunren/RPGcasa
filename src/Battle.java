import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
	private int playerDmg;
	private int enemyDmg;
	
	public void battle(Player player, ArrayList<Player> playerList, ArrayList<Enemy> enemies, Portion portion, ArrayList<String> levelUpList) throws IOException{
		Scanner scan = new java.util.Scanner(System.in);
		boolean isEnd = false;
		
		do {
			System.out.printf("1:攻撃 2;魔法 3:防御 4:アイテム 5:ステータス 6:退却>");
			int select = scan.nextInt();
			
			switch(select) {
			case 1:
				System.out.printf("1:通常攻撃 2:特殊攻撃>");
				int selectAttack = scan.nextInt();
					if(selectAttack == 1) {
						int r = new java.util.Random().nextInt(enemies.size());
						enemies.get(r).setHp(Player.attack(player, enemies.get(r)));
						this.enemyDmg = enemies.get(r).getHp();
						player.setHp(Enemy.attack(enemies.get(r), player));
						this.playerDmg = player.getHp();
					}else if(selectAttack == 2) {
						int r = new java.util.Random().nextInt(enemies.size());
						enemies.get(r).setHp(Player.attack(player, enemies.get(r)));
						this.enemyDmg = enemies.get(r).getHp();
						enemies.get(r).setHp(Player.attack(player, enemies.get(r)));
						this.enemyDmg = enemies.get(r).getHp();
						this.enemyDmg = enemies.get(r).getHp();
						player.setHp(Enemy.attack(enemies.get(r), player));
						this.playerDmg = player.getHp();
						}
					
					System.out.printf("%s HP%d MP%d\n",player.getName(), player.getHp(), player.getMp());
					for(int i = 0; i < enemies.size(); i++) {
					System.out.printf("%s HP%d\n\n",enemies.get(i).getName(), enemies.get(i).getHp());
					}
			break;
			
			case 2:
				if(player.getMp() > 0) {
					System.out.printf("1:通常魔法 2:特殊魔法>");
					int selectMagic = scan.nextInt();
					if(selectMagic == 1) {
						if(player.getMp() >= 5) {
						System.out.println("魔法攻撃");
						int r= new java.util.Random().nextInt(enemies.size());
						enemies.get(r).setHp(enemies.get(r).getHp() - 5);
						player.setMp(player.getMp() - 5);
						player.setHp(Enemy.attack(enemies.get(r), player));
						System.out.printf("%s HP%d MP%d\n",player.getName(), player.getHp(), player.getMp());
						System.out.printf("%s HP%d\n\n",enemies.get(r).getName(), enemies.get(r).getHp());
						this.enemyDmg = enemies.get(r).getHp();
						this.playerDmg = player.getHp();
						}else if(player.getMp() < 5) {System.out.println("MPが足りません");}					
				}else if(player.getMp() <= 0) {
					System.out.println("MPが足りません\n");
					break;}
				}

				System.out.printf("%s HP%d MP%d\n",player.getName(), player.getHp(), player.getMp());
				for(int i = 0; i < enemies.size(); i++) {
					System.out.printf("%s HP%d\n\n",enemies.get(i).getName(), enemies.get(i).getHp());
					}
				System.out.println("");
				break;
			
			case 3:
				System.out.println("防御している");
				int r = new java.util.Random().nextInt(enemies.size());
				int def = enemies.get(r).getAtk() - (player.getDef() * 2);
				if(def >= 0) {
					player.setHp(player.getHp() - def);
					this.playerDmg = player.getHp();
					System.out.printf("%s HP%d MP%d\n",player.getName(), player.getHp(), player.getMp());
					for(int i = 0; i < enemies.size(); i++) {
						System.out.printf("%s HP%d\n\n",enemies.get(i).getName(), enemies.get(i).getHp());
						}
				}else if(def < 0) ;
				this.playerDmg = 0;

				System.out.println("");
				break;
				
			case 4:
				playerList.get(0).showItem();
				player.usePortion(player, playerList, portion);
				break;
				
			case 5:
				playerList.get(0).showStatus();
				break;
			
			case 6:
				System.out.println("退却した");
				isEnd = true;
				break;
				
			default:
			}
			
			if(playerList.get(0).getHp() <= 0) {
				isEnd = playerList.get(0).isDead();
			}
			
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).getHp() <= 0) {
					int total = 0;
					total++;
					if (total == enemies.size()) {
						isEnd = enemies.get(i).isDead();
					}
				}
			}
				

		}while(!isEnd);
	}
	
	public int getPlayerDmg() {
		return this.playerDmg;
	}
	
	public int getEnemyDmg() {
		return this.enemyDmg;
	}

	//マジックナンバー利用要修正
	public void levelUp(Player player, ArrayList<Player> playerList, ArrayList<String> levelUpList) throws IOException {
		String path = "levelList.csv";

		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String line = null;
		while ((line = br.readLine()) != null) {
			levelUpList.add(line);
		}
		
		
		int[] levelInt = new int[levelUpList.size()];

			for (int i = 0; i < levelUpList.size(); i++) {
				 String[] str = levelUpList.get(i).split(",");
				 for(int j = 0; j < str.length; j++) {
				 levelInt[j]= Integer.parseInt(str[j]);
				 }
			}
System.out.println(levelInt[2]);
			for(int i = 0; i < levelInt.length; i++) {
				if(playerList.get(i).getLevel() == (levelInt[i] * 11) && playerList.get(i).getExp() == (levelInt[i + 1] *11)){
						playerList.get(i).setHp(levelInt[(i * 11) + 2]);
						playerList.get(i).setFullhp(levelInt[(i * 11) + 3]);
						playerList.get(i).setMp(levelInt[(i * 11) + 4]);
						playerList.get(i).setFullmp(levelInt[(i * 11) + 5]);
						playerList.get(i).setAtk(levelInt[(i * 11) + 6]);
						playerList.get(i).setDef(levelInt[(i * 11) + 7]);
						playerList.get(i).setExp(levelInt[(i * 11) + 8]);
						playerList.get(i).setLevel(levelInt[(i * 11) + 9]);
				}
			}
			
		
	}
}
