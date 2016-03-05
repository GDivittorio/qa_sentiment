package feature;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;

import pipeline.AllFeatures;
import pipeline.KeywordFeatures;
import pipeline.LexiconFeatures;
import pipeline.SemanticFeatures;
import arffProcessing.CSV2Arff;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.keyword.Utility;

public class Main {

	public static void main(String[] args){
		ObjectMapper mapper = new ObjectMapper();
		AllFeatures af;
		Utility u;
		SemanticFeatures sf;
		KeywordFeatures kf;
		LexiconFeatures lf;
		SortedMap<String, Integer> positionWord;
		CSV2Arff csv2arff = new CSV2Arff();

		args = new String[5];
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out
					.println("Insert S for Semantic features, K for Keyword features, L for Lexicon features, A for all features, Q to quit the program: ");
			args[4] = in.next();
			if (args[4].equalsIgnoreCase("q")) {
				flag = false;
			} else if (args[4].matches("(?i)[klas]")) {
				try {
					System.out.print("Insert path to input csv corpus: ");
					args[0] = in.next();
					File input = new File(args[0]);
					System.out.print("Insert path to DSM: ");
					args[1] = in.next();
					System.out.println("Loading DSM ...");
					@SuppressWarnings("unchecked")
					Map<String, String> map = mapper.readValue(new File(args[1]),Map.class);
					System.out.print("Insert path output csv: ");
					args[2] = in.next();
					System.out.print("Insert path output arff: ");
					args[3] = in.next();
					//Column of the input csv with text
					int textColumn = 0;
					switch (args[4].toUpperCase()) {
					case "A":
						af = new AllFeatures();
						u = new Utility();
						positionWord = u.getPositionWordMap(input, textColumn);
						af.writeCsvFile(args[2], input, map, positionWord);
						break;
					case "S":
						sf = new SemanticFeatures();
						sf.writeCsvFile(args[2], input, map);
						break;
					case "K":
						kf = new KeywordFeatures();
						u = new Utility();
						positionWord = u.getPositionWordMap(input, textColumn);
						kf.writeCsvFile(args[2], input, map, positionWord);
						break;
					case "L":
						lf = new LexiconFeatures();
						lf.writeCsvFile(args[2], input, map);
						break;
					}
					System.out.println("Writing arff ...");
					csv2arff.writeArff(args[2], args[3]);
					System.out.println("Arff file was created successfully.");
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}	
			} else {
				System.out.println("Invalid character");
			}
		}
		in.close();
	}
}
