package chap1;

public class QueueOfThreeStacks<Item> {
	private int N = 0;
	private ResizingArrayStack<Item> stk_tail_top = new ResizingArrayStack<>();
	private ResizingArrayStack<Item> stk_head_top = new ResizingArrayStack<>();
	private ResizingArrayStack<Item> stk_temp = new ResizingArrayStack<>();
	public int size(){ return N;}
	
	public void pushLeft(Item item){
		stk_head_top.push(item);
		N++;
	}
	public void pushRight(Item item){
		stk_tail_top.push(item);
		N++;
	}
	public Item popLeft(){
		if(N > 0){
			if(stk_head_top.isEmpty()){
				for(int i = N; i > 0; i--){
					Item item = stk_tail_top.pop();
					stk_head_top.push(item);
					stk_temp.push(item);
				}
				stk_tail_top.clear();
				while(!stk_temp.isEmpty()){
					stk_tail_top.push(stk_temp.pop());
				}
			}
			N--;
			return stk_head_top.pop();
		}
		return null;
	}
	
	public Item popRight(){
		if(N > 0){
			if(stk_tail_top.isEmpty()){
				for(int i = N; i > 0; i--){
					Item item = stk_head_top.pop();
					stk_tail_top.push(item);
					stk_temp.push(item);
				}
				stk_head_top.clear();
				while(!stk_temp.isEmpty()){
					stk_head_top.push(stk_temp.pop());
				}
			}
		}
		if(stk_tail_top.isEmpty()){
			while(!stk_head_top.isEmpty()){
				stk_tail_top.push(stk_head_top.pop());
			}
		}
		return stk_tail_top.pop();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueueOfThreeStacks<Integer> three = new QueueOfThreeStacks<Integer>();
		three.pushRight(4);
		
		three.pushRight(5);
		three.pushLeft(3);
		System.out.println(three.size());
		System.out.println("3 = " + three.popLeft());
		System.out.println("4 = "+ three.popLeft());
		three.pushRight(6);
		System.out.println("5 = "+three.popLeft());
		three.pushLeft(2);
		System.out.println("6= " + three.popRight());
	}

}
