import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CsvReader {
	public ArrayList<String> csvReader(String csv)throws IOException{
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
	String path = csv;
	
	FileInputStream fis = new FileInputStream(path);
	InputStreamReader isr = new InputStreamReader(fis, "utf-8");
	BufferedReader br = new BufferedReader(isr);
	
	String line = null;
	
	while((line = br.readLine()) != null) {
		list.add(line);
	}
		
	for(int i = 0; i < list .size(); i++) {
		String[] str = list.get(i).split(",");
		list2.add(i,str[i]);
		}
	return list2;
	}
}
