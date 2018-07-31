package chap2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
	public static double time(String alg, Comparable[] a){
		Stopwatch timer = new Stopwatch();
		if(alg.equals("Insertion")) Insertion.sort(a);
		if(alg.equals("Selection")) Selection.sort(a);
		if(alg.equals("Shell")) Shell.sort(a);
//		if(alg.equals("Merge")) Merge.sort(a);
//		if(alg.equals("Quick")) Quick.sort(a);
//		if(alg.equals("Heap")) Heap.sort(a);
		return timer.elapsedTime();
	}
	
	public static double timeRandomInput(String alg, int N, int T){
		//create an array with size N, and test T times by the alg sort.
		Double[] arr = new Double[N];
		//initiate total time
		double total = 0.0;
		for(int t = 0; t < T; t++){
			for(int i= 0; i < N; i++){
				arr[i] = StdRandom.uniform();
			}
			//sort by alg, and get the time.
			total += time(alg, arr);
		}
		return total;
	}
	
	public static void main(String[] args) {
		String alg1 = "Insertion";
		String alg2 = "Selection";
		int N = 1000;
		int T = 100;
		double t1 = timeRandomInput(alg1, N, T);
		double t2 = timeRandomInput(alg2, N, T);
		StdOut.printf("For %d random Doubles\n %s is ", N, alg1);
		StdOut.printf("%.1f times faster than %s\n", t2/t1, alg2);
		
	}

}
