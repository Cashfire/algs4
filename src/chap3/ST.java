
package chap3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;
/**
 * The {@code ST} class represents an ordered symbol table of generic key-value pairs.
 * ST is based on TreeMay which implements Map interface similar to HashMap class, 
 * but TM is sorted in the ascending order of its keys, while HashMap is unordered.
 */
public class ST<Key extends Comparable <Key>, Value> implements Iterable<Key>{
	private TreeMap<Key, Value> st;
	public ST(){ st = new TreeMap<Key, Value>(); }
	
	public Value get(Key k){
		if(k == null) throw new IllegalArgumentException("calls get() with null key");
		return st.get(k);
	}
	
	public void put(Key k, Value v){
		if(k == null) throw new IllegalArgumentException("calls get() with null key");
		if(v == null) st.remove(k);
		else st.put(k, v);
	}
	
	public void delete(Key k){
		if(k == null) throw new IllegalArgumentException("calls get() with null key");
		st.remove(k);
	}
	
	public boolean contains(Key k){
		if(k == null) throw new IllegalArgumentException("calls get() with null key");
		return st.containsKey(k);
	}
	
	public int size(){	return st.size(); }
	public boolean isEmpty(){ return size() == 0; }
	
	public Iterable<Key> keys(){ return st.keySet(); }
	
	@Deprecated
	public Iterator<Key> iterator() { return st.keySet().iterator();	}
	
	public Key min(){
		if(isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return st.firstKey();
	}
	
	public Key max(){
		if(isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
		return st.lastKey();
	}
	public Key ceiling(Key k){
		if(k == null) throw new IllegalArgumentException("argument to ceiling() is null");
		Key key = st.ceilingKey(k);
		if(k == null) throw new NoSuchElementException("all keys are less than"+ k);
		return key;
	}
	
	public Key floor(Key k){
		if(k == null) throw new IllegalArgumentException("argument to floor() is null");
		Key key = st.ceilingKey(k);
		if(k == null) throw new NoSuchElementException("all keys are greater than"+ k);
		return key;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ST<String, Double> st = new ST<String, Double>();
		String[] grades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
		Double[] GPA = {4.33,4.00, 3.67, 3.33, 3.00, 2.67, 2.33, 2.00, 1.67, 1.00, 0.00 };
		int n = grades.length;
		for(int i = 0; i < n; i++){
			st.put(grades[i], GPA[i]);
		}
		System.out.println(st.get("D"));
	}
	

}
