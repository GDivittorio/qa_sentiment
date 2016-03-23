package feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VectorOperation {
	
	private Vector v;
	
	/**
	 * Sum of two vectors
	 * @param a first vector
	 * @param b second vector
	 * @return v vector sum of b and a
	 * @throws Exception
	 */
	public Vector sum(Vector a, Vector b) throws Exception {
		if (a.length() != b.length()) {
			throw new Exception(
					"Errore. La lunghezza dei 2 vettori non è la stessa.");
		}
		v = new Vector();
		double sum = 0;
		for (int i = 0; i < a.length(); i++) {
			sum = a.getElement(i) + b.getElement(i);
			v.add(sum);
		}
		return v;
	}

	/**
	 * Fr
	 * Data una stringa ne costruisce la rappresentazione vettoriale sommando
	 * tutti i vettori delle parole che compongono la stringa
	 * @throws Exception
	 */
	/**
	 * Build the vector representation of a string, summing every vector of words in the string
	 * @param s input string
	 * @param m DSM
	 * @return vector representatio of a string
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Vector stringRepresentation(String s, Map m) throws Exception {
		String[] ss = s.split(" ");
		v = new Vector();
		List<Vector> vectorList = new ArrayList<Vector>();
		for (String word : ss) {
			String vect = (String) m.get(word);
			if(vect != null){
			v = new Vector(vect);
			vectorList.add(v);
			}
		}
		return sum(vectorList);
	}

	/**
	 * Make the sum of a list of vectors
	 * @param vectorList
	 * @return sum of vectors
	 * @throws Exception
	 */
	public Vector sum(List<Vector> vectorList) throws Exception {
		if(vectorList.size()>1){
		v = sum(vectorList.get(0), vectorList.get(1));
		Vector temp = new Vector();
		for (int i = 2; i < vectorList.size(); i++) {
			temp = sum(v, vectorList.get(i));
			v = temp;
		}
		}else if (vectorList.size()==1){
			v = vectorList.get(0);
		}
		else v = new Vector();
		return v;
	}
}
