package chap1;


public class RemoveNodeFromLLByKey<Item> {
	private LLNode head;
	private int N;
	private class LLNode{
		Item item;
		LLNode next;
	}
	public void push(Item k){
		LLNode old_head = head;
		head = new LLNode();
		head.item = k;
		head.next = old_head;
		N++;
	}
	
	public void remove(Item key){
		LLNode dummy = new LLNode();
		dummy.next = head;
		LLNode n = head;
		LLNode prev = dummy;
		while(n!=null){
			if(n.item == key){
				prev.next = n.next;
				N--;
				n = n.next;
			}else{
				prev = n;
				n = n.next;
			}
		}
		head = dummy.next;
	}
	
	private void printLL() {
		LLNode n = head;
		while(n!=null){
			System.out.print(n.item + ", ");
			n = n.next;
			}	
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveNodeFromLLByKey<String> test = new RemoveNodeFromLLByKey<String>();
		test.push("I");
		test.push("Love");
		test.push("coding");
		test.push("Love");
		test.printLL();
		test.remove("Love");
		test.printLL();
	}

}
