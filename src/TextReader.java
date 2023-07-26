import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TextReader {
	public void textReader(ArrayList<String> list, String txt)throws IOException {
	Scanner scan = new Scanner(System.in);
	
	String path = txt;

	FileInputStream fis = new FileInputStream(path);
	InputStreamReader isr = new InputStreamReader(fis, "utf-8");
	BufferedReader br = new BufferedReader(isr);

	String line = null;

	while ((line = br.readLine()) != null) {
		list.add(line);
		}
	}
}
