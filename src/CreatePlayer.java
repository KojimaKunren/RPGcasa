import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CreatePlayer {
	public void createPlayers(ArrayList<String> players, ArrayList<Player> playerList)throws IOException {
		String pathPlayer = "playerList.csv";
		
		FileInputStream fis2 = new FileInputStream(pathPlayer);
		InputStreamReader isr2 = new InputStreamReader(fis2, "UTF-8");
		BufferedReader br2 = new BufferedReader(isr2);
		
		String line2 = null;
		while((line2 = br2.readLine()) != null) {
			players.add(line2);
		}
		
		//問題点：players.size()が入らない（OutOfBounds）
		int[] playerInt = new int[12];
		
		for(int i = 0; i < players.size(); i++) {
			String[] str = players.get(i).split(",");
			 for(int j = 2; j < str.length; j++) {
				 int a =Integer.parseInt(str[j]);
				 playerInt[j-2] = a;
			 }
			playerList.add(i, new Player(str[0], str[1], playerInt[0], playerInt[1], playerInt[2], playerInt[3], playerInt[4], playerInt[5], playerInt[6],
					playerInt[7], playerInt[8]));
			}
		}
}
