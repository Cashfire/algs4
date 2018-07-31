package chap1;

public class QuickUnion {
	private int[] id;
	private int count;
	public QuickUnion(int N){
		count = N;
		id = new int[N];
		for(int i = 0; i < id.length; i++){
			id[i] = i;
		}
	}
	public int count(){ return count;}
	public int find(int p){
		while(id[p] != p){
			p = id[p];
		}
		return p;
	}
	
	public void union(int p, int q){
		int p_root = find(p);
		int q_root = find(q);
		if(p_root == q_root) return;
		id[p_root] = q_root;
		count--;
	}
	public static void main(String[] args) {
		QuickUnion qu = new QuickUnion(10);
		qu.union(9, 0);
		qu.union(3, 4);
		qu.union(5, 8);
		qu.union(7, 2);
		qu.union(2, 1);
		qu.union(5, 7);
		qu.union(0, 3);
		qu.union(4, 2);
		System.out.println(qu.count());
		for(int i = 0; i < 10; i++){
			System.out.print(qu.find(i)+ ", ");
		}

	}

}
