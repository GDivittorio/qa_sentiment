package feature;

import java.io.File;
import java.util.Map;
import java.util.SortedMap;

import pipeline.AllFeatures;
import pipeline.KeywordFeatures;
import pipeline.LexiconFeatures;
import pipeline.SemanticFeatures;
import weka.core.Utils;
import arffProcessing.CSV2Arff;

import com.fasterxml.jackson.databind.ObjectMapper;

import feature.keyword.Utility;

/**
 *
 * Command-line parameters:
 * <ul>
 * <li>-F features - feature to evaluate {A, S, L, K} A for All, S for Semantic, L for Lexicon, K for Keyword</li>
 * <li>-i filename - the dataset to use (.csv)</li>
 * <li>-W filename - the word space to use (.json)</li>
 * <li>-oc filename - output csv </li>
 * <li>-oa filename - output arff</li>
 * </ul>
 *
 * Example command-line:
 * <pre>
 * java Main -F A -i input.csv -W wordSpace.json -oc output.csv -oa output.arff"
 * </pre>
 *
 */
public class Main {
 
    public static void main(String[] args) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
		AllFeatures af;
		Utility u;
		SemanticFeatures sf;
		KeywordFeatures kf;
		LexiconFeatures lf;
		SortedMap<String, Integer> positionWord;
		CSV2Arff csv2arff = new CSV2Arff();
		String[] param = new String[5];
			param[4] = Utils.getOption("F", args);
			if (param[4].matches("(?i)[klas]")) {
				try {
					//input csv param -i
					param[0] = Utils.getOption("i", args);
					File input = new File(param[0]);
					//input word space param -W
					param[1] = Utils.getOption("W", args);
					System.out.println("Loading DSM ...");
					@SuppressWarnings("unchecked")
					Map<String, String> map = mapper.readValue(new File(param[1]),Map.class);
					//output csv param -oc
					param[2] = Utils.getOption("oc", args);
					//output arff param -oa
					param[3] = Utils.getOption("oa", args);
					//Column of the input csv with text
					int textColumn = 0;
					switch (param[4].toUpperCase()) {
					case "A":
						af = new AllFeatures();
						u = new Utility();

						positionWord = u.getPositionWordMap(input, textColumn);
						af.writeCsvFile(param[2], input, map, positionWord);
						break;
					case "S":
						sf = new SemanticFeatures();
						sf.writeCsvFile(param[2], input, map);
						break;
					case "K":
						kf = new KeywordFeatures();
						u = new Utility();
						positionWord = u.getPositionWordMap(input, textColumn);
						kf.writeCsvFile(param[2], input, map, positionWord);
						break;
					case "L":
						lf = new LexiconFeatures();
						lf.writeCsvFile(param[2], input, map);
						break;
					}
					System.out.println("Writing arff ...");
					csv2arff.writeArff(param[2], param[3]);
					System.out.println("Arff file was created successfully.");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else {
				System.out.println("Invalid character");
			}
		}

}
