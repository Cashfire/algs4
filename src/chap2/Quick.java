package chap2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Quick {
	public static int compare_times = 0; 
	public static int exchange_times = 0;
	private static boolean less(Comparable v, Comparable w){
		compare_times++;
		return v.compareTo(w) < 0;}
	
	private static void exch(Comparable[] a, int i, int j){
		exchange_times++;
		Comparable t = a[i];	a[i] = a[j];	a[j] = t;}
	
	private static void show(Comparable[] a){
		for(int i = 0; i < a.length; i++){StdOut.print(a[i]+ " ");}
		StdOut.println();
	}
	static void draw(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			double x = (1.0/N)*i; 
			double y = (double) a[i]/2.0;
			double rw = 0.5/N;
			double rh = (double) a[i]/2.0;
			StdDraw.filledRectangle(x, y, rw, rh);	
		}
	}
	private static boolean isSorted(Comparable[] a){
		for(int i = 1; i< a.length; i++){
			if(less(a[i], a[i-1])){ return false; }
		}
		return true;
	}
	public static void sort(Comparable[] a){		
//		StdRandom.shuffle(a);
		sort(a, 0, a.length -1);	
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partion(a, lo, hi);
		sort(a, lo, j);
		sort(a, j+1, hi);
	}
	
	private static int partion(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi+1;
		Comparable v= a[lo];
		while(true){
			//if a[1]<v, move i to 2. if all elements in a are < v, then break.
			while(less(v,a[++i])) if(i== hi) break;
			while(less(a[--j],v)) if(j == lo) break;
			if(i>=j) break;
			//if a[i]>=v and a[j]<=v, exchange.
			exch(a, i, j);		
		}
		exch(a, lo, j);
		return j;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] arr = {11,20,30,15,21,16,10,17,50,8,12};
		Integer[] arr1 = {5,5,5,5,5,5,5,5,5,5};
		Quick qs= new Quick();
		Quick.sort(arr1);
		System.out.println("compare_times = "+Quick.compare_times);
		System.out.println("exchange_times = "+Quick.exchange_times);
	}

}
