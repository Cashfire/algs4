package chap1;

public class Parentheses<Item> {
	private Item[] parentheses = (Item[]) new Object[20];
	private int N = 0;
	public void push(Item item){
		if(N == parentheses.length) resize(2* parentheses.length);
		parentheses[N++] = item;
	}
	
	public Item pop(){
		Item item =  parentheses[N--];
		parentheses[N] = null;
		if(N > 0 && N == parentheses.length/4) resize(parentheses.length/2);
		return item;
	}
	
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++){
			temp[i] = parentheses[i];
		}
		parentheses = temp;
	}

	public boolean check(Item[] items){
		for(Item item : items){
			if(item =="{" || item =="[" || item == "("){
				System.out.println("We are gonna PUSH"+ item);
				push(item);
				}else if((item == "}" && parentheses[N] == "{") ||
						(item == ")" && parentheses[N] == "(") || 
						(item == "]" && parentheses[N] == "[")){
					System.out.println("We are gonna POP"+ item);
					pop();
				}	
		}
		if(N == 0){ return true;}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parentheses<String> p = new Parentheses<String>();
		String[] mystring = {"[", "I","(","Love","]", ")"}; 
		System.out.print(p.check(mystring));		
		}

	

}
