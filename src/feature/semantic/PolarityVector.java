package feature.semantic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import feature.Vector;
import feature.VectorOperation;

/*
 * To calculate Semantic features
 */
public class PolarityVector {

	public String[] posEmoticon = { "%-)", "(-:", "(:", "(^ ^)", "(^-^)",
			"(^.^)", "(^_^)", "(o:", "--^--@", "0:)", "8)", ":)", ":-)", ":-*",
			":-*", ":-D", ":-P", ":-}", ":3", ":9", ":D", ":P", ":P", ":X",
			":]", ":b)", ":o)", ":p", ":�", ";^)", "<3", "=)", "=]", ">:)",
			">:D", ">=D", " @}->--", "XD", "XD", "XP", "^_^", "x3?", "xD",
			"|D", "}:)" };

	public String[] negEmoticon = { "%-(", ")-:", "):", ")o:", "38*", "8-0",
			"8/", "8\\", "8c", ":#", ":'(", ":'-(", ":(", ":*(", ":,(", ":-&",
			":-(", ":-(o)", ":-/", ":-S", ":-\\", ":-|", ":/", ":E", ":F",
			":O", ":S", ":[", ":[", ":\\", ":_(", ":o(", ":s", ":|", ":�(",
			"</3-1", "<o<", "=(", "=[", ">/", ">:(", ">:L", ">:O", ">[", ">\\",
			">o>", "B(", "Bc", "D:", "X(", "X(", "X-(", "XO", "XP", "^o)",
			"xP", "|8C", "|8c" };

	private VectorOperation vo = new VectorOperation();
	private List<Vector> vectorList;
	private BufferedReader br;

	/*
	 * Return the prototype positive vector
	 */
	@SuppressWarnings("rawtypes")
	public Vector getPositiveVector(Map m) throws Exception {
		Vector pos = new Vector();
		vectorList = new ArrayList<Vector>();
		Vector temp = new Vector();
		String tmp = "";
		for (String s : posEmoticon) {
			tmp = (String) m.get(s);
			if (tmp != null) {
				temp = new Vector(tmp);
				vectorList.add(temp);
			}
		}
		String line = "";
		InputStream is = getClass().getResourceAsStream("/PositiveWord");
		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			tmp = (String) m.get(line);
			if (tmp != null) {
				temp = new Vector(tmp);
				vectorList.add(temp);
			}
		}
		pos = vo.sum(vectorList);
		return pos;
	}

	/*
	 * Return the prototype negative vector
	 */
	@SuppressWarnings("rawtypes")
	public Vector getNegativeVector(Map m) throws Exception {
		Vector neg = new Vector();
		vectorList = new ArrayList<Vector>();
		Vector temp = new Vector();
		String tmp = "";
		for (String s : negEmoticon) {
			tmp = (String) m.get(s);
			if (tmp != null) {
				temp = new Vector(tmp);
				vectorList.add(temp);
			}

		}
		String line = "";
		InputStream is = getClass().getResourceAsStream("/NegativeWord");
		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			tmp = (String) m.get(line);
			if (tmp != null) {
				temp = new Vector(tmp);
				vectorList.add(temp);
			}
		}
		neg = vo.sum(vectorList);
		return neg;
	}

	/*
	 * Return the objective prototype vector
	 */
	@SuppressWarnings("rawtypes")
	public Vector getObjectiveVector(Map m) throws Exception {
		Vector obj = new Vector();
		vectorList = new ArrayList<Vector>();
		Vector temp = new Vector();
		String tmp = "";
		String line = "";
		InputStream is = getClass().getResourceAsStream("/ObjectiveWord");
		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			tmp = (String) m.get(line);
			if (tmp != null) {
				temp = new Vector(tmp);
				vectorList.add(temp);
			}
		}
		obj = vo.sum(vectorList);
		return obj;
	}

	/*
	 * Return the subjective prototype vector
	 */
	@SuppressWarnings("rawtypes")
	public Vector getSubjectiveVector(Map m) throws Exception {
		Vector subj = vo.sum(getPositiveVector(m), getNegativeVector(m));
		return subj;
	}
	
	/*
	 * Return true if e is a positive emoticon, false otherwise
	 */
	public boolean isPosEmoticon(String e){
		for (String string : posEmoticon) {
			if(e.equals(string)) return true;
		}
		return false;
	}
	
	/*
	 * Return true if e is a negative emoticon, false otherwise
	 */
	public boolean isNegEmoticon(String e){
		for (String string : negEmoticon) {
			if(e.equals(string)) return true;
		}
		return false;
	}

}
