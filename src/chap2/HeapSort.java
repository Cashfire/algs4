package chap2;

public class HeapSort {
	
	public static void sort(Comparable[] a){
		int n = a.length -1;
		//construct the heap
		for(int k = n/2; k >= 1; k--){ sink(a, k, n); }
//		for(int i = 0; i< a.length; i++){
//        	System.out.print(a[i]+", ");
//        }
//		System.out.println();
		//Now every heap is ordered
		while(n > 1){
			exch(a, 1, n--);
			sink(a, 1, n);
		}
		
	}
	private static void sink(Comparable[] a,int k, int N){
		while(2*k <= N){
			int l = 2*k;
			if(l < N && less(a, l, l+1)) l++; //choose its bigger child.
			if(!less(a, k, l)) break; 	//if not less than the bigger child, then break.
			exch(a, k, l);	      //if less, than exchange it with the bigger child.
			k = l;
		}
	}
	
	private static boolean less(Comparable[] a, int i, int j){
		return a[i].compareTo(a[j]) < 0;
	}
	private static void exch(Comparable[] a, int i, int j){
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myString = "S O R T E X A M P L E";
        String[] myArrayList= myString.split(" ");
        String[] start_from_one = new String[myArrayList.length+1];
        for(int i = 1; i< start_from_one.length; i++){
        	start_from_one[i]=myArrayList[i-1];
        }
        sort(start_from_one);
        for(int i = 0; i< start_from_one.length; i++){
        	System.out.print(start_from_one[i]+", ");
        }
	}

}
