package chap1;


public class QuickFind {
	private int[] id;
	private int count;
	public QuickFind(int N){
		count = N;
		id = new int[N];
		for(int i = 0; i < id.length; i++){
			id[i] = i;
		}
	}
	public int count(){ return count;}
	public int find(int p){
		return id[p];
	}
	
	public void union(int p, int q){
		int p_id = find(p);
		int q_id = find(q);
		if(p_id == q_id) return;
		for(int i = 0; i < id.length; i++){
			if(id[i] == p_id) { id[i] = q_id;}
		}
		count --;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickFind qf = new QuickFind(10);
		qf.union(9, 0);
		qf.union(3, 4);
		qf.union(5, 8);
		qf.union(7, 2);
		qf.union(2, 1);
		qf.union(5, 7);
		qf.union(0, 3);
		qf.union(4, 2);
		System.out.println(qf.count());
		for(int i = 0; i < 10; i++){
			System.out.print(qf.find(i)+ ", ");
		}
	}

}
