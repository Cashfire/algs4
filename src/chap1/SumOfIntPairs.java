package chap1;

import java.util.Arrays;

public class SumOfIntPairs {
	public static int calculate(int[] a){
		//Arrays.sort() 's TC is NlgN
		Arrays.sort(a);
		// The following programming's TC is linear N.
		int sum = 0;
		int count = 0;
		int i = 1;
		while(i < a.length){
			if(a[i] > a[i-1]){
				sum += count*(count+1)/2;
				count = 0;
				i++;
			}else{  //else a[i] == a[i-1]
				count ++;
				i++;
			}
		}
		//corner case: all the element are the same.
		if(count != 0){ sum += count*(count+1)/2;}
		return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 1, 3, 4, 5, 6};
		System.out.println(calculate(arr));
	}

}
