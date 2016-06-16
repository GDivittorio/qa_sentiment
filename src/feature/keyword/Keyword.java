package feature.keyword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import edu.stanford.nlp.util.StringUtils;
import feature.semantic.PolarityVector;

/*
 * To calculate keyword features
 */
public class Keyword {

	private int count;
	private PolarityVector pv = new PolarityVector();
	private List<String> negationWords = getNegationWordList();

	/*
	 * Counts the occurrences of every Ngrams of the input string. Return a map of
	 * Ngrams and occurrences
	 */
	public Map<String, Integer> getNgramsOccurrences(String input, int n) {
		int occurrences;
		Map<String, Integer> mapOccurences = new HashMap<String, Integer>();
		Collection<String> ss = StringUtils.getNgrams(new ArrayList<String>(Arrays.asList(input.split("\\s+"))), n, n);;
		for (String string : ss) {
			occurrences = 0;
			if (mapOccurences.keySet().contains(string)) {
				occurrences = mapOccurences.get(string);
				occurrences++;
				mapOccurences.put(string, occurrences);
			} else {
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
	 * 
	 * @author Gianmarco Divittorio TODO: test
	 */
	private int wordsCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			count++;
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
	 * Counts the total number of uppercase words in a string. Note: special
	 * characters are considered as uppercase characters too for the purpose of
	 * the method.
	 * 
	 * @author Gianmarco Divittorio TODO: test
	 */
	public int uppercaseWordsCount(String input) {
		count = 0;
		String[] ss = input.split("\\s");
		for (String s : ss) {
			if (s.matches("^[A-Z]+$")) {
				count++;
			}
		}
		return count;
	}

	/*
	 * Counts the ratio between uppercase words and the total number of words in
	 * a string.
	 * 
	 * @author Gianmarco Divittorio
	 */
	public double uppercaseWordsRatio(String input) {
		double upperWords = uppercaseWordsCount(input);
		double totalWords = wordsCount(input);

		return upperWords / totalWords;
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
	 * Counts the total number of user mentions in a string, where a user
	 * mention is expressed in the form: "@User" TODO: test
	 * 
	 * @author Gianmarco Divittorio
	 */
	public int mentionCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			if (s.startsWith("@")) {
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
	 * Counts the occurrences of expression of laughters in a string
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
	 * Verify is a string is an expression of laughters, like
	 * "ahah","ihihih","lol",etc.
	 */
	private static boolean isValidLaughSequence(String s) {
		String l1 = "(?i)((ah|ha){2}(a|h)*)|((eh|he){2}(e|h)*)|((ih|hi){2}(i|h)*)";
		if (s.matches(l1) || s.equalsIgnoreCase("lmao") || s.equalsIgnoreCase("lol") || s.equalsIgnoreCase("alol"))
			return true;
		else
			return false;
	}

	/*
	 * Counts the total occurrences of string composed only from question marks
	 * and/or exclamation marks.
	 */
	public int qeStringCount(String input) {
		count = 0;
		String[] ss = input.split("\\s+");
		for (String s : ss) {
			if (isValidQEString(s))
				count++;
		}
		return count;
	}

	/*
	 * Verify if a sting is a valid sequence of question marks and/or
	 * exclamation marks (min 2).
	 */
	private boolean isValidQEString(String s) {
		String regex = "[!?]{2}[!?]*";
		return s.matches(regex);
	}

	/*
	 * Get the list of english negation words from /res/NegatingWordList
	 */
	private List<String> getNegationWordList() {
		List<String> n = new ArrayList<String>();
		BufferedReader br;
		try {
			InputStream is = getClass().getResourceAsStream("/NegatingWordList");
			br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = br.readLine()) != null) {
				n.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}

	/*
	 * Counts the number of english negation words in a string
	 */
	public int enNegationsCount(String s) {
		int n = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			// case sensitive
			if (negationWords.contains(string))
				n++;
		}
		return n;
	}

}
