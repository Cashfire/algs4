package chap2;

import java.util.NoSuchElementException;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.WildcardLoader;

public class MinPQ<Item extends Comparable<Item>> {
	private Item[] pq; //use indices from 1 to n. Note: spare the index 0.
	private int N;   //number of items on pq, not pq.length
	
	public MinPQ(int initCapacity){
		pq = (Item[]) new Comparable[initCapacity +1];
		N = 0;
	}
	
	public MinPQ(){ this(1);}
	
	public MinPQ(Item[] keys){
		N = keys.length;
		pq = (Item[]) new Comparable[N+1];
		for(int i = 0; i< N; i++){ pq[i+1] = keys[i]; }
		for(int k = N/2; k >= 1; k--){ sink(pq, k, N);}
		assert isMinHeap();
	}
	//helper function to double the size of the heap.
	private void resize(int capacity){
		assert capacity > N;
		Item[] temp = (Item[]) new Object[capacity];
		for(int i = 0; i <= N; i++){ temp[i] = pq[i];}
		pq = temp;
	}
	
	public void insert(Item x){
		if(N == pq.length -1) resize(2* pq.length);
		pq[++N] = x;
		swim(N); //percolate it up to maintain heap
		assert isMinHeap();
	}
	
	public Item delMin(){
		if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		Item min = pq[1];
		exch(pq, 1, N--);
		sink(pq, 1, N);
		pq[N+1] = null; // to avoid loitering and help with garbage collection;
		if( (N > 0) && (N == (pq.length- 1)/4) ) resize(pq.length /2);
		assert isMinHeap();
		return min;
	}
	
	public boolean isEmpty(){ return N == 0;}
	public int size(){ return N;}
	
	public Item min(){
		if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}
	
	/**
	 * Heap sort the priority queue.
	 * @return an array with the descending order
	 */
	public Item[] sort(){
		Item[] result = (Item[]) new Comparable[N+1];
		for(int i=0; i < N+1; i++){	result[i] = pq[i];	}
		int n = N;
		while(n > 1){
			exch(result, 1, n--);
			sink(result, 1, n);
		}
		return result;
	}
	/***************************************************************************
	* Helper functions to restore the heap invariant.
	***************************************************************************/
	
	private void swim(int k){
		while(k > 1 && greater(pq, k/2, k)){
			exch(pq, k, k/2);
			k = k/2;
		}
	}
	
	private void sink(Item[] pq, int k, int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && greater(pq, j, j+1)) j++;
			if(!greater(pq, k, j)) break;
			exch(pq, k, j);
			k = j;
		}
	}
	
	/**********************************************************************
	 * Helper functions for compares and swaps.
	 **********************************************************************/
	private boolean greater(Item[] pq, int i, int j){	return pq[i].compareTo(pq[j]) > 0;	}
	
	private void exch(Item[] pq, int i, int j){
		Item temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private boolean isMinHeap(){ return isMinHeap(1);}
	private boolean isMinHeap(int k){
		if(k > N) return true;
		int left = 2*k;
		int right = 2*k + 1;
		if(left <= N && greater(pq, k, left)) return false;
		if(right <= N && greater(pq, k, right)) return false;
		return isMinHeap(left) & isMinHeap(right);
	}
	
	public static void main(String[] args) {
		String[] myStrings = {"S", "O", "R", "T", "E", "X","A", "M","P","L","E"};
		MinPQ test = new MinPQ(myStrings);
		System.out.println("Is test a priority queue? -"+test.isMinHeap());
		//String[] rslt = (String[]) test.sort(); //will throw ClassCastException: 
		//java.lang.Comparable; cannot be cast to java.lang.String
		Comparable[] rslt = test.sort();
		for(int i = 1; i< rslt.length; i++){
			System.out.print(rslt[i]+ ", ");
		}

	}

}
