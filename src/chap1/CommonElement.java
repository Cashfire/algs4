/*
 * Given 2 ordered int arrays, orderly print all the common elements.
 * Require: TC as linear N.
 */
package chap1;


public class CommonElement {
	static void print(int[] a, int[] b){
		
		for(int i=0, j=0; i < a.length && j < b.length;){
			if(a[i] == b[j]){
				System.out.print(a[i]+", ");
				i++;
				j++;
			}else if(a[i] > b[j]){
				//if a[i] > b[j], then compare a[i] and b[j+1].
				j++;
			}else{
				//if a[i] < b[j], then compare a[i+1] and b[j].
				i++;
			}
		}
	}
	
	public static void main(String[] args) {

		int[] arr1 = {1, 2, 6, 10};
		int[] arr2 = {0,3,5,7, 10};
		print(arr1, arr2);
	}

}
