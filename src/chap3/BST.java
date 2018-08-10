package chap3;

import chap1.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	private BSTnode root;
	private class BSTnode{
		private Key key;
		private Value value;
		private BSTnode left, right;
		private int N; //amount of elements of the subtree/ tree.
		public BSTnode(Key k, Value v, int n)
		{ this.key = k; this.value = v; this.N = n;}
	}	
	
	/**
	 * Search of BST: trace down the BST.
	 * @param k
	 * @return null if {@code k} doesn't exist in the BST;
	 * 		   k.value if found the k in the BST.
	 */
	public Value get(Key k)
	{ return get(root, k);}
	public Value get(BSTnode x, Key k){
		if(x == null) return null;  //if didn't find the k.
		int cmp = k.compareTo(x.key);
		if(cmp < 0) return get(x.left, k);
		if(cmp > 0) return get(x.right, k);
		else return x.value;  //if find the k. (cmp=0)
	}
	public Value get_nonrecursive(Key k){
		BSTnode x= root;
		while(x != null){
			int cmp = k.compareTo(x.key);
			if(cmp == 0) return x.value;
			else if(cmp < 0) x = x.left;
			else if(cmp > 0) x= x.right;
		}
		return null;		
	}
	
	private BSTnode find(Key k){   
		BSTnode x = root;
		while( x != null){
			int cmp = k.compareTo(x.key);
			if(cmp == 0) return x;
			else if(cmp < 0) x = x.left;
			else if(cmp > 0) x = x.right;
		}
		System.out.println(k+" doesn't exist in the binary tree.");
		return null;
	}
	
	public Key min()
	{ return min(root).key; }
	private BSTnode min(BSTnode r){
		if(r.left == null) return r;
		return min(r.left);
	}
	public Key max()
	{ return max(root).key;}
	private BSTnode max(BSTnode x){
		if(x.right == null) return x;
		return max(x.right);
	}
	
	/**
	 * Search By Range
	 * @return
	 */
	public Iterable<Key> keys()
	{ return keys(min(), max());}
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	private void keys(BSTnode x, Queue<Key> q, Key lo, Key hi){
		if(x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left, q, lo, hi);
		if(cmplo <= 0 && cmphi >= 0) q.enqueue(x.key);
		if(cmphi > 0) keys(x.right, q, lo, hi);
	}
	/** Insertion of BST: trace down the BST, if didn't find the key, 
	 *  than new a node, reset the x.left or right nodes, and update x.N.
	 * @param k
	 * @param v
	 */
	public void put(Key k, Value v)
	{ root = put(root, k, v); }
	public BSTnode put(BSTnode x, Key k, Value v){
		//if the BST is empty and the root=null, 
		//or if didn't find k in the tree, new a node.
		if(x == null) return new BSTnode(k, v, 1);
		int cmp = k.compareTo(x.key);
		//if k < x, k must on the left of x.
		if(cmp < 0) x.left = put(x.left, k, v);
		//if k > x, k must on the right of x.
		if(cmp > 0) x.right = put(x.right, k, v);
		else x.value = v; //if find k in the tree, update the k's value.
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public int size()
	{return size(root); }
	private int size(BSTnode x) {
		if(x == null) return 0;
		else return x.N;
	}

	/** TC: O(N); SC: O(lgN)
	 * @return the height of the BST.
	 */
	public int height_by_recursion(){
		return height_by_recursion(root, 1);
	}
	private int height_by_recursion(BSTnode n, int h){
		if(n.left == null && n.right == null) return h;
		int h1=0, h2=0;
		if(n.left != null){
			h1 = height_by_recursion(n.left, h+1);
		}
		if(n.right != null){
			h2 = height_by_recursion(n.right, h+1);
		}
		return Math.max(h1, h2);
	}
	
	/**
	 * The min is the most left element of the BST, (Min.up).left = min. 
	 * Let (min.up).left = (min.up).right, update the (min.up).N, and return (min.up).
	 * Finally, root will be the updated root without the node min.
	 */
	public void deleteMin()
	{ root = deleteMin(root);}
	private BSTnode deleteMin(BSTnode x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) +1;
		return x;
	}
	
	public void delete(Key k)
	{ root = delete(root, k); }
	private BSTnode delete(BSTnode x, Key k) {
		if(x == null) return null; //if didn't find k in the BST.
		int cmp = k.compareTo(x.key);
		if(cmp < 0)       x.left = delete(x.left, k);
		else if(cmp > 0)  x.right = delete(x.right, k);
		else{ //find k in the BST:
			if(x.right == null) return x.left;
			if(x.left == null)  return x.right;
			BSTnode to_be_deleted = x;
			x = min(to_be_deleted.right); //x points to the substitute node of to_be_deleted.
			x.right = deleteMin(to_be_deleted.right);
			x.left = to_be_deleted.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	
	public void print_tree(Key k){
		print(find(k));
		System.out.println();
	}
	private void print(BSTnode r){
		if(r == null) return;
		print(r.left);
		System.out.print(r.key+ ", ");
		print(r.right);
	}
	
	public Key floor_nonrecusrive(Key k){
		BSTnode largest_less_than = null, x = root;
		while(x != null){
			int cmp = k.compareTo(x.key);
			if(cmp == 0){ 
				return x.key;
			}
			else if(cmp > 0){
				largest_less_than = x;
				x = x.right;
			}else{ x = x.left;}
		}
		if(largest_less_than == null){ return null;}
		else{ return largest_less_than.key;}
	}
	
	public Key ceiling_nonrecusrive(Key k){
		BSTnode smallest_larger_than = null, x = root;
		while(x != null){
			int cmp = x.key.compareTo(k);
			if(cmp == 0){ return x.key; }
			else if(cmp < 0){
				x = x.right;
			}else{
				smallest_larger_than = x;
				x = x.left;}
		}
		if(smallest_larger_than == null){ return null;}
		else{ return smallest_larger_than.key;}
	}
	
	public static void main(String[] args) {
		BST<String, Integer> bst= new BST<String, Integer>();
		bst.put("S", 0);
		bst.put("E", 1);
		bst.put("B", 2);
		bst.put("R", 3);
		bst.put("C", 4);
		bst.put("H", 5);
		bst.put("E", 6);
		bst.put("X", 7);
		bst.put("M", 8);
		bst.put("P", 9);
		bst.delete("E");
		bst.print_tree("S");
		System.out.println("floor(N) = M: "+bst.floor_nonrecusrive("N"));
		System.out.println("ceiling(Y) = null: "+bst.ceiling_nonrecusrive("Y"));
		System.out.println("E's value = "+bst.get("E"));
		System.out.println("Norecursive E's value = "+ bst.get_nonrecursive("E"));
		System.out.println("size = "+bst.size());
		System.out.println("bst.min() = "+bst.min());
		System.out.println("bst.max() = "+bst.max());
		System.out.println("bst.height_by_recusrion() = "+bst.height_by_recursion());
		
	}

}
