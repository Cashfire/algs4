package chap2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shell {
	private static boolean less(Comparable v, Comparable w)
	{return v.compareTo(w) < 0;}
	
	private static void exch(Comparable[] a, int i, int j)
	{Comparable t = a[i];	a[i] = a[j];	a[j] = t;}
	
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
		int N = a.length;
		//h = 1, 4, 13, 40, 121, 364, 1093...
		int h = 1;
		while(h < N/3){ h = 3*h +1;}
		while(h >=1){
			//compare a[i]<a[i-h], then exch(a, i, i-h) and move to a[i-h]&a[i-2*h] comparison.
			for(int i= h; i< N; i++){
				//a[i] will be inserted into a[i-h], a[i-2*h] OR a[i-3*h]...
				for(int j = i; j>=h && less(a[j], a[j-h]); j-=h){
					exch(a, j, j-h);
					//cartoon effect
//					draw(a);
//					StdDraw.clear();
				}
			}
			h = h/3;
		}
	}
	
	public static void main(String[] args) {
		// create an array size 100
		Double[] doubles = new Double[100];
		for(int i = 0; i < 100; i++){
			doubles[i] = StdRandom.uniform();
		}
			
		Shell shell = new Shell();
		shell.sort(doubles);
		shell.draw(doubles);
//		System.out.println(isSorted(doubles));
	}

}
