/*
 * Given an N size double[] array, find out the pair whose absolute difference is minimal.
 * Require: TC as NlgN.
 */
package chap1;

import java.util.Arrays;

public class MinDiff {
	static double[] find(double[] arr){
		double[] pair = new double[2];
		Arrays.sort(arr);
		double diff = arr[1] - arr[0];
		double min_diff = diff;
		for(int i = 2; i < arr.length; i++){
			diff = arr[i] - arr[i-1];
			if(diff < min_diff){
				min_diff = diff;
				pair[0] = arr[i-1];
				pair[1] = arr[i];
			}
		}
		return pair;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] dl = {1.9, 2.9, 3.4, 3.5};
		double[] result = find(dl);
		System.out.println("Find the pair:");
		for(int i = 0; i< result.length; i++){
			System.out.print(result[i]+", ");
		}
	}

}
