package chap3;

import edu.princeton.cs.algs4.Queue;

public class SeparateChainingHashST<Key, Value> {
	private int N; //the total number of Key-Value pairs
	private int M; //the length of symbolTables.
	private SequentialSearchST<Key, Value>[] symbolTables;
	
	public SeparateChainingHashST(int M){
		this.M = M;
		symbolTables =(SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i = 0; i< M; i++){
			symbolTables[i] = new SequentialSearchST<Key, Value>();
		}
	}
	private void put(Key k, Value v){ 
		int id = hash(k);
		if(symbolTables[id].contains(k)){ N++;}
		symbolTables[id].put(k, v); 
	}
	
	private Value get(Key k) { return (Value) symbolTables[hash(k)].get(k); }
	
	private void delete(Key k){
		int id = hash(k);
		for(int i= 0; i < M; i++){
			if(symbolTables[i].contains(k)){ symbolTables[i].delete(k);	
			N --;
			}
		}
	}
	
	private int hash(Key k){ return (k.hashCode() & 0x7fffffff)% M; }
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < M; i++){	
			for(Key k: symbolTables[i].keys())
			{ queue.enqueue(k);}
		}	
		return queue;
	}
	
	//This is a test for Hash Attack: Aa and BB will both be hashed to 21.
	public static int hashCode(String s){
		int hash = 0;
		for(int i = 0; i < s.length(); i++){
			int temp = hash;
			hash = (hash * 31)+ s.charAt(i);
			System.out.print(s.charAt(i)+": "+(hash-temp)+", ");
		}
		System.out.println(hash);
		return hash;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String s1= "Aa";
//		hashCode(s1);
//		hashCode("BB");
		String myString= "SEARCHEXAMPlE";
		String[] strings = myString.split("");
		SeparateChainingHashST<String, Integer> hs = new SeparateChainingHashST<String, Integer>(5);
		for(int i = 0; i < myString.length(); i++){
			hs.put(strings[i], i);
		}
		for(String s: hs.keys()){
			System.out.println(s+": "+hs.get(s));
		}
	}

}
