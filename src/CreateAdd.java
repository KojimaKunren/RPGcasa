import java.io.IOException;
import java.util.ArrayList;

public class CreateAdd {
	public void createAdd(int num, int num2, ArrayList<Enemy> enemies, ArrayList<String> getList)throws IOException {
		for(int i =  0; i <= num2;) {
			String str = getList.get(num);
			for(int j = 1; j < 7; j++) {
				int[] nums = new int[7];
				nums[j -1] = Integer.parseInt(getList.get(num + j));
			Enemy enemy = new Enemy(str,nums[0],nums[1],nums[2],nums[3],nums[4],nums[5],nums[6]);
			enemies.add(j-1,enemy);
			}
		}
	}
}
