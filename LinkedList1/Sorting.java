import java.util.Random;


public class Sorting {
	public static void QuickSort(int[] list, int start, int end) {
		if(start < end) {
			int p = Partition(list, start, end);
			QuickSort(list, start, p - 1);
			QuickSort(list, p + 1, end);
		} // end if
	} // end QuickSort()
	
	public static int Partition(int[] list, int start, int end) {
		int lHS = start - 1;
		int unknown = end;
		for(int i=start; i < end; i++) {
			if(list[i] <= list[unknown]) {
				swap(list, i, lHS+1);
				lHS++;
			} // end if
		} // end for
		swap(list, end, lHS+1);
		return lHS + 1;
	} // end Partition()
	
	public static void swap(int[] list, int index1, int index2) {
		int temp = list[index1];
		list[index1] = list[index2];
		list[index2] = temp;
	} // end swap()
	
	public static void MergeSort(int[] list) {
		int n = list.length;
		int[] left = new int[n/2];
		int[] right = new int[n - n/2];
		int[] result = new int[n];
		
		for(int i=0; i < n/2; i++) {
			left[i] = list[i];
		} // end for
		
		for(int i=0; i < n - n/2; i++) {
			right[i] = list[n/2 + i];
		} // end for
		
		int i=0, j=0, k=0;
		while(i < left.length && j < right.length) {
			if(left[i] < right[j]) {
				result[k] = left[i];
				i++;
			} else {
				result[k] = right[j];
				j++;
			} // end if
			k++;
		} // end while
		if(i < left.length) {
			while(i < left.length) {
				result[k] = left[i];
				i++;
				k++;
			} // end while
		} else {
			while(j < right.length) {
				result[k] = right[j];
				j++;
				k++;
			} // end while
		} // end if
		System.arraycopy(result, 0, list, 0, n);
	} // end MergeSort()
	
	public static void SortRecursive(int[] list, int i) {
		if(i < list.length) {
			int minIndex = findMinIndex(list, i);
			swap(list, i, minIndex);
			SortRecursive(list, i+1);
		} // end if
	} // end SortRecursive()
	
	public static int findMinIndex(int[] list, int i) {
		int minIndex = i;
		for(int j=i+1; j < list.length; j++) {
			if(list[minIndex] > list[j]) {
				minIndex = j;
			} // end if
		} // end for
		return minIndex;
	} // end findMinIndex()
	
	public static void main(String[] args) {
		Random gen = new Random();
		int n = 1000;
		int range = 100;
		int []a = new int[n];
		int []b = new int[n];
		long start, end;
		int i;
		for(i=0; i < n; i++) {
			a[i] = gen.nextInt();
			b[i] = a[i];
		} // end for
	}
	
} // end class
