package feature.keyword;
import java.util.HashMap;
import java.util.Map;

import feature.semantic.PolarityVector;

/*
 * To calculate keyword features
 */
public class Keyword {

	private int count;
	private PolarityVector pv = new PolarityVector();
	
	/*
	 * Counts the occurrences of every word of the input string.
	 * Return a map of unigrams and occurrences
	 */
	public Map<String, Integer> getWordOccurrences(String input){
		int occurrences;
		Map<String, Integer> mapOccurences = new HashMap<String, Integer>();
			String[] tokens = input.split("\\s+");
			for (String string : tokens) {
				occurrences = 0;
				if(mapOccurences.keySet().contains(string)){
					occurrences = mapOccurences.get(string);
					occurrences++;
					mapOccurences.put(string, occurrences);
				}else{
					mapOccurences.put(string, 1);
				}
			}
		return mapOccurences;
	}

	/*
	 * Counts the total number of characters in a string
	 */
	private int charactersCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
				count += s.length();
			}
		return count;
	}
	
	/*
	 * Counts the total number of words in a string.
	 * @author Gianmarco Divittorio
	 * TODO: test
	 */
	private int wordsCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
				count ++;
			}
		return count;
	}	

	/*
	 * Counts the total number of uppercase characters in a string
	 */
	private int uppercaseCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			for (char c : s.toCharArray()) {
				if (Character.isUpperCase(c))
					count++;
			}
		}
		return count;
	}
	
	/*
	 * Counts the total number of uppervase words in a string. Note: special characters
	 * are considered as uppercase characters too for the purpose of the method.
	 * @author Gianmarco Divittorio
	 * TODO: test
	 */
	private int uppercaseWordsCount(String input){
		count = 0;
		String[] ss = input.split("\\s");
		for (String s: ss){
			if(s.matches("^[A-Z]+$")){
		        count++;
		    }
		}
		return count;
	}
	
	/*
	 * Counts the ratio between uppercase words and the total number of words in a string.
	 * @author Gianmarco Divittorio
	 */
	public double uppercaseWordsRatio (String input){
		double upperWords = uppercaseWordsCount(input);
		double totalWords = wordsCount(input);
		
		return upperWords/totalWords;
	}

	/*
	 * Counts the ratio between the uppercase characters and the total number of
	 * characters in a string
	 */
	public double uppercaseRatio(String input) {
		double totalChar = charactersCount(input);
		double upperChar = uppercaseCount(input);
		return upperChar / totalChar;
	}
	
	/*
	 * Counts the total number of user mentions in a string, where a user mention is
	 * expressed in the form: "@User" 
	 * TODO: test
	 * @author Gianmarco Divittorio
	 */
	public int mentionCount (String input){
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			if (s.startsWith("@")){
				count++;
			}
		}
		return count;
	}

	/*
	 * Counts the total occurrences of positive emoticon in a string
	 */
	public int emoPosCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			for (String s1 : pv.posEmoticon) {
				if (s.equals(s1))
					count++;
			}
		}
		return count;
	}

	/*
	 * Counts the total occurrences of negative emoticon in a string
	 */
	public int emoNegCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			for (String s1 : pv.negEmoticon) {
				if (s.equals(s1))
					count++;
			}
		}
		return count;
	}

	/*
	 * Counts the occurrences of sequences of "ah" as slang expression of
	 * laughters in a string
	 */
	public int laughCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			if (isValidLaughSequence(s)) {
				count++;
			}
		}
		return count;
	}

	/*
	 * Verify if a string is a valid sequence of "ah" (min 2)
	 */
	private boolean isValidLaughSequence(String s) {
		String a = "(?i)ah(ah)+";
		return s.matches(a);
	}

	/*
	 * Counts the total occurences of question marks in a string
	 */
	public int qMarkCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			for (char c : s.toCharArray()) {
				if (c == '?')
					count++;
			}
		}
		return count;
	}

	/*
	 * Counts the total occurences of exclamation marks in a string
	 */
	public int eMarkCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			for (char c : s.toCharArray()) {
				if (c == '!')
					count++;
			}
		}
		return count;
	}
}
