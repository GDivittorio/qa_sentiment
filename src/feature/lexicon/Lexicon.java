package feature.lexicon;

import java.util.Map;
import feature.semantic.PolarityVector;

/*
 * To calculate Lexicon features
 */
public class Lexicon {

	private SentiStrength ss = new SentiStrength();
	private Map<String, Integer> pos = ss.getPositiveTokens();
	private Map<String, Integer> neg = ss.getNegativeTokens();
	private PolarityVector pv = new PolarityVector();
	private int count;

	/*
	 * Counts the number of positive tokens in a string
	 */
	public int positiveTknCount(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (pos.containsKey(string)) {
				count++;
			}
		}
		count += positiveEmoticonCount(s);
		return count;
	}

	/*
	 * Counts the number of positive emoticon
	 */
	private int positiveEmoticonCount(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String s1 : ss) {
			for (String s2 : pv.posEmoticon) {
				if (s1.equals(s2)) {
					count++;
				}
			}
		}
		return count;
	}

	/*
	 * Counts the number of negative emoticon
	 */
	private int negativeEmoticonCount(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String s1 : ss) {
			for (String s2 : pv.negEmoticon) {
				if (s1.equals(s2)) {
					count++;
				}
			}
		}
		return count;
	}

	/*
	 * Counts the number of negative tokens in a string
	 */
	public int negativeTknCount(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (neg.containsKey(string)) {
				count++;
			}
		}
		count += negativeEmoticonCount(s);
		return count;
	}

	/*
	 * Counts the number of subjective token (either positive and negative
	 * tokens) in a string
	 */
	public int subjTknCount(String s) {
		return positiveTknCount(s) + negativeTknCount(s);
	}

	/*
	 * Return the score of the last positive token in a string
	 */
	public int lastPosScore(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (pos.containsKey(string)) {
				count = pos.get(string);
			} else if (pv.isPosEmoticon(string)) {
				count = 1;
			}
		}
		return count;
	}

	/*
	 * Return the score of the last negative token in a string
	 */
	public int lastNegScore(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (neg.containsKey(string)) {
				count = neg.get(string);
			} else if (pv.isNegEmoticon(string)) {
				count = -1;
			}
		}
		return count;
	}

	/*
	 * Return the score of the last emoticon in a string
	 */
	public int lastEmoScore(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (pv.isPosEmoticon(string))
				count = +1;
			else if (pv.isNegEmoticon(string))
				count = -1;
		}
		return count;
	}

	/*
	 * Return the sum of positive scores for the tokens in a string
	 */
	public int sumPosScore(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (pos.containsKey(string)) {
				count += pos.get(string);
			}
			if (pv.isPosEmoticon(string)) {
				count += 1;
			}
		}
		return count;
	}

	/*
	 * Return the sum of negative scores for the tokens in a string
	 */
	public int sumNegScore(String s) {
		count = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (neg.containsKey(string)) {
				count += neg.get(string);
			} else if (pv.isNegEmoticon(string)) {
				count += -1;
			}
		}
		return count;
	}

	/*
	 * Return the sum of positive and negative scores
	 */
	public int sumSubjScore(String s) {
		return sumPosScore(s) + sumNegScore(s);
	}

	/*
	 * Return the maximum positive score observed for tokens in a string
	 */
	public int maxPosScore(String s) {
		int max = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (pos.containsKey(string)) {
				if (pos.get(string) > max)
					max = pos.get(string);
			} else if (pv.isPosEmoticon(string)) {
				if (1 > max)
					max = 1;
			}
		}
		return max;
	}

	/*
	 * Return the maximum negative score observed for tokens in a string
	 */
	public int maxNegScore(String s) {
		int min = 0;
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (neg.containsKey(string)) {
				if (neg.get(string) < min)
					min = neg.get(string);
			} else if (pv.isNegEmoticon(string)) {
				if (-1 < min)
					min = -1;
			}
		}
		return min;
	}
}
