package chap1;

public class WeightedQuickUnion {
	private int count;
	private int[] sz;
	private int[] id;
	public int count() { return count;}
	
	public WeightedQuickUnion(int n){
		count = n;
		id = new int[n]; //Must new int[] to avoid NullPointerException
		sz = new int[n];
		for(int i = 0; i < n; i++){ id[i] = i; }
		for(int i = 0; i < n; i++){ sz[i] = 1;}
	}
	
	public int find(int p){
		while(p != id[p]){ p = id[p];}
		return p;
	}
	
	public void union(int p, int q){
		int p_root = find(p);
		int q_root = find(q);
		if(p_root == q_root) return;
		if(sz[p_root] < sz[q_root]){
			id[p_root] = q_root;
			sz[q_root] += sz[p_root];
		}else{
			id[q_root] = p_root;
			sz[p_root] += sz[q_root];
		}
		count--;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeightedQuickUnion wqu = new WeightedQuickUnion(10);
		wqu.union(9, 0);
		wqu.union(3, 4);
		wqu.union(5, 8);
		wqu.union(7, 2);
		wqu.union(2, 1);
		wqu.union(5, 7);
		wqu.union(0, 3);
		wqu.union(4, 2);
		System.out.println(wqu.count());
		for(int i = 0; i < 10; i++){
			System.out.print(wqu.find(i)+ ", ");
		}

	}

}
