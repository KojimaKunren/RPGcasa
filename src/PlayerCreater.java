import java.io.IOException;
import java.util.ArrayList;

public class PlayerCreater extends CreateAdd {
	public void createPlayer(int num, ArrayList<Player> players, ArrayList<String> getList)throws IOException {
		int[] nums = new int[9];
		String str = getList.get(num);
		String str2 = getList.get(num + 1);
		for(int j = 2; j < 11; j++) {
			nums[j - 2] = Integer.parseInt(getList.get(num + j));
		}
			Player player = new Player(str, str2 ,nums[0],nums[1],nums[2],nums[3],nums[4],nums[5],nums[6],nums[7],nums[8]);
			players.add(player);
	}
}
