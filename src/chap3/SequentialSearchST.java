package chap3;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key, Value> {
	private int N; //number of key-value pairs.
	private Node head;
	
	private class Node{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key k, Value v, Node n){
			this.key = k;
			this.value = v;
			this.next = n;
		}
	}
	
	public SequentialSearchST(){}
	
	public void put(Key k, Value v){
		if(k == null) throw new IllegalArgumentException("first argument to put() is null");
		if(v == null) { delete(k); return; }
		//if find k in the linkedlist.
		for(Node x= head; x!=null; x=x.next){
			if(k.equals(x.key)){ x.value = v; return; }
		}
		head = new Node(k, v, head); //if didn't find key.
		N++;
	}
	
	public void delete(Key k) {
		if(k == null) throw new IllegalArgumentException("first argument to delete() is null");
		Node dummyPre = new Node(null, null, head);
		for(Node x= dummyPre; x.next != null; x=x.next){
			if(k.equals(x.next.key)){ //if find k in the LinkedList.
				x.next = x.next.next;
				N--;
			}
		}
		head = dummyPre.next;
		dummyPre.next = null; //avoid loitering.
	}
	
	/**
	 * search by key, costs N times comparison at worst.
	 * @param k
	 * @return
	 */
	public Value get(Key k){
		if(k == null) throw new IllegalArgumentException("first argument to get() is null");
		for(Node x= head; x!=null; x= x.next){
			if(k.equals(x.key)){ return x.value;}
		}
		return null;
	}
	public boolean contains(Key k){
		if(k == null) throw new IllegalArgumentException("first argument to contains() is null");
		return get(k)!=null;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(Node x= head; x != null; x=x.next){	queue.enqueue(x.key);}
		return queue;
	}
	
	public static void main(String[] args) {
		String myString= "SEARCHEXAMPlE";
		String[] strings = myString.split("");
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		for(int i = 0; i < myString.length(); i++){
			st.put(strings[i], i);
		}
		for(String s: st.keys()){
			System.out.println(s+" "+ st.get(s));
		}
	}

}
