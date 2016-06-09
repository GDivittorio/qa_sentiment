package pipeline;

import java.util.List;

public class DatasetRow {

	private String label;
	private double[] dimensions;
	private double positiveSim, negativeSim;
	private List<Integer> occurrences;
	private double upperCRatio;
	private int posEmoticon, negEmoticon, laughCount, qeStringsCount;
	private int posTokens, negTokens, subjTokens, lastPos, lastNeg,
			lastEmoticon;
	private int posSum, negSum, subjSum, maxPos, maxNeg;

	public DatasetRow(String label, double[] dimensions, double positiveSim,
			double negativeSim, List<Integer> occurrences, double upperCRatio, int posEmoticon,
			int negEmoticon, int laughCount, int qeStringsCount,
			int posTokens, int negTokens, int subjTokens, int lastPos,
			int lastNeg, int lastEmoticon, int posSum, int negSum, int subjSum,
			int maxPos, int maxNeg) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.positiveSim = positiveSim;
		this.negativeSim = negativeSim;
		this.setOccurrences(occurrences);
		this.upperCRatio = upperCRatio;
		this.posEmoticon = posEmoticon;
		this.negEmoticon = negEmoticon;
		this.laughCount = laughCount;
		this.qeStringsCount = qeStringsCount;
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
	public DatasetRow(String label, double[] dimensions, double positiveSim, double negativeSim) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.positiveSim = positiveSim;
		this.negativeSim = negativeSim;
	}

	/*
	 * Only for keyword features
	 */
	public DatasetRow(String label, double[] dimensions, List<Integer> occurrences, double uppercaseRatio, int emoPosCount,
			int emoNegCount, int laughCount, int qeStringCount) {
		this.setLabel(label.replace(" ", ""));
		this.dimensions = dimensions;
		this.setOccurrences(occurrences);
		this.upperCRatio = uppercaseRatio;
		this.posEmoticon = emoPosCount;
		this.negEmoticon = emoNegCount;
		this.laughCount = laughCount;
		this.qeStringsCount = qeStringCount;
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

	public double getUpperCRatio() {
		return upperCRatio;
	}

	public void setUpperCRatio(double upperCRatio) {
		this.upperCRatio = upperCRatio;
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

	public void setQeStringsCount(int qeStrings) { this.qeStringsCount = qeStrings; }

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
}
