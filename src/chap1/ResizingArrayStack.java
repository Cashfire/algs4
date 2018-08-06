package chap1;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] arr = (Item[]) new Object[16];
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
	public void clear(){
		arr = (Item[]) new Object[16];
		N = 0;
	}
	
	public void push(Item item){
		if(N == arr.length) resize(2* arr.length);
		arr[N++] = item;
	}
	public Item pop(){
		if(N > 0){	
			Item item = arr[--N];
			arr[N] = null;
			if(N == arr.length/4) resize(arr.length/2);
			return item;
		}
		return null;
	}
	
	@Override
	public Iterator<Item> iterator(){	return new ReverseArrayIterator();}
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = N;
		@Override
		public boolean hasNext(){ return i > 0;}
		@Override
		public Item next(){ return arr[i--]; }
		@Override
		public void remove() {}
	}

	void printStack(){
		System.out.println(N);
		for(int i = 0; i < N; i++){
			System.out.print(arr[i]+", ");
		}
		
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
		ResizingArrayStack<String> myStack = new ResizingArrayStack<String>();
		myStack.push("I");
		myStack.push("am");
		myStack.push("Deborah");
		System.out.println(myStack.size());
		System.out.println(myStack.pop());
		myStack.pop();
		myStack.printStack();
	}

}
