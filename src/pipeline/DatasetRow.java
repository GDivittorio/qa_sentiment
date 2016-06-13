package pipeline;

import java.util.List;

public class DatasetRow {

	private String label;
	private double[] dimensions;
	private double positiveSim, negativeSim, subjectiveSim, objectiveSim;
	private List<Integer> occurrences;
	private int posEmoticon, negEmoticon, laughCount, qeStringsCount, uppercaseWord, mentionCount, englishNegCount;
	private int posTokens, negTokens, subjTokens, lastPos, lastNeg, lastEmoticon;
	private int posSum, negSum, subjSum, maxPos, maxNeg;

	public DatasetRow(String label, double[] dimensions, double subjectiveSim, double objectiveSim, double positiveSim,
			double negativeSim, List<Integer> occurrences, int uppercWordCount, int userMentionCount,int posEmoticon, int negEmoticon,
			int laughCount, int qeStringsCount, int enNegationCount, int posTokens, int negTokens, int subjTokens, int lastPos, int lastNeg,
			int lastEmoticon, int posSum, int negSum, int subjSum, int maxPos, int maxNeg) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.subjectiveSim = subjectiveSim;
		this.objectiveSim = objectiveSim;
		this.positiveSim = positiveSim;
		this.negativeSim = negativeSim;
		this.occurrences = occurrences;
		this.uppercaseWord = uppercWordCount;
		this.mentionCount = userMentionCount;
		this.posEmoticon = posEmoticon;
		this.negEmoticon = negEmoticon;
		this.laughCount = laughCount;
		this.qeStringsCount = qeStringsCount;
		this.englishNegCount = enNegationCount;
		this.posTokens = posTokens;
		this.negTokens = negTokens;
		this.subjTokens = subjTokens;
		this.lastPos = lastPos;
		this.lastNeg = lastNeg;
		this.lastEmoticon = lastEmoticon;
		this.posSum = posSum;
		this.negSum = negSum;
		this.subjSum = subjSum;
		this.maxPos = maxPos;
		this.maxNeg = maxNeg;
	}

	/*
	 * Only for semantic features
	 */
	public DatasetRow(String label, double[] dimensions, double subjectiveSim, double objectiveSim, double positiveSim,
			double negativeSim) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.subjectiveSim = subjectiveSim;
		this.objectiveSim = objectiveSim;
		this.positiveSim = positiveSim;
		this.negativeSim = negativeSim;
	}

	/*
	 * Only for keyword features
	 */
	public DatasetRow(String label, double[] dimensions, List<Integer> occurrences, int ucWordCount, int userMCount,
			int emoPosCount, int emoNegCount, int laughCount, int qeStringCount, int enNegCount) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.occurrences = occurrences;
		this.uppercaseWord = ucWordCount;
		this.mentionCount = userMCount;
		this.posEmoticon = emoPosCount;
		this.negEmoticon = emoNegCount;
		this.laughCount = laughCount;
		this.qeStringsCount = qeStringCount;
		this.englishNegCount = enNegCount;
	}

	/*
	 * Only for lexicon features
	 */
	public DatasetRow(String label, double[] dimensions, int positiveTknCount, int negativeTknCount, int subjTknCount,
			int lastPosScore, int lastNegScore, int lastEmoScore, int sumPosScore, int sumNegScore, int sumSubjScore,
			int maxPosScore, int maxNegScore) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.posTokens = positiveTknCount;
		this.negTokens = negativeTknCount;
		this.subjTokens = subjTknCount;
		this.lastPos = lastPosScore;
		this.lastNeg = lastNegScore;
		this.lastEmoticon = lastEmoScore;
		this.posSum = sumPosScore;
		this.negSum = sumNegScore;
		this.subjSum = sumSubjScore;
		this.maxPos = maxPosScore;
		this.maxNeg = maxNegScore;

	}

	public double[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(double[] dimensions) {
		this.dimensions = dimensions;
	}

	public double getSubjectiveSim() {
		return subjectiveSim;
	}

	public void setSubjectiveSim(double subjectiveSim) {
		this.subjectiveSim = subjectiveSim;
	}

	public double getObjectiveSim() {
		return objectiveSim;
	}

	public void setObjectiveSim(double objectiveSim) {
		this.objectiveSim = objectiveSim;
	}

	public double getPositiveSim() {
		return positiveSim;
	}

	public void setPositiveSim(double positiveSim) {
		this.positiveSim = positiveSim;
	}

	public double getNegativeSim() {
		return negativeSim;
	}

	public void setNegativeSim(double negativeSim) {
		this.negativeSim = negativeSim;
	}

	public int getPosEmoticon() {
		return posEmoticon;
	}

	public void setPosEmoticon(int posEmoticon) {
		this.posEmoticon = posEmoticon;
	}

	public int getNegEmoticon() {
		return negEmoticon;
	}

	public void setNegEmoticon(int negEmoticon) {
		this.negEmoticon = negEmoticon;
	}

	public int getLaughCount() {
		return laughCount;
	}

	public void setLaughCount(int laughCount) {
		this.laughCount = laughCount;
	}

	public int getQeStringsCount() {
		return qeStringsCount;
	}

	public void setQeStringsCount(int qeStrings) {
		this.qeStringsCount = qeStrings;
	}

	public int getPosTokens() {
		return posTokens;
	}

	public void setPosTokens(int posTokens) {
		this.posTokens = posTokens;
	}

	public int getNegTokens() {
		return negTokens;
	}

	public void setNegTokens(int negTokens) {
		this.negTokens = negTokens;
	}

	public int getSubjTokens() {
		return subjTokens;
	}

	public void setSubjTokens(int subjTokens) {
		this.subjTokens = subjTokens;
	}

	public int getLastPos() {
		return lastPos;
	}

	public void setLastPos(int lastPos) {
		this.lastPos = lastPos;
	}

	public int getLastNeg() {
		return lastNeg;
	}

	public void setLastNeg(int lastNeg) {
		this.lastNeg = lastNeg;
	}

	public int getLastEmoticon() {
		return lastEmoticon;
	}

	public void setLastEmoticon(int lastEmoticon) {
		this.lastEmoticon = lastEmoticon;
	}

	public int getPosSum() {
		return posSum;
	}

	public void setPosSum(int posSum) {
		this.posSum = posSum;
	}

	public int getNegSum() {
		return negSum;
	}

	public void setNegSum(int negSum) {
		this.negSum = negSum;
	}

	public int getSubjSum() {
		return subjSum;
	}

	public void setSubjSum(int subjSum) {
		this.subjSum = subjSum;
	}

	public int getMaxPos() {
		return maxPos;
	}

	public void setMaxPos(int maxPos) {
		this.maxPos = maxPos;
	}

	public int getMaxNeg() {
		return maxNeg;
	}

	public void setMaxNeg(int maxNeg) {
		this.maxNeg = maxNeg;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Integer> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(List<Integer> occurrences) {
		this.occurrences = occurrences;
	}

	public int getUppercaseWord() {
		return uppercaseWord;
	}

	public void setUppercaseWord(int uppercaseWord) {
		this.uppercaseWord = uppercaseWord;
	}

	public int getMentionCount() {
		return mentionCount;
	}

	public void setMentionCount(int mentionCount) {
		this.mentionCount = mentionCount;
	}

	public int getEnglishNegCount() {
		return englishNegCount;
	}

	public void setEnglishNegCount(int englishNegCount) {
		this.englishNegCount = englishNegCount;
	}
}
