
public class convertMinHeapToMaxHeap {

	public static void main(String args[]) {

		int[] arr = new int[] { 3, 5, 9, 6, 8, 20, 10, 12, 18, 9 };
		convertToMaxHeap(arr);

		System.out.println("Completed");
		for ( int i=0; i < arr.length ;i++){
			System.out.println(arr[i]);
		}
	}
	
	/**
	 * http://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity
	 * Sifting down on a tree is better than sifting up because there are more nodes 
	 * at the bottom levels than at the top 
	 * hence when sifting down there are likely to be fewer movements 
	 * @param arr
	 * @param index
	 */

	public static void maxHeapify(int[] arr, int index) {
		// sifting down -> i.e make root > left and root > right
		int leftIndex = (2 * index) + 1;
		int rightIndex = (2 * index) + 2;

		int largestIndex = index;

		if (leftIndex <= arr.length - 1 && arr[index] < arr[leftIndex]) {
			largestIndex = leftIndex;
		}
		if (rightIndex <= arr.length - 1 && arr[rightIndex] > arr[largestIndex]) {
			largestIndex = rightIndex;
		}

		if (largestIndex != index) {
			swap(arr, index, largestIndex);
			maxHeapify(arr, largestIndex);
		}

	}

	public static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	public static void convertToMaxHeap(int[] arr) {
		for (int i = arr.length/2; i >=0 ; i--) {
			maxHeapify(arr, i);
		}
	}

}
