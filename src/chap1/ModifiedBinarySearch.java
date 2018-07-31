/*
 * Always return the index whose value is most near the input value.
 */
package chap1;

public class ModifiedBinarySearch {
	public static int search(int[] a, int v){
		// suppose that the array is sorted already.
		int low = 0;
		int high = a.length -1;
		int mid = (low + high) /2;
		int diff = v- a[mid];
		int abs_diff = Math.abs(diff);
		int idx = mid;
		while(low < high){
			if(diff > 0){
				low = mid + 1;
				mid = (low + high) /2;
				diff = v - a[mid];
			}else if (diff < 0){
				high = mid -1;
				mid = (low + high) /2;
				diff = v - a[mid];
			}else{
				break;
			}
			
			if(Math.abs(diff) > abs_diff){
				//break;
			}else{//update the abs_diff as the minimum.
				abs_diff = Math.abs(diff);
				idx = mid;
			}
		}
		return idx;
		
	}
	public static void main(String[] args) {
		int[] arr = {0, 5, 10, 15, 20, 30};
		int[] keys = {0, 6, 16};
		for(int k : keys){
			int index = search(arr, k);
			System.out.println(k+ " near "+ arr[index]+" most.");
		}

	}

}
