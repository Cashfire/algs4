package chap3;

import chap1.Queue;

public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 16;
	private int N; //numbers of key-value pairs.
	private int M; //length of the LinearProbingHashST.
	private Key[] keys;
	private Value[] values;
	public LinearProbingHashST(){ this(INIT_CAPACITY);}
	public LinearProbingHashST(int m){
		N = 0;
		M = m;
		keys = (Key[]) new Object[m];
		values = (Value[]) new Object[m];
	}
	
	private void resize(int m){
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(m);
		for(int i = 0; i < M; i++){
			if(keys[i] != null){ 
				temp.put(keys[i], values[i]);  //Note: MUST use put() to rehash all keys!!!
			}
		}
		keys = temp.keys;
		values = temp.values;
		M = temp.M;
	}
	private int hash(Key k){ 
		return (k.hashCode() & 0x7fffffff) % M; 
	}

	private boolean contains(Key k){ return get(k) != null;}
	
	private Value get(Key k) {
		if (k == null) throw new IllegalArgumentException("argument to get() is null");
		for(int i = hash(k); keys[i] != null; i = (i+1)% M){
			if(keys[i].equals(k)){ 
				return values[i];
			}
		}
		return null;
	}
		
	public void put(Key k, Value v){
		//double table size if 50% full.
		if(N >= M/2) { 
			resize(2*M);
		} 
		int i = hash(k);	
		//if keys[i] has a key already.
		while(keys[i] != null){
			//if keys[i] == k, update the values[i] as v.
			if(keys[i].equals(k)) { 
				values[i] = v; 
				return; 
			}
			i = (i+1)% M;
		}
		keys[i] = k;
		values[i] = v;
		N++;
		
	}
	
	public void delete(Key k){
		if(!contains(k)){ return;}
		int i = hash(k);
		while(keys[i] != k){ i = (i + 1)%M;}
		keys[i] = null;
		values[i] = null;
		//rehash all keys in the same cluster.
		i = (i+1)%M;
		while(keys[i] != null){
			Key keyToReDo = keys[i];
			Value valueToReDo = values[i];
			keys[i] = null;
			values[i] = null;
			N--;
			put(keyToReDo, valueToReDo);
			i = (i+1)%M;
		}
		N--;
		if(N >0 & N <= M/8) resize(M/2);
		assert check();
	}
	
	//integrity check during a delete().
	private boolean check(){
		//first, check that hash table is at most 50% full.
		if(M < 2*N){
			System.err.println("Hash table size M = "+M+", key-value pairs' amount N = "+N);
			return false;
		}
		//second, check that each key in the table can be found by get().
		for(int i = 0; i < M; i++){
			if(keys[i] == null) continue;
			else if(get(keys[i]) != values[i]){
				System.err.println("get("+keys[i]+") = "+get(keys[i])+", values["+i+"] ="+values[i]);
				return false;
			}
		}
		return true;
	}
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < M; i++){
			if(keys[i] != null){ queue.enqueue(keys[i]); }
		}
		return queue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myString= "SEARCHEXAMPlE";
		String[] strings = myString.split("");
		LinearProbingHashST<String, Integer> lp = new LinearProbingHashST<String, Integer>();
		for(int i = 0; i < strings.length; i++){
			lp.put(strings[i], i);
		}
		for(String s: lp.keys()){
			Integer i = lp.get(s);
			System.out.println(s+": "+i);
		}
	}

}
