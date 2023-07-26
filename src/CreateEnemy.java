import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CreateEnemy {
	public void createEnemies(ArrayList<String> enemies, ArrayList<Enemy> enemyList)throws IOException {
//	ArrayList<String> enemies = new ArrayList<String>();
	String pathEnemy = "enemyList.csv";
	
	FileInputStream fis2 = new FileInputStream(pathEnemy);
	InputStreamReader isr2 = new InputStreamReader(fis2, "UTF-8");
	BufferedReader br2 = new BufferedReader(isr2);
	
	String line2 = null;
	while((line2 = br2.readLine()) != null) {
		enemies.add(line2);
	}
	
//	ArrayList<Enemy> enemyList = new ArrayList<Enemy>(); 
	int[] enemyInt = new int[enemies.size()];
	
	for(int i = 0; i < enemies.size(); i++) {
		String[] str = enemies.get(i).split(",");
		 for(int j = 1; j < str.length; j++) {
			 int a =Integer.parseInt(str[j]);
			 enemyInt[j-1] = a;
		 }
		enemyList.add(i, new Enemy(str[0], enemyInt[0], enemyInt[1], enemyInt[2], enemyInt[3], enemyInt[4], enemyInt[5], enemyInt[6]));
		}
	}
	
}
