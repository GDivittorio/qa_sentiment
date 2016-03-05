package arffProcessing;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

public class CSV2Arff {
	/**
	 * takes 2 arguments: - CSV input file - ARFF output file
	 * @throws IOException 
	 */
	public void writeArff(String inputCsv, String outputArff) throws IOException {

		// load CSV
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(inputCsv));
		Instances data = loader.getDataSet();

		// save ARFF
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File(outputArff));
		saver.writeBatch();
	}

}