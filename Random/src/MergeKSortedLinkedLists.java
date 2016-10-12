import java.util.LinkedList;

public class MergeKSortedLinkedLists {
	static LinkedList<Integer> res = new LinkedList<Integer>();
	static LinkedList<Integer>[] lists = new LinkedList[3];

	public static class HeapNode {
		int value;
		int indexOfLinkedList;
		int indexOfNextElement;

		public HeapNode(int value, int indexOfLinkedList, int indexOfNextElement) {
			this.value = value;
			this.indexOfLinkedList = indexOfLinkedList;
			this.indexOfNextElement = indexOfNextElement;
		}
	}

	public static void main(String args[]) {
		int numLists = 3;

		LinkedList<Integer> l1 = new LinkedList<Integer>();
		LinkedList<Integer> l2 = new LinkedList<Integer>();
		LinkedList<Integer> l3 = new LinkedList<Integer>();

		l1.add(1);
		l1.add(3);
		l1.add(5);
		l1.add(7);

		l2.add(2);
		l2.add(4);
		l2.add(6);
		l2.add(8);

		l3.add(0);
		l3.add(9);
		l3.add(10);
		l3.add(11);

		HeapNode[] arr = new HeapNode[numLists];

		lists = new LinkedList[numLists];

		lists[0] = l1;
		lists[1] = l2;
		lists[2] = l3;

		int arrindex = 0;
		// POPULATE IT FIRST TIME

		for (int i = 0; i < numLists; i++) {
			arr[arrindex] = new HeapNode((int) lists[i].get(0), i, 1);
			arrindex++;
		}
		buildMinHeap(arr);

		for (int j = 0; j < numLists * l1.size(); j++) {

			// buildMinHeap(arr);
			int min = getMin(arr); // remove top and heapify
			res.add(min);

		}

		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}

	}

	public static int getMin(HeapNode[] arr) {
		HeapNode val = arr[0];
		int res = arr[0].value;

		LinkedList list = lists[val.indexOfLinkedList];

		// re-heapify after replacing root with next value from the same list
		// If its the last element replace with infinity so it wont be
		// considered in min-heap

		int newvalue;
		if (val.indexOfNextElement >= list.size()) {
			newvalue = Integer.MAX_VALUE;
		} else {
			newvalue = (int) list.get(val.indexOfNextElement);
		}

		arr[0] = new HeapNode(newvalue, val.indexOfLinkedList, val.indexOfNextElement + 1);
		minHeapify(arr, 0);

		return res;

	}

	public static void minHeapify(HeapNode[] arr, int index) {
		// sifting down -> i.e make root < left and root < right
		int leftIndex = (2 * index) + 1;
		int rightIndex = (2 * index) + 2;

		int smallestIndex = index;

		if (leftIndex <= arr.length - 1 && arr[leftIndex].value < arr[index].value) {
			smallestIndex = leftIndex;
		}
		if (rightIndex <= arr.length - 1 && arr[rightIndex].value < arr[smallestIndex].value) {
			smallestIndex = rightIndex;
		}

		if (smallestIndex != index) {
			swap(arr, index, smallestIndex);
			minHeapify(arr, smallestIndex);
		}

	}

	public static void swap(HeapNode[] arr, int x, int y) {
		HeapNode tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	public static void buildMinHeap(HeapNode[] arr) {
		for (int i = arr.length / 2; i >= 0; i--) {
			minHeapify(arr, i);
		}

	}
}
