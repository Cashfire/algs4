package chap3;

import com.sun.corba.se.impl.orbutil.graph.Node;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int N= 0;
	public BinarySearchST(int capacity){
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Comparable[capacity];
	}
	
	/** How many elements in the ST are < {@code key} ?
	 * @param key
	 * @return the number of keys in the symbol table strictly less than {@code key}.
	 * @throws IllegablArgumentException if {@code key} is {@code null}
	 */
	public int rank(Key key){
		if(key == null){ throw new IllegalArgumentException("argument to rank() is null"); }
		int lo = 0, hi = N-1;
		while(lo <= hi){
			int mid = lo + (hi- lo)/2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0){ hi = mid -1;}
			else if(cmp > 0){ lo = mid + 1;}
			else return mid; //key exists in the ST.
		}
		return lo; //key doesn't exist in the ST.
	}
	
	public void put(Key key, Value val){
		if(key == null){ throw new IllegalArgumentException("argument to rank() is null"); }
		if(val == null){ delete(key); return;}
		int i = rank(key);
		//if key is in the ST: update the value and return.
		if(i < N && keys[i].compareTo(key)== 0)
		{ values[i] = val; return;}
		//else: move all larger elements backward, and insert the key-val pair.
		for(int j = N; j > i; j--){
			keys[j] = keys[j-1];
			values[j] = values[j-1];
		}
		keys[i] = key; values[i] = val;
		N++;		
	}
	
	private void delete(Key key) {
		if(key == null){ throw new IllegalArgumentException("argument to delete() is null"); }
		if(N == 0) return;
		int i = rank(key);
		//if key not in ST: return;
		if(keys[i].compareTo(key) != 0) return;
		//else: remove the key-value pair.
		for(int j = i; j< N-1; j++){
			keys[j] = keys[j+1];
			values[j] = values[j+1];
		}
		N--;
		keys[N] = null; //to avoid loitering.
		values[N] = null;
		//resize if 1/4 full
		if(N>0 && N == keys.length/4){ resize(keys.length/2);}
	}

	/**
	 * Resize the keys[] and values[] to size capacity.
	 * @param capacity
	 */
	private void resize(int capacity) {
		Key[] tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Comparable[capacity];
		for(int i = 0; i < N; i++){
			tempk[i] = keys[i];
			tempv[i] = values[i];
		}
		keys = tempk;
		values = tempv;
	}
	/**
	 * 
	 * @param key
	 * @return the largest Key in the ST that <= {@code key}.
	 */
	public Key floor(Key key){
		if(key == null){ throw new IllegalArgumentException("argument to floor() is null"); }
		int i = rank(key);
		if(i == 0){return null;}
		else if(key.compareTo(keys[i])==0){ return keys[i];}
		else{ return keys[i-1];}
		
	}
	
	public static void main(String[] args) {
		BinarySearchST<String, Integer> bsst= new BinarySearchST(10);
		bsst.put("Harris", 94134);
		bsst.put("Elaine", 94108);
		bsst.put("Larry", 92118);
//		bsst.put("Jill", 94105);
		System.out.println(bsst.floor("Jill"));
		

	}

}
