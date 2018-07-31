/*
 * For i(0: N-1), make sure a[i] is the min of the a[(i+1):(N-1)]
 */
package chap2;

import java.util.Calendar;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Selection {
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
		for(int i = 0; i < N; i++){
			int min_idx = i;
			for(int j = i+1; j < N; j++){
				if(less(a[j], a[min_idx])){ min_idx = j;}
			}
			exch(a, i, min_idx);
			//cartoon effect
//			draw(a);
		}
//		StdDraw.clear();
	}
	
	public static void main(String[] args) {
		// create an array size 100
		Double[] doubles = new Double[100];
		for(int i = 0; i < 100; i++){
			doubles[i] = StdRandom.uniform();
		}
			
		Selection ss = new Selection();
		ss.sort(doubles);
		ss.draw(doubles);
//		System.out.println(isSorted(doubles));
		
	}

}
