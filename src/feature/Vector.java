package feature;

import java.util.ArrayList;
import java.util.List;

public class Vector {

	private List<Double> l;
	
	/*
	 * Creates a new empty vector
	 */
	public Vector() {
		l = new ArrayList<Double>();
	}
	
	/*
	 * Creates a vector from a string of numbers, separated by spaces
	 */
	public Vector(String s){
		l = new ArrayList<Double>();
		String[] ss = s.split(" ");
		for (String string : ss) {
			l.add(Double.parseDouble(string));
		}
	}
	
	/*
	 * Add a dimension to the vector
	 */
	public void add(double d){
		l.add(d);
	}
	
	/*
	 * Add dimensions to the vector
	 */
	public void add(double[] d){
		for (double e : d) {
			l.add(e);
		}
	}
	
	/*
	 * returns the size of the vector
	 */
	public int length(){
		return l.size();
	}
	
	/*
	 * Gets element by index
	 */
	public Double getElement(int index){
		return l.get(index);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Double d : l) {
			s += d + " ";
		}
		return s;
	}
}
