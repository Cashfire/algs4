package chap1;

import java.util.Iterator;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] arr = (Item[]) new Object[1];
	private int N = 0;
	
	public boolean isEmpty() { return N==0;}
	public int size(){ return N;}
	private void resize(int max){
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++){
			temp[i] = arr[i];
		}
		arr = temp;
	}
	public void push(Item item){
		if(N == arr.length) resize(2* arr.length);
		arr[N++] = item;
	}
	public Item pop(){
		Item item = arr[N--];
		arr[N] = null;
		if(N > 0 && N == arr.length/4) resize(arr.length/2);
		return item;
	}
	
	public Iterator<Item> iterator(){	return new ReverseArrayIterator();}
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = N;
		public boolean hasNext(){ return i > 0;}
		public Item next(){ return arr[i--]; }
		public void remove() {}
	}

	
	public static ResizingArrayStack<String> copy(ResizingArrayStack<String> ras){
		ResizingArrayStack<String> stackCopy = new ResizingArrayStack<String>();
		Iterator<String> iter = ras.iterator();
		while (iter.hasNext()){
		    stackCopy.push(iter.next());
		}
		return stackCopy;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}
