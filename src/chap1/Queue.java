package chap1;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Queue<Item> implements Iterable<Item>{
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty(){ return first == null;}
	public int size(){ return N; }
	public void enqueue(Item item){
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()) { first = last;}
		else{ oldlast.next = last;}
		N++;
	}
	public Item dequeue(){
		Item item = first.item;
		first = first.next;	
		//corner case: just has one node and first == last.
		if(isEmpty()) { last = first; }
		N--;
		return item;		
	}
	public void print(){
		Node temp = first;
		while(temp != null){
			StdOut.print(temp.item);
			temp = temp.next;
		}
	}
	
	public void printReverse(int k){
		int reverse = N - k;
		Node temp = first;
		while(reverse != 0){
			temp = temp.next;
			reverse --;
		}
		StdOut.print(temp.item);
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();}
	private class ListIterator implements Iterator<Item>{
		private Node curr = first;
		public boolean hasNext(){	return curr != null;}
		public Item next(){
			Item item = curr.item;
			curr = curr.next;
			return item;
		}
	}
	public static void main(String[] args) {
		// what will happen if we dequeue an empty Queue?
		//-throw java.lang.NullPointerException.
		int k = 5;
		Queue<String> q = new Queue<String>();
		while(!StdIn.isEmpty()){
			String item = StdIn.readString();
			q.enqueue(item);
		}
		
		if(k <= q.size()){
			q.printReverse(k);
		}
		
	
	}

	

}
