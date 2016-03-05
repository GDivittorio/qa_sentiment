package pipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import cosineSimilarity.CosineSimilarity;
import feature.Vector;
import feature.VectorOperation;
import feature.semantic.PolarityVector;

/*
 * Class for generating a .csv file with only Semantic features
 */
public class SemanticFeatures {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	private PolarityVector pv = new PolarityVector();

	private CosineSimilarity cs = new CosineSimilarity();
	private VectorOperation vo = new VectorOperation();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void writeCsvFile(String output, File input,
			Map<String, String> dictionary) throws Exception {
		String row = "";
		String line = "";
		Vector v = new Vector();

		Vector positive = pv.getPositiveVector(dictionary);
		Vector negative = pv.getNegativeVector(dictionary);

		BufferedReader br = new BufferedReader(new FileReader(input));
		List<DatasetRow> list = new ArrayList<DatasetRow>();
		DatasetRow dr;
		double[] dimensions;
		while ((row = br.readLine()) != null) {
			line = row.split(";")[0];
			dimensions = new double[200];
			v = vo.stringRepresentation(line.toLowerCase(), dictionary);

			for (int i = 0; i < v.length(); i++) {
				dimensions[i] = v.getElement(i);
			}

			dr = new DatasetRow(row.split(";")[1], dimensions,
					cs.cosineSimilarity(v, positive), cs.cosineSimilarity(v,
							negative));

			list.add(dr);
		}

		List<String> header = new ArrayList<String>();

		for (int i = 1; i <= 200; i++) {
			header.add("d" + i);
		}
		header.add("Positive_similarity");
		header.add("Negative_similarity");
		header.add("label");
		FileWriter fileWriter = null;

		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(
				NEW_LINE_SEPARATOR).withDelimiter(',');
		try {

			// initialize FileWriter object
			fileWriter = new FileWriter(output);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// Create CSV file header
			csvFilePrinter.printRecord(header);
			int ii = 0;
			for (DatasetRow d : list) {
				List l = new ArrayList();
				System.out.println("Printing line:" + ii);
				for (double b : d.getDimensions()) {
					l.add(b);
				}

				l.add(d.getPositiveSim());
				l.add(d.getNegativeSim());

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
				System.out
						.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}
}
