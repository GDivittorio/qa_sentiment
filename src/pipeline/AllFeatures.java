package pipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import cosineSimilarity.CosineSimilarity;
import feature.Vector;
import feature.VectorOperation;
import feature.keyword.Keyword;
import feature.lexicon.Lexicon;
import feature.semantic.PolarityVector;

/*
 * Class for generating a .csv file with all features (Semantic, Lexicon, Keyword)
 */
public class AllFeatures {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	private PolarityVector pv = new PolarityVector();
	private Keyword kc = new Keyword();
	private CosineSimilarity cs = new CosineSimilarity();
	private VectorOperation vo = new VectorOperation();
	private Lexicon l = new Lexicon();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void writeCsvFile(String output, File input, Map<String, String> dictionary,
			SortedMap<String, Integer> positionWord) throws Exception {
		String row = "";
		String line = "";
		String lineLowc = "";
		Vector v = new Vector();

		Vector subjective = pv.getSubjectiveVector(dictionary);
		Vector objective = pv.getObjectiveVector(dictionary);
		Vector positive = pv.getPositiveVector(dictionary);
		Vector negative = pv.getNegativeVector(dictionary);

		Map<String, Integer> wordOccurrences;
		BufferedReader br = new BufferedReader(new FileReader(input));
		List<DatasetRow> list = new ArrayList<DatasetRow>();
		DatasetRow dr;
		double[] dimensions;
		List<Integer> occurrences;
		while ((row = br.readLine()) != null) {
			line = row.split(";")[0];
			occurrences = new ArrayList<Integer>();
			dimensions = new double[200];
			lineLowc = line.toLowerCase();
			v = vo.stringRepresentation(lineLowc, dictionary);

			wordOccurrences = kc.getNgramsOccurrences(line, 1);

			for (int i = 0; i < v.length(); i++) {
				dimensions[i] = v.getElement(i);
			}

			for (String s : positionWord.keySet()) {
				if (wordOccurrences.get(s) != null) {
					occurrences.add(wordOccurrences.get(s));
				} else {
					occurrences.add(0);
				}
			}

			dr = new DatasetRow(row.split(";")[1], dimensions, cs.cosineSimilarity(v, subjective),
					cs.cosineSimilarity(v, objective), cs.cosineSimilarity(v, positive),
					cs.cosineSimilarity(v, negative), occurrences, kc.uppercaseWordsCount(line), kc.mentionCount(line),
					kc.emoPosCount(line), kc.emoNegCount(line), kc.laughCount(line), kc.qeStringCount(line),
					kc.enNegationsCount(lineLowc), l.positiveTknCount(lineLowc), l.negativeTknCount(lineLowc),
					l.subjTknCount(lineLowc), l.lastPosScore(lineLowc), l.lastNegScore(lineLowc), l.lastEmoScore(line),
					l.sumPosScore(lineLowc), l.sumNegScore(lineLowc), l.sumSubjScore(lineLowc), l.maxPosScore(lineLowc),
					l.maxNegScore(lineLowc));

			list.add(dr);
		}

		List<String> header = new ArrayList<String>();

		for (int i = 1; i <= 200; i++) {
			header.add("d" + i);
		}
		header.add("Sim_subj");
		header.add("Sim_obj");
		header.add("Sim_pos");
		header.add("Sim_neg");
		for (int j = 0; j < positionWord.keySet().size(); j++) {
			header.add("t" + j);
		}
		String[] FILE_HEADER_END = { "UpperCase_Word", "User_Mention", "Positive_Emoticon", "Negative_Emoticon",
				"Laugh_Count", "QE_String_Count", "English_Negations", "Positive_Tokens", "Negative_Tokens",
				"Subjective_Tokens", "Last_Positive_Score", "Last_Negative_Score", "Last_Emoticon_Score",
				"Positive_Score_Sum", "Negative_Score_Sum", "Subjective_Score_Sum", "Max_Positive_Score",
				"Max_Negative_Score" };
		for (String s : FILE_HEADER_END) {
			header.add(s);
		}
		header.add("label");
		FileWriter fileWriter = null;

		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR).withDelimiter(',');
		try {

			// initialize FileWriter object
			fileWriter = new FileWriter(output);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// Create CSV file header
			csvFilePrinter.printRecord(header);
			int ii = 0;
			for (DatasetRow d : list) {
				if ((ii % 50) == 0) {
					System.out.println("Printing line:" + ii);
				}
				List l = new ArrayList();

				for (double b : d.getDimensions()) {
					l.add(b);
				}

				l.add(d.getSubjectiveSim());
				l.add(d.getObjectiveSim());
				l.add(d.getPositiveSim());
				l.add(d.getNegativeSim());

				for (int i : d.getOccurrences()) {
					l.add(i);
				}

				l.add(d.getUppercaseWord());
				l.add(d.getMentionCount());
				l.add(d.getPosEmoticon());
				l.add(d.getNegEmoticon());
				l.add(d.getLaughCount());
				l.add(d.getQeStringsCount());
				l.add(d.getEnglishNegCount());

				l.add(d.getPosTokens());
				l.add(d.getNegTokens());
				l.add(d.getSubjTokens());
				l.add(d.getLastPos());
				l.add(d.getLastNeg());
				l.add(d.getLastEmoticon());
				l.add(d.getPosSum());
				l.add(d.getNegSum());
				l.add(d.getSubjSum());
				l.add(d.getMaxPos());
				l.add(d.getMaxNeg());
				l.add(d.getLabel());
				csvFilePrinter.printRecord(l);

				ii++;
			}

			System.out.println("CSV file was created successfully.");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				br.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}
}
