package cosineSimilarity;

import feature.Vector;

/*
 * For calculating cosine similarity between two documents
 */
public class CosineSimilarity {
	
	/**
	 * Method to calculate cosine similarity between two documents.
	 * 
	 * @param docVector1
	 *            : document vector 1 (a)
	 * @param docVector2
	 *            : document vector 2 (b)
	 * @return
	 */
	public double cosineSimilarity(Vector docVector1, Vector docVector2) {
		double dotProduct = 0.0;
		double magnitude1 = 0.0;
		double magnitude2 = 0.0;
		double cosineSimilarity = 0.0;

		for (int i = 0; i < docVector1.length(); i++) // docVector1 and docVector2
													// must be of same length
		{
			dotProduct += docVector1.getElement(i) * docVector2.getElement(i); // a.b
			magnitude1 += Math.pow(docVector1.getElement(i), 2); // (a^2)
			magnitude2 += Math.pow(docVector2.getElement(i), 2); // (b^2)
		}

		magnitude1 = Math.sqrt(magnitude1);// sqrt(a^2)
		magnitude2 = Math.sqrt(magnitude2);// sqrt(b^2)

		if (magnitude1 != 0.0 | magnitude2 != 0.0) {
			cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
		} else {
			return 0.0;
		}
		return cosineSimilarity;
	}

}