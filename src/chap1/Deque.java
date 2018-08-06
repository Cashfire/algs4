package chap1;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
	private int N = 0;
	private Node first;
	private Node last;
	private class Node{
		Item item;
		Node next;
	}
	public boolean isEmpty(){ return first == null;}
	public int Size(){ return N;}
	public void pushLeft(Item item){
		Node old_first = first;
		first = new Node();
		first.item = item;
		if(old_first == null){ 
			last = first;
		}else{
			first.next = old_first;
		}
		N++;
	}
	public  void pushRight(Item item){
		Node old_last = last;
		last = new Node();
		last.item = item;
		if(old_last == null){
			first = last;
		}else{
			old_last.next = last;
		}
		N++;
//		System.out.println("After pushRight: first = "+first.item+", last = "+last.item);
	}
	
	public Item popLeft(){
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	public Item popRight(){
		Item item = last.item;
		Node prev = first;
		if(first == last){ //if there is just one node.
			N--;
		}else{
			while(prev.next != last){
				prev = prev.next;
			}
			last = prev;
			last.next = null;
			N--;
		}
		return item;
	}
	public void print(){
		Iterator itr = iterator();
		while(itr.hasNext()){
			System.out.print(itr.next());
		}
		System.out.println();
 	}
	@Override
	public Iterator<Item> iterator() {	return new ListIterator(); }
	private class ListIterator implements Iterator<Item>{
		private Node curr = first;
		@Override
		public boolean hasNext() {	return curr != null;}
		@Override
		public Item next() {
			Item item = curr.item;
			curr = curr.next;
			return item;
		}
		
	}
	
	public static void main (String[] args){
		Deque<String> dq = new Deque<String>();
		dq.pushRight("are ");
		dq.pushLeft("They ");
		dq.pushRight("values?");
		dq.print();
		dq.popRight();
		dq.popLeft();
		dq.pushLeft("Those ");
		dq.pushRight("hobbies.");
		dq.print();
		System.out.println(dq.Size());
		
	}
}
