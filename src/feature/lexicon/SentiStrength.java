package feature.lexicon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SentiStrength{
	
	private Map<String, Integer> map;
		
	public Map<String, Integer> getPositiveTokens(){
		map = new HashMap<String, Integer>();
		File f = new File("./res/PositiveWordWithScore");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			String[] ss;
	 		while((line = br.readLine()) != null){
					ss = line.split(" ");
					map.put(ss[0], Integer.parseInt(ss[1]));
				}

				br.close();
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
		return map;
	}
	
	public Map<String, Integer> getNegativeTokens(){
		map = new HashMap<String, Integer>();
		File f = new File("./res/NegativeWordWithScore");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			String[] ss;
	 		while((line = br.readLine()) != null){
					ss = line.split(" ");
					map.put(ss[0], Integer.parseInt(ss[1]));
				}
				br.close();
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}		
		return map;
	}
}
