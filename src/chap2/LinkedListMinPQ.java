/*
 * use a linkedlist to store the heap based priority queue.
 * Every node of the linkedList has 3 nodes: left, right and up.
 *  
 */
package chap2;

import java.util.ArrayList;

public class LinkedListMinPQ<Item extends Comparable<Item>> {
	private int N = 0; //the amount of elements in the LinkedListPQ.
	private Node root, tail;
	private class Node{
		Item item;
		Node left;
		Node right;
		Node up;
		public Node(Item v){ this.item = v;}
	}
	public LinkedListMinPQ(){	
	}
	
	public void insert(Item item){
		//Note: in a binary tree, even is always left child and odd is always right child.
		Node newNode = new Node(item);
		if(N == 0){ 
			root = tail = newNode;
			N++;
			return;
			}
		//if N is even, then newNode will be a right child on the (N+1)/odd position.
		if(N%2 == 0){
			newNode.up = tail.up;
			tail.up.right = newNode;
			tail = newNode;
			N++;
			swim(tail);
			System.out.println("After swim, the root.item ="+root.item+", and tail.item="+ tail.item);
		}else{
		//if N is odd, then newNode will be a left child on the (N+1)/even position,
		//and we should check the up nodes of newNode(N+1).
			ArrayList<Integer> parents = new ArrayList<Integer>();
			for(int k = (N+1)/2; k  >= 1 ; k = k/2){ parents.add(k);}
			//NOW parents= {(N+1)/2, (N+1)/4,...,1}
			Node parentOfnew = root;
			//the last element of parents is 1, so check from the last 2nd element.
			int n = parents.size() - 2;		
			while(n >= 0){
				if(parents.get(n)%2 == 0) parentOfnew = parentOfnew.left;
				if(parents.get(n)%2 != 0) parentOfnew = parentOfnew.right;
				n--;
			}
			parentOfnew.left = newNode;
			newNode.up = parentOfnew;
			tail = newNode;
			N++;
			swim(tail);
			System.out.println("After swim, the root.item ="+root.item+", and tail.item="+ tail.item);
		}
	}
	
	
	public boolean isHeap(){
		return isHeap(root);
	}
	private boolean isHeap(Node k){
		if(k== null) return true;
		if((k.left != null) && less(k.left, k)) return false;
		if((k.right != null) && less(k.right, k)) return false;
		return isHeap(k.left) && isHeap(k.right);
	}
	
	private boolean less(Node n1, Node n2){
		return n1.item.compareTo(n2.item) < 0;
	}

	private void swim(Node k){
		while( (k!= root) && less(k, k.up)){
			exch(k, k.up);
			k = k.up;
		}
	}
	
	private void exch(Node n1, Node n2){
		Item temp= n2.item;
		n2.item = n1.item;
		n1.item = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListMinPQ<String> llpq= new LinkedListMinPQ<String>();
		llpq.insert("S");
		llpq.insert("Y");
		llpq.insert("A");
	    System.out.println(llpq.isHeap());
	    System.out.println("Y".compareTo("S")<0);
	}

}
