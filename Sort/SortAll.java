//Andrew Simpson
//Cs3410
//Summer 2015
//Program 4 (resubmission)
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortAll {

private int[] sorArr;
private int[] MergArr;
private int length;

public static int[] SelectionSort(int[] OrigArr) 
{
	for (int i = 0; i < OrigArr.length - 1; i++) 
	{
		int loc = i;
		for (int j = i + 1; j < OrigArr.length; j++) 
		{
			if (OrigArr[j] < OrigArr[loc]) 
			{
				loc = j;
			}
		}

		int small = OrigArr[loc];
		OrigArr[loc] = OrigArr[i];
		OrigArr[i] = small;
	}

	return OrigArr;
}

public static int[] InsertionSort(int[] OrigArr) 
{
	int temp;
	for (int i = 1; i < OrigArr.length; i++) 
	{
		for (int j = i; j > 0; j--) 
		{
			if (OrigArr[j] < OrigArr[j - 1]) 
			{
				temp = OrigArr[j];
				OrigArr[j] = OrigArr[j - 1];
				OrigArr[j - 1] = temp;
			}
		}
	}
	return OrigArr;
}

public void sort(int originalArr[]) 
{
	this.sorArr = originalArr;
	this.length = originalArr.length;
	this.MergArr = new int[length];
	MergeSorting(0, length - 1);
}

private void MergeSorting(int low, int high) 
{

	if (low < high) 
	{
		int mid = low + (high - low) / 2;
		// Sorting left side of sorArr in the below step
		MergeSorting(low, mid);
		// Sorting right side of sorArr in the below step
		MergeSorting(mid + 1, high);
		// Merging both parts
		MergingParts(low, mid, high);
	}
}

private void MergingParts(int low, int mid, int high) 
{
	for (int i = low; i <= high; i++) 
	{
		MergArr[i] = sorArr[i];
	}
	int i = low;
	int j = mid + 1;
	int k = low;
	while (i <= mid && j <= high) 
	{
		if (MergArr[i] <= MergArr[j]) 
		{
			sorArr[k] = MergArr[i];
			i++;
		} else 
		{
			sorArr[k] = MergArr[j];
			j++;
		}
		k++;
	}
	while (i <= mid) 
	{
		sorArr[k] = MergArr[i];
		k++;
		i++;
	}	
}

public void quick(int[] inputArr) 
{

	if (inputArr == null || inputArr.length == 0) 
	{
		return;
	}
	this.sorArr = inputArr;
	length = inputArr.length;
	quickSort(0, length - 1);
}

private void quickSort(int low, int high) 
{

	int i = low;
	int j = high;
	//In the below step, we are calculating pivot number
	int pivot = sorArr[low + (high - low) / 2];
	// Dividing the Array into two Arrays.
	while (i <= j) 
	{
		while (sorArr[i] < pivot) 
		{
			i++;
		}
		while (sorArr[j] > pivot) 
		{
			j--;
		}
		if (i <= j) 
		{
			exchNum(i, j);
			//Moving index to next position of the array
			i++;
			j--;
		}
	}
	// calling quickSort() recursively
	if (low < j) 
	{
		quickSort(low, j);
	}
	if (i < high) 
	{
		quickSort(i, high);
	}
}

private void exchNum(int i, int j) 
{
	int temp = sorArr[i];
	sorArr[i] = sorArr[j];
	sorArr[j] = temp;
}

public static void main(String[] args) throws FileNotFoundException 
{
	SortAll sortArr = new SortAll();
	long start;
	long end;
	long total;
	int[] outputArr;
	int[] randomNumArr = new int[200];
	int[] equalNumArr = new int[200];
	int[] sortedIncArr = new int[200];
	int[] sortedDecArr = new int[200];
	String[] one = new String[200];
	String[] two = new String[200];
	String[] three = new String[200];
	String[] four = new String[200];
	/// Scanner should go here
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter file location: ");
	String filename = sc.nextLine();
	File file = new File(filename);
	Scanner reader = new Scanner(file);

	try
	{

		//while (reader.hasNextLine()) {
		//{
		String lineJustFetched = reader.nextLine();
		{
			one = lineJustFetched.split(", ");
			for(int i=0;i<one.length;i++){
			randomNumArr[i] = Integer.parseInt(one[i]);
		}

		}
		{
		two = lineJustFetched.split(", ");
		for(int i=0;i<two.length;i++){
		equalNumArr[i] = Integer.parseInt(two[i]);
		}
		}
		{
		three = lineJustFetched.split(", ");
		for(int i=0;i<three.length;i++){
		sortedIncArr[i] = Integer.parseInt(three[i]);
		}

		}
		{
		four = lineJustFetched.split(", ");
		for(int i=0;i<four.length;i++){
		sortedDecArr[i] = Integer.parseInt(four[i]);
		}
		}
	}
//}
//}
	finally
	{
		reader.close();
	}

	///// Manually inputed the number from datafile
	//int[] randomNumArr = {492, 501, 257, 390, 478, 77, 386, 140, 318, 383, 471, 317, 483, 191, 48, 189, 458, 482, 254, 142, 31, 27, 470, 312, 416, 327, 316, 222, 358, 464, 98, 130, 52, 18, 291, 201, 138, 81, 264, 255, 400, 346, 85, 144, 393, 100, 7, 351, 218, 282, 38, 124, 448, 495, 397, 409, 318, 131, 147, 446, 292, 413, 339, 271, 325, 277, 124, 376, 321, 142, 20, 43, 173, 88, 285, 314, 58, 1, 318, 131, 147, 446, 292, 413, 339, 271, 325, 277, 124, 376, 321, 142, 20, 43, 173, 88, 285, 314, 58, 1, 240, 184, 247, 158, 299, 15, 102, 124, 14, 153, 207, 180, 22, 55, 121, 294, 467, 160, 168, 411, 228, 400, 415, 36, 130, 161, 26, 248, 183, 15, 108, 367, 447, 372, 338, 131, 142, 139, 233, 164, 302, 203, 308, 60, 455, 386, 30, 312, 164, 143};
	//int[] equalNumArr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	//int[] sortedIncArr = {9, 13, 15, 17, 18, 23, 23, 30, 33, 41, 48, 48, 56, 57, 66, 71, 71, 72, 76, 77, 79, 83, 88, 91, 104, 105, 114, 115, 115, 119, 123, 141, 142, 143, 147, 148, 150, 159, 167, 168, 172, 183, 184, 191, 197, 202, 206, 207, 210, 210, 222, 227, 231, 232, 232, 234, 238, 243, 253, 256, 257, 264, 269, 279, 279, 279, 284, 290, 301, 309, 311, 313, 314, 315, 316, 317, 318, 326, 327, 328, 328, 331, 331, 335, 342, 349, 355, 362, 366, 367, 367, 376, 377, 380, 386, 389, 390, 393, 396, 397, 400, 402, 411, 412, 413, 414, 415, 417, 418, 426, 433, 433, 447, 457, 464, 467, 468, 472, 476, 477, 479, 482, 484, 485, 487, 488, 490, 501};
	// int[] sortedDecArr = {501, 490, 488, 487, 485, 484, 482, 479, 477, 476, 472, 468, 467, 464, 457, 447, 433, 433, 426, 418, 417, 415, 414, 413, 412, 411, 402, 400, 397, 396, 393, 390, 389, 386, 380, 377, 376, 367, 367, 366, 362, 355, 349, 342, 335, 331, 331, 328, 328, 327, 326, 318, 317, 316, 315, 314, 313, 311, 309, 301, 290, 284, 279, 279, 279, 269, 264, 257, 256, 253, 243, 238, 234, 232, 232, 231, 227, 222, 210, 210, 207, 206, 202, 197, 191, 184, 183, 172, 168, 167, 159, 150, 148, 147, 143, 142, 141, 123, 119, 115, 115, 114, 105, 104, 91, 88, 83, 79, 77, 76, 72, 71, 71, 66, 57, 56, 48, 48, 41, 33, 30, 23, 23, 18, 17, 15, 13, 9};

	//Running Selection Sort on Random Array
	start = System.nanoTime();

	outputArr = SelectionSort(randomNumArr);
	end = System.nanoTime();
	total = end - start;
	System.out.println("Runtime For SELECTION SORT :\n");
	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Random Array : " + total + " nanoseconds.\n");

	//Running Selection Sort on Equal Array
	start = System.nanoTime();
	outputArr = SelectionSort(equalNumArr);
	end = System.nanoTime();
	total = end - start;
	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Equal Array : " + total + " nanoseconds.\n");

	//Running Selection Sort on Sorted Array Increasing order
	start = System.nanoTime();
	outputArr = SelectionSort(sortedIncArr);
	end = System.nanoTime();
	total = end - start;
	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	
	System.out.println();
	System.out.print("--Sorted in Increasing Order Array : " + total + " nanoseconds.\n");

	//Running Selection Sort on Sorted Array Decreasing order
	start = System.nanoTime();
	outputArr = SelectionSort(sortedDecArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Decreasing Order Array : " + total + " nanoseconds.\n");

	//Running Insertion Sort
	start = System.nanoTime();
	outputArr = InsertionSort(randomNumArr);
	end = System.nanoTime();
	total = end - start;

	System.out.println("\nRuntime For Insertion Sort :\n");
	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Random Array : " + total + " nanoseconds.\n");

	//Running Selection Sort on Equal Array
	start = System.nanoTime();
	outputArr = InsertionSort(equalNumArr);
	end = System.nanoTime();
	total = end - start;
	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Equal Array : " + total + " nanoseconds.\n");

	//Running Selection Sort on Sorted Array Increasing order
	start = System.nanoTime();
	outputArr = InsertionSort(sortedIncArr);
	end = System.nanoTime();
	total = end - start;
	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Increasing Order Array : " + total + " nanoseconds.\n");

	//Running Selection Sort on Sorted Array Decreasing order
	start = System.nanoTime();
	outputArr = InsertionSort(sortedDecArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Decreasing Order Array : " + total + " nanoseconds.\n");

	//Runtime for Merge Sort
	System.out.println("\nRuntime For Merge Sort :\n");
	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.sort(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Random Array : " + total + " nanoseconds.\n");

	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.sort(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Equal Array : " + total + " nanoseconds.\n");

	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.sort(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Increasing Order Array : " + total + " nanoseconds.\n");

	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.sort(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Decreasing Order Array : " + total + " nanoseconds.\n");

	//Runtime FOr QuickSort
	System.out.println("\nRuntime For Quick Sort :\n");
	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.quick(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Random Array : " + total + " nanoseconds.\n");

	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.quick(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Equal Array : " + total + " nanoseconds.\n");

	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.quick(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Increasing Order Array : " + total + " nanoseconds.\n");

	outputArr = randomNumArr;
	start = System.nanoTime();
	sortArr.quick(outputArr);
	end = System.nanoTime();
	total = end - start;

	for (int i : outputArr) 
	{
		System.out.print(i);
		System.out.print(" ");
	}
	System.out.println();
	System.out.print("--Sorted in Decreasing Order Array : " + total + " nanoseconds.\n");

	}

}

