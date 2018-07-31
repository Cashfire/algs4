package chap2;

import java.util.Calendar;
import java.util.LinkedList;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Insertion {
	private static boolean less(Comparable v, Comparable w)
	{return v.compareTo(w) < 0;}
	
	private static void exch(Comparable[] a, int i, int j)
	{Comparable t = a[i];	a[i] = a[j];	a[j] = t;}
	
	private static void show(Comparable[] a){
		for(int i = 0; i < a.length; i++){StdOut.print(a[i]+ ", ");}
		StdOut.println();
	}
	private static void draw(Comparable[] a){
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
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
				exch(a, j, j-1);
//				//cartoon effect.
//				draw(a);
//				StdDraw.clear();
			}		
		}
	}
	
	public static void sortWithoutExch(Comparable[] a){
		int N = a.length;
		LinkedList<Comparable> ll= new LinkedList<Comparable>();
		ll.add(a[0]);
		for(int i = 1; i< N; i++){
			int j = i-1;
			while(j >= 0 && less(a[i], ll.get(j))){
				j--;}
			j = j+1;
			ll.add(j,a[i]);			
		}
		int k = 0;
		for(Comparable c: ll){
			a[k] = c;
			k++;
		}
	}

	
	public static void main(String[] args) {
		// create an array size 100
//		Double[] doubles = new Double[100];
//		for(int i = 0; i < 100; i++){
//			doubles[i] = StdRandom.uniform();
//		}
		Double[] doubles = {7.7, 8.6, 1.9, 2.9, 4.5, 3.4, 3.5};
		Insertion ss = new Insertion();
		ss.sortWithoutExch(doubles);
		ss.show(doubles);
//		System.out.println(isSorted(doubles));		
	}

}
