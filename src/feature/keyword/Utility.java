package feature.keyword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class Utility {

	/*
	 * Return a map of word in file with an incremental integer
	 */
	public SortedMap<String, Integer> getPositionWordMap(File f, int j)
			throws IOException {
		SortedMap<String, Integer> map = new TreeMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = "";
		String row = "";
		int i = 0;
		while ((row = br.readLine()) != null) {
			line = row.split(";")[j];
			line = line.toLowerCase();
			line = removeUrl(line);
			line = removeUserMention(line);
			if (line.length() > 0) {
				String[] tokens = line.split("\\s+");
				for (String string : tokens) {
					if (!(string.equals("?") || string.equals("!")))
						if (!map.containsValue(string)) {
							map.put(string, i);
							i++;
						}
				}
			}
		}
		br.close();
		return map;
	}

	/*
	 * Remove urls from string
	 */
	private String removeUrl(String s) {
		String res = "";
		String[] ss = s.split("\\s+");
		for (String string : ss) {
			if (!Patterns.WEB_URL.matcher(string).matches()) {
				res += string + " ";
			}
		}
		if (res.length() > 0)
			res = res.substring(0, res.length() - 1);
		return res;
	}

	/*
	 * Remove user mention from a string
	 */
	private String removeUserMention(String s) {
		String res = "";
		if (s.length() > 0) {
			String[] ss = s.split("\\s+");
			for (String string : ss) {
				if (!(string.charAt(0) == '@')) {
					res += string + " ";
				}
			}
			if (res.length() > 0)
				res = res.substring(0, res.length() - 1);
		}
		return res;

	}
}
