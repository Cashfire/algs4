package chap5;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import chap1.Queue;
import sun.net.www.content.audio.x_aiff;

public class TrieST<Value> {
	private static final int R = 256; //radix is the extended ASCII.
	private Node root;
	private int N; //amount of keys in trie.
	private static class Node{
		private Object val;
		private Node[] next = new Node[R];
	}
	public TrieST(){}
	/**
	 * iterative insertion
	 * @param key
	 * @param val
	 */
	public void put(String key, Value val){
		if(root == null){ root = new Node(); }
		int d = key.length();
		Node curr= root;
		for(int i = 0; i < d; i++){
			char c = key.charAt(i);
			if(curr.next[c] == null){
				curr.next[c] = new Node();
				N++;
			}
			curr = curr.next[c];
		}
		curr.val = val;
//		System.out.println(curr.val+" has been added.");
	}
	public int size(){return N;}
	
	public Value get(String key){
		if(root == null){
			System.out.println("The trie is empty.");
			return null;
		}
		int d = key.length();
		Node curr = root;
		for(int i = 0; i < d; i++){
			char c = key.charAt(i);
			if(curr.next[c] == null){
				System.out.println(key+" does not exist in the Trie.");
				return null;
			}
			curr = curr.next[c];
		}
		if(curr.val == null){ return null;}
		return (Value) curr.val;
	}
	public void delete(String key){
		if(root == null){ return; }
		int d = key.length();
		Node curr = root;
		Node[] path = new Node[d];
		for(int i = 0; i < d; i++){
			char c = key.charAt(i);
			if(curr.next[c] == null){
				System.out.println(key+" does not exist in the Trie.");
				return;
			}
			path[i] = curr;
			curr = curr.next[c];
		}
		
		if(curr.val == null){
			System.out.println(key+" does not exist in the Trie.");
			return;
		}else{
			curr.val = null;
		}
		for(char c = 0; c < R; c++){
			//if the tail node has other child, like delete("she"), but has "shell".		
			if(curr.next[c] != null){ return;} 		
		}
		//now the tail node curr doesn't have any child.
		//move upwords, delete the unnecessary nodes in the path.
		for(int j = d - 1; j >= 0; j--){
			int child_amount = 0;
			for(char c = 0; c < R; c++){
				//set the next[key.charAt(d-1)] as null.
				if(c==key.charAt(j)){
					path[j].next[c] = null;
					System.out.println(key.charAt(j)+ " is deleted.");
				}
				//if the nodes has other child. like delete("she"), but has "shell".
				if(path[j].next[c] != null){child_amount++;} 			
			}
			//like delete("shell"), we will delete "l", "l" ,but keep "she".
			if( (child_amount > 0) || (path[j].val != null)){ return; }
		}		
	}
	
//	public Iterable<String> keys(){
//		if(root == null) return null;
//		Queue<String> queue = new Queue<String>();
//		Node curr = root;
//		for(char c = 0; c < R; c++){
//			if(curr.next[c] == null){ continue;}
//			while(curr.next[c] != null){
//				
//			}
//		}
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrieST<Integer> tSt= new TrieST<Integer>();
		tSt.put("sea", 2);
		tSt.put("shells", 3);
		tSt.put("by", 4);
		tSt.put("sells", 1);
		tSt.put("she", 0);
		tSt.delete("she");
		System.out.println("The value of key 'she' is: "+tSt.get("she"));
	}

}
