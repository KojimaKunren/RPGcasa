import java.io.IOException;
import java.util.ArrayList;

public class CreateEnemy {
	public void createEnemy(int num, int num2, ArrayList<Enemy> enemies, ArrayList<String> getList)throws IOException {
		int[] nums = new int[7];
		for(int i = 0; i < num2; i++) {
		String str = getList.get(num) + (i + 1);
		for(int j = 1; j < 7; j++) {
			nums[j -1] = Integer.parseInt(getList.get(num + j));
		}
			Enemy enemy = new Enemy(str,nums[0],nums[1],nums[2],nums[3],nums[4],nums[5],nums[6]);
			enemies.add(enemy);
			System.out.println(enemies.get(i).getName() + "があらわれた");
		}
	}
}

