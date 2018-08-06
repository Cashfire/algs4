/*
 * Bottom-Up Merge Sort 
 */
package chap2;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
	private static Comparable[] aux;
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
		aux = new Comparable[N];
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
		aux = new Comparable[N];
		ArrayList<Integer> groups = new ArrayList<Integer>();
		int low = 0;
		for(int i = 1; i < a.length; i++){
			//if a[i] >= a[i-1], then do nothing.
			//if a[i] < a[i-1], then update the size and index low.
			if(i== a.length -1){
				groups.add(i-low+1);
			}else if(less(a[i], a[i-1])){	
				groups.add(i-low);//the previous ascending group's length is (i-low).
				low = i;//update a[i] as the new low of the next group.
			}
		}
//		for(Integer c : groups){
//			System.out.print(c+", ");
//		}
//		System.out.println();
		
		low = 0;
		int mid = 0;
		int high =0;
		int g = 0;
		
		while(groups.get(g) != a.length){
			if(2*g < groups.size()-1){
				mid = low + groups.get(2*g) -1;
				high = mid + groups.get(2*g+1);
//				System.out.println("g = "+g);
//				System.out.println("groups["+g+"*2] ="+groups.get(2*g));
//				System.out.println("groups["+g+"*2+1] ="+groups.get(2*g+1));
//				System.out.println("Merging ("+low+", "+mid+", "+high+")");
				merge(a, low, mid, high);
				low = high +1;		
				groups.set(g, groups.get(2*g)+groups.get(2*g+1));
				g++;
			}else if(2*g == groups.size()-1){
				groups.add(g,groups.get(2*g));
				groups = new ArrayList<Integer>(groups.subList(0, g+1));
				g = 0;
				low = 0;
			}else{
				g = 0;
				low = 0;
			}
			
		}
		
	}
	
	
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}
		for(int k = lo; k <= hi; k++){
			if(i> mid){
				a[k] = aux[j++];
			}else if(j> hi){
				a[k] = aux[i++];
			}else if(less(aux[j], aux[i])){
				a[k] = aux[j++];
			}else{
				a[k] = aux[i++];
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] myarr = {11,20,30,15,21,16,10,17,50,8,12};
		MergeBU msBu = new MergeBU();
		MergeBU.sort(myarr);
		System.out.println(isSorted(myarr));
		for(Integer i: myarr){
			System.out.print(i+", ");
		}
	}

}
